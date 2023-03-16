package cn.srd.itcp.sugar.component.id.generator.snowflake.support;

import cn.srd.itcp.sugar.component.id.generator.snowflake.core.EnableSnowflakeId;
import cn.srd.itcp.sugar.component.id.generator.snowflake.core.SnowflakeIdConfig;
import cn.srd.itcp.sugar.component.id.generator.snowflake.core.SnowflakeIdConfigScan;
import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.*;
import cn.srd.itcp.sugar.tool.core.asserts.Assert;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Set;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar SnowflakeId
 *
 * @author wjm
 * @since 2022-08-29 09:04:19
 */
@AutoConfiguration
public class SugarSnowflakeIdAutoConfiguration {

    private static final String METHOD_NAME_OF_SET_WORKER_ID = "setWorkerId";

    /**
     * 装配 {@link YitIdHelper}
     */
    @PostConstruct
    public void initIdGeneratorOptions() {
        if (Objects.isNotEmpty(SpringsUtil.scanPackageByAnnotation(EnableSnowflakeId.class))) {
            String[] packageNamesToFindSnowflakeConfig = new String[]{SpringsUtil.getRootPackagePath()};
            Set<Class<?>> classesWithSnowflakeConfigScan = SpringsUtil.scanPackageByAnnotation(SnowflakeIdConfigScan.class);
            Assert.INSTANCE.set(StringsUtil.format("found multi @{} in {}, please just specifies one", SnowflakeIdConfigScan.class.getSimpleName(), CollectionsUtil.toList(classesWithSnowflakeConfigScan, Class::getName))).throwsIfTrue(classesWithSnowflakeConfigScan.size() > 1);
            Set<Class<?>> snowflakeConfigSubclasses;
            if (Objects.isNotEmpty(classesWithSnowflakeConfigScan)) {
                packageNamesToFindSnowflakeConfig = ArraysUtil.append(packageNamesToFindSnowflakeConfig, AnnotationsUtil.getAnnotationValue(CollectionsUtil.getFirst(classesWithSnowflakeConfigScan), SnowflakeIdConfigScan.class));
                snowflakeConfigSubclasses = ClassesUtil.scanPackagesBySuper(packageNamesToFindSnowflakeConfig, SnowflakeIdConfig.class);
            } else {
                snowflakeConfigSubclasses = SpringsUtil.scanPackagesBySuper(SnowflakeIdConfig.class);
            }
            Assert.INSTANCE.set(StringsUtil.format("no sub class of [{}], please specifies one", SnowflakeIdConfig.class.getSimpleName())).throwsIfEmpty(snowflakeConfigSubclasses);
            Assert.INSTANCE.set(StringsUtil.format("found multi sub class [{}] of [{}], please just specifies one", CollectionsUtil.toList(snowflakeConfigSubclasses, Class::getName), SnowflakeIdConfig.class.getSimpleName())).throwsIfTrue(snowflakeConfigSubclasses.size() > 1);
            Class<?> snowflakeConfigSubclass = CollectionsUtil.getFirst(snowflakeConfigSubclasses);
            short workerId = ReflectsUtil.invoke(SpringsUtil.registerCapableBean(snowflakeConfigSubclass), METHOD_NAME_OF_SET_WORKER_ID);
            IdGeneratorOptions options = new IdGeneratorOptions(workerId);
            YitIdHelper.setIdGenerator(options);
        }
    }

}
