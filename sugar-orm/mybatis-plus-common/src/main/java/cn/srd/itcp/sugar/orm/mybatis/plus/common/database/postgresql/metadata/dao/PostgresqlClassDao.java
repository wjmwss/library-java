package cn.srd.itcp.sugar.orm.mybatis.plus.common.database.postgresql.metadata.dao;

import cn.srd.itcp.sugar.orm.mybatis.plus.common.core.GenericCurdDao;
import cn.srd.itcp.sugar.orm.mybatis.plus.common.database.postgresql.metadata.bean.po.PostgresqlClassPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * (pg_class) dao
 *
 * @author wjm
 * @since 2022-07-18 17:59:54
 */
@Mapper
public interface PostgresqlClassDao extends GenericCurdDao<PostgresqlClassPO> {

}