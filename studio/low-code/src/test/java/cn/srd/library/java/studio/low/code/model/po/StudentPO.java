// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.model.po;

import cn.srd.library.java.orm.contract.model.base.PO;
import cn.srd.library.java.studio.low.code.model.bo.StudentBO;
import cn.srd.library.java.studio.low.code.model.converter.StudentConverter;
import cn.srd.library.java.studio.low.code.model.vo.StudentVO;
import com.mybatisflex.annotation.Table;
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
@Table(value = "student")
public class StudentPO extends StudentBO implements PO {

    @Serial private static final long serialVersionUID = 264523282982557895L;

    @Override
    public StudentVO toVO() {
        return StudentConverter.INSTANCE.toVO(this);
    }

}