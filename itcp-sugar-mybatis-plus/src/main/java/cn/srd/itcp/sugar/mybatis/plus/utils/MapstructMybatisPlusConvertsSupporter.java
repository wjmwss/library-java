package cn.srd.itcp.sugar.mybatis.plus.utils;

import cn.srd.itcp.sugar.convert.mapstruct.exception.MapstructConvertMethodUnsupportedException;
import cn.srd.itcp.sugar.convert.mapstruct.support.MapstructConvertsSupporter;
import cn.srd.itcp.sugar.tools.constant.StringPool;
import cn.srd.itcp.sugar.tools.core.CollectionsUtil;
import cn.srd.itcp.sugar.tools.page.PageResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Mapstruct 转换器支持：{@link IPage} 类型
 *
 * @author wjm
 * @date 2021/5/1 14:13
 */
public class MapstructMybatisPlusConvertsSupporter implements MapstructConvertsSupporter {

    public static final MapstructMybatisPlusConvertsSupporter INSTANCE = new MapstructMybatisPlusConvertsSupporter();

    /**
     * com.baomidou.mybatisplus.core.metadata.IPage
     */
    private static final String IPAGE_NAME = IPage.class.getName();

    /**
     * cn.sugar.tools.page.PageResult
     */
    private static final String PAGE_RESULT_NAME = PageResult.class.getName();

    @Override
    public String buildKeyToMatchMapstructMethod(@NonNull Object source) {
        // PagerResult 类型从第一层缓存永远获取不到转换方法，因此此处直接返回 null
        return null;
    }

    @Override
    public String buildKeyToMatchMapstructMethod(@NonNull Class<?> target, @NonNull Object... sources) {
        if (sources.length == 1) {
            return IPAGE_NAME + ((IPage<?>) sources[0]).getRecords().get(0).getClass().getName() + StringPool.SLASH + PAGE_RESULT_NAME + target.getName();
        }
        // PagerResult 类型只支持一对一转换
        throw new MapstructConvertMethodUnsupportedException();
    }

    @Override
    public <T> Object getDefaultValue(@Nullable T defaultValue) {
        return Objects.isNull(defaultValue)
                ?
                new PageResult<>()
                        .setCurrentPage(0L)
                        .setTotalPages(0L)
                        .setPageSize(0L)
                        .setTotal(0L)
                        .setData(new ArrayList<>())
                :
                // TODO wjm 关于分页参数未优化，应该不会有这种需求
                new PageResult<>()
                        .setCurrentPage(1L)
                        .setTotalPages(1L)
                        .setPageSize(1L)
                        .setTotal(1L)
                        .setData(CollectionsUtil.newArrayList(defaultValue));
    }

}
