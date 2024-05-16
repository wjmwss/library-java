// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.studio.low.code.controller;

import cn.srd.library.java.orm.contract.model.page.PageResult;
import cn.srd.library.java.studio.low.code.model.vo.TeacherGetConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherListConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherPageConditionVO;
import cn.srd.library.java.studio.low.code.model.vo.TeacherVO;
import cn.srd.library.java.studio.low.code.service.TeacherService;
import cn.srd.library.java.tool.validation.jakarta.ValidList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 教师信息 controller
 *
 * @author TODO 请填写作者名字
 * @since 2024-04-15 23:57
 */
@Tag(name = "教师信息管理")
@AllArgsConstructor
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Operation(summary = "保存数据")
    @PostMapping("/save")
    public void save(@RequestBody TeacherVO teacherVO) {
        teacherService.save(teacherVO);
    }

    @Operation(summary = "根据 id 更新数据")
    @PostMapping("/updateById")
    public void updateById(@RequestBody TeacherVO teacherVO) {
        teacherService.updateById(teacherVO);
    }

    @Operation(summary = "根据 id 删除数据")
    @PostMapping("/deleteByIds")
    public void deleteByIds(@Validated @RequestBody ValidList<Long> ids) {
        teacherService.deleteByIds(ids);
    }

    @Operation(summary = "根据条件查询数据")
    @PostMapping("/getByCondition")
    public TeacherVO getByCondition(@RequestBody TeacherGetConditionVO conditionVO) {
        return teacherService.getByCondition(conditionVO);
    }

    @Operation(summary = "根据条件查询列表数据")
    @PostMapping("/listByCondition")
    public List<TeacherVO> listByCondition(@RequestBody TeacherListConditionVO conditionVO) {
        return teacherService.listByCondition(conditionVO);
    }

    @Operation(summary = "根据条件查询分页数据")
    @PostMapping("/pageByCondition")
    public PageResult<TeacherVO> pageByCondition(@RequestBody TeacherPageConditionVO conditionVO) {
        return teacherService.pageByCondition(conditionVO);
    }

}