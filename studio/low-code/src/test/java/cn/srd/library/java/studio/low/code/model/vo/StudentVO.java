// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.model.vo;

import cn.srd.library.java.orm.contract.model.base.VO;
import cn.srd.library.java.studio.low.code.model.bo.StudentBO;
import cn.srd.library.java.studio.low.code.model.po.StudentPO;
import cn.srd.library.java.tool.convert.api.Converts;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

/**
 * 学生信息 model
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@AutoMappers({@AutoMapper(target = StudentBO.class), @AutoMapper(target = StudentPO.class)})
public class StudentVO extends StudentBO implements VO {

    @Serial private static final long serialVersionUID = -5249617111496549985L;

    @Override
    public StudentPO toPO() {
        return Converts.onMapstruct().toBean(this, StudentPO.class);
    }

}