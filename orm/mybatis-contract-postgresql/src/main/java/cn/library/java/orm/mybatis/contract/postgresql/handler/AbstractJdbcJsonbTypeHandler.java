// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.library.java.orm.mybatis.contract.postgresql.handler;

import cn.library.java.orm.mybatis.contract.base.handler.AbstractJdbcComplexTypeHandler;
import cn.library.java.orm.mybatis.contract.base.type.JdbcComplexType;
import cn.srd.library.java.contract.constant.database.PostgreSQLDataType;
import cn.srd.library.java.contract.constant.jvm.SuppressWarningConstant;
import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.model.throwable.RunningException;
import cn.srd.library.java.tool.convert.all.Converts;
import cn.srd.library.java.tool.lang.text.Strings;
import io.vavr.control.Try;
import lombok.SneakyThrows;
import org.postgresql.util.PGobject;

import java.util.Optional;
import java.util.Set;

/**
 * the jdbc jsonb type handler
 *
 * @param <T> the java object data type
 * @author wjm
 * @since 2023-11-06 18:29
 */
public abstract class AbstractJdbcJsonbTypeHandler<T> extends AbstractJdbcComplexTypeHandler<T> {

    protected abstract boolean isEmptyJsonbContent(String content);

    protected abstract T toJavaObjectWhenEmptyJsonbContent();

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    protected abstract T doConvertToJavaObject(String content, Class javaType);

    protected abstract Object doConvertToJdbcObject(T javaObject);

    @SneakyThrows
    @Override
    protected Object toJdbcObject(T javaObject) {
        PGobject pgObject = new PGobject();
        pgObject.setType(PostgreSQLDataType.JSONB.getValue());
        pgObject.setValue(Converts.withJackson().toString(doConvertToJdbcObject(javaObject)));
        return pgObject;
    }

    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    @SneakyThrows
    @Override
    protected T toJavaObject(String content, String columnName) {
        if (isEmptyJsonbContent(content)) {
            return toJavaObjectWhenEmptyJsonbContent();
        }
        Set<Class> javaTypes = JdbcComplexType.JSON.getColumnMappingRelationCache().getMappingJavaTypes(columnName);
        return javaTypes.stream()
                .map(javaType -> Optional.ofNullable(Try.of(() -> doConvertToJavaObject(content, javaType)).getOrNull()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new RunningException(Strings.format(
                        "{}could not convert the value of column name [{}] to following classes {}, please check!",
                        ModuleView.ORM_MYBATIS_SYSTEM,
                        columnName,
                        javaTypes.stream().map(Class::getName).toList()
                )));
    }

}
