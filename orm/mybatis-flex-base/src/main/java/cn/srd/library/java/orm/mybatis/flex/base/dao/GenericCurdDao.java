// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.dao;

import cn.srd.library.java.contract.constant.database.SQLQueryCondition;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.page.PageConstant;
import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.orm.contract.model.base.BO;
import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.orm.contract.model.page.PageParam;
import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.orm.mybatis.flex.base.converter.PageConverter;
import cn.srd.library.java.orm.mybatis.flex.base.tool.MybatisFlexs;
import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.convert.Converts;
import cn.srd.library.java.tool.lang.functional.Action;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.reflect.Reflects;
import cn.srd.library.java.tool.lang.text.Strings;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.field.FieldQueryBuilder;
import com.mybatisflex.core.keygen.CustomKeyGenerator;
import com.mybatisflex.core.logicdelete.LogicDeleteManager;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Db;
import com.mybatisflex.core.row.Row;
import com.mybatisflex.core.service.IService;
import com.mybatisflex.core.util.ClassUtil;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * the generic curd dao
 *
 * @param <T> the entity extends {@link PO}
 * @author wjm
 * @since 2023-11-04 00:19
 */
@CanIgnoreReturnValue
@SuppressWarnings(SuppressWarningConstant.ALL)
public interface GenericCurdDao<T extends PO> extends BaseMapper<T> {

    /**
     * see <a href="https://mybatis-flex.com/zh/base/batch.html">"the batch operation guide"</a>.
     */
    int GENERATE_FULL_SQL_BATCH_SIZE = 100;

    /**
     * default batch operation size each time
     */
    int DEFAULT_BATCH_SIZE_EACH_TIME = DEFAULT_BATCH_SIZE;

    /**
     * insert and not append the null column value.
     * <ul>
     *   <li>if the value of primary key is null or blank string, will be generated by primary key generate strategy.</li>
     *   <li>if the value of primary key is not null or not blank string, then using the primary key value in entity.</li>
     *   <li>supported the entity with multiple primary keys.</li>
     * </ul>
     * for example:
     * <ul>
     *   <li>
     *       the entity: TestTablePO(id=null, name="testName")<br/>
     *       the generated sql: INSERT INTO "test_table"("id", "name") VALUES (487223443892741, 'testName');
     *   </li>
     *   <br/>
     *   <li>
     *       the entity: TestTablePO(id=null, name=null)<br/>
     *       the generated sql: INSERT INTO "test_table"("id") VALUES (487223443892741);
     *   </li>
     *   <br/>
     *   <li>
     *       the entity: TestTablePO(id=1, name=null)<br/>
     *       the generated sql: INSERT INTO "test_table"("id") VALUES (1);
     *   </li>
     *   <br/>
     *   <li>
     *       the entity with two primary keys: TestTablePO(id1=null, id2=null).<br/>
     *       the generated sql: INSERT INTO "test_table"("id1", "id2") VALUES (487223443892741, 487223443892742);
     *   </li>
     *   <br/>
     *   <li>
     *       the entity with two primary keys: TestTablePO(id1=1L, id2=2L).<br/>
     *       the generated sql: INSERT INTO "test_table"("id1", "id2") VALUES (1, 2);
     *   </li>
     * </ul>
     *
     * @param entity the operate entity
     * @return the entity with primary key
     * @see BaseMapper#insertSelective(Object)
     * @see CustomKeyGenerator#processBefore(Executor, MappedStatement, Statement, Object)
     */
    default T save(T entity) {
        BaseMapper.super.insertSelective(entity);
        return entity;
    }

    /**
     * using {@link #GENERATE_FULL_SQL_BATCH_SIZE batch size each time} to insert batch.
     *
     * @param entities the operate entities
     * @return the entities with primary key
     * @see #GENERATE_FULL_SQL_BATCH_SIZE
     * @see #save(PO)
     * @see #save(Iterable, int)
     * @see BaseMapper#insertSelective(Object)
     * @see BaseMapper#insertBatch(List, int)
     * @see IService#saveBatch(Collection, int)
     * @see CustomKeyGenerator#processBefore(Executor, MappedStatement, Statement, Object)
     */
    default List<T> save(Iterable<T> entities) {
        return save(entities, DEFAULT_BATCH_SIZE_EACH_TIME);
    }

    /**
     * insert batch.
     * <ol>
     *   <li>
     *       using {@link BaseMapper#insertBatch(List, int)} if the entites size <= {@link #GENERATE_FULL_SQL_BATCH_SIZE}, the generated insert sql like:<br/>
     *       <br/>
     *       INSERT INTO "test_table"("id", "name") VALUES<br/>
     *       (487223443892741, 'test1'),<br/>
     *       (487223443892742, 'test2'),<br/>
     *       (487223443913230, 'test3');
     *   </li>
     *   <br/>
     *   <li>
     *       using {@link IService#saveBatch(Collection, int)} if the entites size > {@link #GENERATE_FULL_SQL_BATCH_SIZE}, the generated insert sql like:<br/>
     *       <br/>
     *       INSERT INTO "test_table"("id", "name") VALUES (487223443892741, 'test1');<br/>
     *       INSERT INTO "test_table"("id", "name") VALUES (487223443892742, 'test2');<br/>
     *       INSERT INTO "test_table"("id", "name") VALUES (487223443913230, 'test3');
     *   </li>
     * </ol>
     *
     * @param entities          the operate entities
     * @param batchSizeEachTime insert size each time
     * @return the entities with primary key
     * @apiNote about the different between {@link BaseMapper#insertBatch(List, int)} and {@link IService#saveBatch(Collection, int)}, you should see <a href="https://mybatis-flex.com/zh/base/batch.html">"the batch operation guide"</a>.
     * @see #GENERATE_FULL_SQL_BATCH_SIZE
     * @see #save(PO)
     * @see BaseMapper#insertSelective(Object)
     * @see BaseMapper#insertBatch(List, int)
     * @see IService#saveBatch(Collection, int)
     * @see CustomKeyGenerator#processBefore(Executor, MappedStatement, Statement, Object)
     */
    @Transactional(rollbackFor = Throwable.class)
    default List<T> save(Iterable<T> entities, int batchSizeEachTime) {
        if (Nil.isEmpty(entities)) {
            return Collections.newArrayList();
        }
        List<T> listTypeEntities = entities instanceof List<T> ? (List<T>) entities : Converts.toList(entities);
        Action.ifTrue(listTypeEntities.size() <= GENERATE_FULL_SQL_BATCH_SIZE)
                .then(() -> BaseMapper.super.insertBatch(listTypeEntities, batchSizeEachTime))
                .otherwise(() -> Db.executeBatch(listTypeEntities, batchSizeEachTime, ClassUtil.getUsefulClass(this.getClass()), GenericCurdDao::save));
        return listTypeEntities;
    }

    /**
     * update by id.
     *
     * @param entity – the operate entity
     * @see #updateById(Iterable, int)
     */
    default void updateById(T entity) {
        BaseMapper.super.update(entity);
    }

    /**
     * update batch by id.
     *
     * @param entities the operate entities
     * @see #updateById(Iterable, int)
     * @see IService#updateBatch(Collection, int)
     */
    default void updateById(T... entities) {
        updateById(Collections.ofArrayList(entities));
    }

    /**
     * using {@link #GENERATE_FULL_SQL_BATCH_SIZE batch size each time} to update by id.
     *
     * @param entities the operate entities
     * @see #updateById(Iterable, int)
     * @see IService#updateBatch(Collection, int)
     */
    default void updateById(Iterable<T> entities) {
        updateById(entities, DEFAULT_BATCH_SIZE_EACH_TIME);
    }

    /**
     * update batch by id.
     * <ul>
     *   <li>using {@link IService#updateBatch(Collection, int)} to update.</li>
     *   <li>supported the entity with multiple primary keys.</li>
     * </ul>
     * <ul>
     *   <li>for example:</li>
     *   <ul>
     *     <li>
     *         if the entity has one primary key, the generated insert sql like:<br/><br/>
     *         UPDATE "test_table" SET "name" = 'test1' WHERE "id" = 1;
     *     </li>
     *     <br/>
     *     <li>
     *         if the entity has two primary keys, the generated insert sql like:<br/><br/>
     *         UPDATE "test_table" SET "name" = 'test1' WHERE "id" = 1 AND "id2" = 2;
     *     </li>
     *   </ul>
     * </ul>
     *
     * @param entities          the operate entities
     * @param batchSizeEachTime insert size each time
     * @apiNote see <a href="https://mybatis-flex.com/zh/base/batch.html">"the batch operation guide"</a>.
     * @see IService#updateBatch(Collection, int)
     */
    @Transactional(rollbackFor = Throwable.class)
    default void updateById(Iterable<T> entities, int batchSizeEachTime) {
        Db.executeBatch(
                entities instanceof Collection<T> ? (Collection<T>) entities : Converts.toList(entities),
                batchSizeEachTime,
                ClassUtil.getUsefulClass(this.getClass()),
                GenericCurdDao::updateById
        );
    }

    /**
     * update batch by condition.
     *
     * @param entities     the operate entities
     * @param queryWrapper the query condition
     * @see #updateById(Iterable, int)
     */
    default void updateByCondition(T entity, QueryWrapper queryWrapper) {
        BaseMapper.super.updateByQuery(entity, queryWrapper);
    }

    /**
     * update by id.
     * <ul>
     *   <li>supported the entity with version.</li>
     *   <li>supported the entity with multiple primary keys.</li><br/>
     *   <li>the update stage as following:</li>
     *   <ul>
     *     <li>the entity with one primary key: TestTablePO(id=1L, name="test1", version=0).</li>
     *     <ol>
     *       <li>SELECT * FROM "test_table" WHERE "id" = 1;</li>
     *       <li>UPDATE "test_table" SET "name" = 'test2', "version" = "version" + 1  WHERE "id" = 1 AND "version" = 0;</li>
     *     </ol>
     *   </ul>
     *   <ul>
     *     <li>the entity with two primary key: TestTablePO(id1=1L, id2=2L, name="test1", version=0).</li>
     *     <ol>
     *       <li>SELECT * FROM "test_table" WHERE "id" = 1 AND "id2" = 2;</li>
     *       <li>UPDATE "test_table" SET "name" = 'test2', "version" = "version" + 1  WHERE "id" = 1 AND "id2" = 2 AND "version" = 0;</li>
     *     </ol>
     *   </ul>
     * </ul>
     *
     * @param entity the operate entitiy
     * @see #updateById(PO)
     */
    default void updateWithVersionById(T entity) {
        setVersionFieldValue(getEntityToUpdateVersion(entity), entity);
        updateById(entity);
    }

    /**
     * update batch by id.
     *
     * @param entities    the operate entities
     * @param getIdAction how to find the primary key in each entity
     * @apiNote only support the entity with one primary key.
     * @see #updateWithVersionById(Iterable, int, Function)
     * @see #updateById(Iterable, int)
     * @see IService#updateBatch(Collection, int)
     */
    default void updateWithVersionById(Iterable<T> entities, Function<T, ? extends Serializable> getIdAction) {
        setVersionFieldValues(entities, getIdAction);
        updateById(entities);
    }

    /**
     * update batch by id.
     * <ul>
     *   <li>supported the entity with version.</li>
     *   <li>only support the entity with one primary key.</li><br/>
     *   <li>the update stage as following:</li>
     *   <ul>
     *     <li>the entities with one primary key: [TestTablePO(id=1L, name="test1", version=0), TestTablePO(id=2L, name="test2", version=0)].</li>
     *     <ol>
     *       <li>SELECT * FROM "test_table" WHERE "id" = 1  OR "id" = 2;</li>
     *       <li>UPDATE "test_table" SET "name" = 'test3', "version" = "version" + 1  WHERE "id" = 1 AND "version" = 0;</li>
     *       <li>UPDATE "test_table" SET "name" = 'test4', "version" = "version" + 1  WHERE "id" = 2 AND "version" = 0;</li>
     *     </ol>
     *   </ul>
     * </ul>
     *
     * @param entities          the operate entities
     * @param batchSizeEachTime insert size each time
     * @param getIdAction       how to find the primary key in each entity
     * @apiNote only support the entity with one primary key.
     * @see #updateById(Iterable, int)
     * @see IService#updateBatch(Collection, int)
     */
    default void updateWithVersionById(Iterable<T> entities, int batchSizeEachTime, Function<T, ? extends Serializable> getIdAction) {
        setVersionFieldValues(entities, getIdAction);
        updateById(entities, batchSizeEachTime);
    }

    /**
     * update by condition.
     *
     * @param entity       the operate entitiy
     * @param queryWrapper the query condition
     * @see #updateWithVersionById(PO)
     * @see #updateByCondition(PO, QueryWrapper)
     */
    default void updateWithVersionByCondition(T entity, QueryWrapper queryWrapper) {
        setVersionFieldValue(getEntityToUpdateVersion(entity), entity);
        updateByCondition(entity, queryWrapper);
    }

    /**
     * delete by id, recommended for the entity with multiple primary keys.
     * <ol>
     *   <li>
     *       if the entity has the logic delete column:<br/>
     *       <ul>
     *         <li>
     *              the entity with two primary keys: TestTablePO(id1=1L, id2=2L, rowIsDeleted=null).
     *         </li>
     *         <li>
     *              the generated delete sql like:<br/><br/>
     *              UPDATE "test_table"<br/>
     *              SET "row_is_deleted" = TRUE<br/>
     *              WHERE "id1" = 1 AND "id2" = 2 AND "row_is_deleted" = FALSE;
     *         </li>
     *       </ul>
     *   </li>
     *   <br/>
     *   <li>
     *       if the entity does not have the logic delete column:<br/>
     *       <ul>
     *         <li>
     *              the entity with two primary keys: TestTablePO(id1=1L, id2=2L).
     *         </li>
     *         <li>
     *              the generated delete sql like:<br/><br/>
     *              DELETE<br/>
     *              FROM "test_table"<br/>
     *              WHERE "id1" = 1 AND "id2" = 2 AND "row_is_deleted" = FALSE;
     *         </li>
     *       </ul>
     *   </li>
     * </ol>
     *
     * @param entity the entity
     */
    default void deleteById(T entity) {
        BaseMapper.super.delete(entity);
    }

    /**
     * delete batch by ids.
     *
     * @param ids the primary key values
     * @see #deleteByIds(Iterable)
     */
    default void deleteByIds(Serializable... ids) {
        deleteByIds(Collections.ofHashSet(ids));
    }

    /**
     * delete batch by ids.
     * <ol>
     *   <li>
     *       if the entity has the logic delete column, the generated delete sql like:<br/>
     *       <br/>
     *       UPDATE "test_table"<br/>
     *       SET "row_is_deleted" = TRUE<br/>
     *       WHERE ("id" = 1 OR "id" = 2 OR "id" = 3)<br/>
     *       AND "row_is_deleted" = FALSE;
     *   </li>
     *   <br/>
     *   <li>
     *       if the entity does not have the logic delete column, the generated delete sql like:<br/>
     *       <br/>
     *       DELETE<br/>
     *       FROM "test_table"<br/>
     *       WHERE "id" = 1 OR "id" = 2 OR "id" = 3;
     *   </li>
     * </ol>
     *
     * @param ids the primary key values
     */
    default void deleteByIds(Iterable<? extends Serializable> ids) {
        deleteBatchByIds(ids instanceof Collection<? extends Serializable> ? (Collection<? extends Serializable>) ids : Converts.toSet(ids));
    }

    /**
     * delete by condition.
     *
     * @param queryWrapper the query condition
     * @see #deleteByIds(Iterable)
     */
    default void deleteByCondition(QueryWrapper queryWrapper) {
        deleteByQuery(queryWrapper);
    }

    /**
     * delete all.
     *
     * @apiNote this will delete all data in table, please confirm if necessary before calling this function.
     * @see #deleteByIds(Iterable)
     */
    @Deprecated
    default void deleteAll() {
        deleteByCondition(QueryWrapper.create());
        // throw new UnsupportedException();
    }

    /**
     * delete skip logic anyway, recommended for the entity with multiple primary keys.
     * <ul>
     *   <li>
     *       no matter of whether the entity has the logic delete column:<br/>
     *       <ul>
     *         <li>
     *              the entity with two primary keys: TestTablePO(id1=1L, id2=2L, rowIsDeleted=null).
     *         </li>
     *         <li>
     *              the generated delete sql like:<br/><br/>
     *              DELETE<br/>
     *              FROM "test_table"<br/>
     *              WHERE "id1" = 1 AND "id2" = 2 AND "row_is_deleted" = FALSE;
     *         </li>
     *       </ul>
     *   </li>
     * </ul>
     *
     * @param entity the entity
     */
    default void deleteSkipLogicById(T entity) {
        LogicDeleteManager.execWithoutLogicDelete(() -> deleteById(entity));
    }

    /**
     * delete skip logic anyway.
     *
     * @param id the primary key value
     * @see #deleteSkipLogicByIds(Iterable)
     */
    default void deleteSkipLogicById(Serializable id) {
        LogicDeleteManager.execWithoutLogicDelete(() -> deleteById(id));
    }

    /**
     * delete batch skip logic anyway.
     *
     * @param ids the primary key values
     * @see #deleteSkipLogicByIds(Iterable)
     */
    default void deleteSkipLogicByIds(Serializable... ids) {
        deleteSkipLogicByIds(Collections.ofHashSet(ids));
    }

    /**
     * delete batch skip logic anyway.
     * <ul>
     *   <li>
     *       no matter of whether the entity has the logic delete column, the generated delete sql like:<br/>
     *       <br/>
     *       DELETE<br/>
     *       FROM "test_table"<br/>
     *       WHERE "id" = 1 OR "id" = 2 OR "id" = 3;
     *   </li>
     * </ul>
     *
     * @param ids the primary key values
     */
    default void deleteSkipLogicByIds(Iterable<? extends Serializable> ids) {
        LogicDeleteManager.execWithoutLogicDelete(() -> deleteByIds(ids instanceof Collection<? extends Serializable> ? (Collection<? extends Serializable>) ids : Converts.toSet(ids)));
    }

    /**
     * delete by condition and skip logic anyway.
     *
     * @param queryWrapper the query condition
     * @see #deleteSkipLogicByIds(Iterable)
     */
    default void deleteSkipLogicByCondition(QueryWrapper queryWrapper) {
        LogicDeleteManager.execWithoutLogicDelete(() -> deleteByCondition(queryWrapper));
    }

    /**
     * delete all and skip logic anyway.
     *
     * @apiNote this will delete all data in table, please confirm if necessary before calling this function.
     * @see #deleteSkipLogicByIds(Iterable)
     */
    @Deprecated
    default void deleteSkipLogicAll() {
        deleteSkipLogicByCondition(QueryWrapper.create().where(SQLQueryCondition.EMPTY_QUERY_CONDITION));
        // throw new UnsupportedException();
    }

    default Optional<T> getById(T entity) {
        return Optional.ofNullable(BaseMapper.super.selectOneByEntityId(entity));
    }

    default Optional<T> getById(Serializable id) {
        return Optional.ofNullable(selectOneById(id));
    }

    default Optional<T> getByCondition(QueryWrapper queryWrapper) {
        return Optional.ofNullable(BaseMapper.super.selectOneByQuery(queryWrapper));
    }

    default Optional<T> getFirstByCondition(QueryWrapper queryWrapper) {
        return Collections.getFirst(listByCondition(queryWrapper));
    }

    default List<T> listByIds(Iterable<? extends Serializable> ids) {
        return selectListByIds(ids instanceof Collection<? extends Serializable> ? (Collection<? extends Serializable>) ids : Converts.toSet(ids));
    }

    default List<T> listByCondition(QueryWrapper queryWrapper) {
        return selectListByQuery(queryWrapper);
    }

    default List<T> listAll() {
        return listByCondition(QueryWrapper.create());
    }

    default <R extends BO> List<R> listByCondition(QueryWrapper queryWrapper, Class<R> asType) {
        return BaseMapper.super.selectListByQueryAs(queryWrapper, asType);
    }

    // TODO wjm totalRow 的说明 在一般的分页场景中，只有第一页的时候有必要去查询数据总量，第二页以后是没必要的（因为第一页已经拿到总量了），因此， 第二页的时候，我们可以带入 totalRow，这样能提高程序的查询效率。
    default PageResult<T> pageByCondition(QueryWrapper queryWrapper) {
        return pageByCondition(PageConstant.DEFAULT_PAGE_INDEX, PageConstant.DEFAULT_PAGE_SIZE, queryWrapper);
    }

    default PageResult<T> pageByCondition(PageParam pageParam, QueryWrapper queryWrapper) {
        return pageByCondition(pageParam.getPageIndex(), pageParam.getPageSize(), queryWrapper);
    }

    default PageResult<T> pageByCondition(Number pageIndex, Number pageSize, QueryWrapper queryWrapper) {
        return PageConverter.INSTANCE.toPageResult(BaseMapper.super.paginate(pageIndex, pageSize, queryWrapper));
    }

    default <R extends BO> PageResult<R> pageByCondition(PageParam pageParam, QueryWrapper queryWrapper, Class<R> asType) {
        return pageByCondition(pageParam.getPageIndex(), pageParam.getPageSize(), queryWrapper, asType);
    }

    default <R extends BO> PageResult<R> pageByCondition(Number pageIndex, Number pageSize, QueryWrapper queryWrapper, Class<R> asType) {
        return PageConverter.INSTANCE.toPageResult(BaseMapper.super.paginateAs(pageIndex, pageSize, queryWrapper, asType));
    }

    default long countByCondition(QueryWrapper queryWrapper) {
        return BaseMapper.super.selectCountByQuery(queryWrapper);
    }

    default long countAll() {
        return countByCondition(QueryWrapper.create());
    }

    private T getEntityToUpdateVersion(T updatedEntity) {
        return getById(updatedEntity).orElseThrow(() -> new LibraryJavaInternalException(Strings.format("{}update with version failed, the entity [{}] could not be found any data in table [{}], please check!", ModuleView.ORM_MYBATIS_SYSTEM, updatedEntity.getClass().getName(), MybatisFlexs.getTableName(updatedEntity).orElse(null))));
    }

    private void setVersionFieldValue(T oldEntity, T updatedEntity) {
        String versionFieldName = MybatisFlexs.getVersionFieldName(updatedEntity).orElseThrow(() -> new LibraryJavaInternalException(Strings.format("{}update with version failed, the entity [{}] does not have the version column, please check!", ModuleView.ORM_MYBATIS_SYSTEM, updatedEntity.getClass().getName())));
        Reflects.setFieldValue(updatedEntity, versionFieldName, Reflects.getFieldValue(oldEntity, versionFieldName));
    }

    private void setVersionFieldValues(Iterable<T> updatedEntities, Function<T, ? extends Serializable> getIdAction) {
        Map<? extends Serializable, T> idMappingOldEntity = Converts.toMap(listByIds(Converts.toList(updatedEntities, getIdAction)), getIdAction);
        Map<? extends Serializable, T> idMappingUpdatedEntity = Converts.toMap(updatedEntities, getIdAction);
        idMappingOldEntity.forEach((id, oldEntity) -> {
            setVersionFieldValue(oldEntity, idMappingUpdatedEntity.get(id));
        });
    }

    // =======================================================================================================================================================
    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇
    // marked most mybatis-flex base mapper funcations as deprecated, since mybatis-flex version 1.7.3, it is not recommended to use as following:

    @Deprecated
    @Override
    default int insert(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertSelective(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertWithPk(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertWithPk(T entity, boolean ignoreNulls) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertSelectiveWithPk(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertBatch(List<T> entities, int size) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertOrUpdate(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertOrUpdateSelective(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int insertOrUpdate(T entity, boolean ignoreNulls) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int delete(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int deleteBatchByIds(List<? extends Serializable> ids, int size) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int deleteByMap(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int deleteByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int update(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int updateByMap(T entity, Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int updateByMap(T entity, boolean ignoreNulls, Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int updateByCondition(T entity, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int updateByCondition(T entity, boolean ignoreNulls, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default int updateByQuery(T entity, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneByEntityId(T entity) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneByMap(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> R selectOneByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneWithRelationsByMap(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneWithRelationsByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneWithRelationsByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default T selectOneWithRelationsById(Serializable id) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> R selectOneWithRelationsByIdAs(Serializable id, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> R selectOneWithRelationsByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectListByMap(Map<String, Object> whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectListByMap(Map<String, Object> whereConditions, Long count) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectListByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectListByCondition(QueryCondition whereConditions, Long count) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectListByQuery(QueryWrapper queryWrapper, Consumer<FieldQueryBuilder<T>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<Object> selectObjectListByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    Cursor<T> selectCursorByQuery(QueryWrapper queryWrapper);

    @Deprecated
    @Override
    List<Row> selectRowsByQuery(QueryWrapper queryWrapper);

    @Deprecated
    @Override
    default <R> List<R> selectListByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> List<R> selectListByQueryAs(QueryWrapper queryWrapper, Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectListWithRelationsByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> List<R> selectListWithRelationsByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> List<R> selectListWithRelationsByQueryAs(QueryWrapper queryWrapper, Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectAll() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default List<T> selectAllWithRelations() {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Object selectObjectByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> R selectObjectByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> List<R> selectObjectListByQueryAs(QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default long selectCountByQuery(QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default long selectCountByCondition(QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginate(Number pageNumber, Number pageSize, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginateWithRelations(Number pageNumber, Number pageSize, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginate(Number pageNumber, Number pageSize, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginateWithRelations(Number pageNumber, Number pageSize, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginate(Number pageNumber, Number pageSize, Number totalRow, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginateWithRelations(Number pageNumber, Number pageSize, Number totalRow, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginate(Number pageNumber, Number pageSize, Number totalRow, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginateWithRelations(Number pageNumber, Number pageSize, Number totalRow, QueryCondition whereConditions) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginate(Page<T> page, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginate(Page<T> page, QueryWrapper queryWrapper, Consumer<FieldQueryBuilder<T>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginateWithRelations(Page<T> page, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default Page<T> paginateWithRelations(Page<T> page, QueryWrapper queryWrapper, Consumer<FieldQueryBuilder<T>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateAs(Number pageNumber, Number pageSize, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateAs(Number pageNumber, Number pageSize, Number totalRow, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateAs(Page<R> page, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateAs(Page<R> page, QueryWrapper queryWrapper, Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateWithRelationsAs(Number pageNumber, Number pageSize, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateWithRelationsAs(Number pageNumber, Number pageSize, Number totalRow, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateWithRelationsAs(Page<R> page, QueryWrapper queryWrapper, Class<R> asType) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <R> Page<R> paginateWithRelationsAs(Page<R> page, QueryWrapper queryWrapper, Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <E> Page<E> xmlPaginate(String dataSelectId, Page<E> page, QueryWrapper queryWrapper) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <E> Page<E> xmlPaginate(String dataSelectId, Page<E> page, Map<String, Object> otherParams) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <E> Page<E> xmlPaginate(String dataSelectId, Page<E> page, QueryWrapper queryWrapper, Map<String, Object> otherParams) {
        throw new UnsupportedException();
    }

    @Deprecated
    @Override
    default <E> Page<E> xmlPaginate(String dataSelectId, String countSelectId, Page<E> page, QueryWrapper queryWrapper, Map<String, Object> otherParams) {
        throw new UnsupportedException();
    }

}
