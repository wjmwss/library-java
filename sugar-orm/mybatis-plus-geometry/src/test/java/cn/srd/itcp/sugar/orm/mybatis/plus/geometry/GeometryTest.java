package cn.srd.itcp.sugar.orm.mybatis.plus.geometry;

import cn.srd.itcp.sugar.orm.mybatis.plus.common.core.EnableMybatisPlusPageInterceptor;
import cn.srd.itcp.sugar.orm.mybatis.plus.geometry.database.postgresql.core.EnableMybatisPlusPostgresqlGeometryInterceptor;
import cn.srd.itcp.sugar.orm.mybatis.plus.geometry.model.po.GeometryTestPO;
import cn.srd.itcp.sugar.orm.mybatis.plus.geometry.service.GeometryTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@MapperScan({"cn.srd.itcp.sugar.orm.mybatis.plus.geometry.dao"})
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableMybatisPlusPageInterceptor
@EnableMybatisPlusPostgresqlGeometryInterceptor
public class GeometryTest {

    @Autowired
    private GeometryTestService geometryTestService;

    @Test
    public void testGeometryJdbcConvert() {
        List<GeometryTestPO> geometryTestPOs = geometryTestService.list();
    }

}
