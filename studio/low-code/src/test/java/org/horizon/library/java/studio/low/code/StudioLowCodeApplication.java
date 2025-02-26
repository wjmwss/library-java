package org.horizon.library.java.studio.low.code;

import com.mybatisflex.core.logicdelete.impl.DateTimeLogicDeleteProcessor;
import org.horizon.library.java.orm.mybatis.flex.base.audit.AuditLogConfig;
import org.horizon.library.java.orm.mybatis.flex.base.autoconfigure.EnableMybatisFlexCustomizer;
import org.horizon.library.java.orm.mybatis.flex.base.id.IdConfig;
import org.horizon.library.java.orm.mybatis.flex.base.id.IdGenerateType;
import org.horizon.library.java.orm.mybatis.flex.base.listener.ListenerConfig;
import org.horizon.library.java.orm.mybatis.flex.base.lock.OptimisticLockConfig;
import org.horizon.library.java.orm.mybatis.flex.base.logic.DeleteLogicConfig;
import org.horizon.library.java.orm.mybatis.flex.base.property.PropertyConfig;
import org.horizon.library.java.studio.low.code.manager.orm.MybatisInsertListener;
import org.horizon.library.java.studio.low.code.manager.orm.MybatisUpdateListener;
import org.horizon.library.java.tool.enums.autoconfigure.EnableEnumAutowired;
import org.horizon.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import org.horizon.library.java.tool.id.snowflake.model.enums.SnowflakeIdEnvironment;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan("org.horizon.library.java.studio.low.code.**.repository")
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableEnumAutowired
@EnableSnowflakeId(environment = SnowflakeIdEnvironment.MULTIPLE_NODE)
@EnableMybatisFlexCustomizer(
        globalIdGenerateConfig = @IdConfig(generateType = IdGenerateType.SNOWFLAKE),
        globalDeleteLogicConfig = @DeleteLogicConfig(processor = DateTimeLogicDeleteProcessor.class),
        globalListenerConfig = @ListenerConfig(whenInsert = MybatisInsertListener.class, whenUpdate = MybatisUpdateListener.class),
        globalOptimisticLockConfig = @OptimisticLockConfig(columnName = "version"),
        globalAuditConfig = @AuditLogConfig(enable = true),
        globalPropertyConfig = @PropertyConfig(
                xmlMapperClassPaths = {"classpath*:org/horizon/library/java/studio/low/code/**/repository/**/impl/*.xml"},
                xmlMapperEntityPackageAliasPackagePaths = {"org.horizon.library.java.studio.low.code.**.model.**"}
        )
)
@SpringBootApplication
public class StudioLowCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudioLowCodeApplication.class, args);
    }

}