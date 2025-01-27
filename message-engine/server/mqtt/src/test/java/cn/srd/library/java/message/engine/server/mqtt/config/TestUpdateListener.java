package cn.srd.library.java.message.engine.server.mqtt.config;

import cn.srd.library.java.orm.contract.mybatis.flex.model.bo.BaseBO;
import cn.srd.library.java.orm.mybatis.flex.base.listener.BaseUpdateListener;

import java.time.LocalDateTime;

/**
 * @author wjm
 * @since 2023-11-04 00:19
 */
public class TestUpdateListener implements BaseUpdateListener<BaseBO> {

    @Override
    public Class<BaseBO> getEntityType() {
        return BaseBO.class;
    }

    @Override
    public void action(BaseBO entity) {
        entity.setUpdaterId(1L).setUpdateTime(LocalDateTime.now());
    }

}