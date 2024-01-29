// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.contract.model.page;

import cn.srd.library.java.tool.lang.collection.Collections;
import cn.srd.library.java.tool.lang.object.Nil;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.List;

/**
 * the page param validation group
 *
 * @author wjm
 * @since 2023-12-04 20:24
 */
public class PageParamValidationGroup implements DefaultGroupSequenceProvider<PageParam> {

    @Override
    public List<Class<?>> getValidationGroups(PageParam pageParam) {
        // the default validation group must be added
        List<Class<?>> defaultValidationGroups = Collections.ofArrayList(PageParam.class);
        // must ensure the instance being validated is not null
        if (Nil.isNotNull(pageParam) && pageParam.getPageNumber() > 1 && Nil.isNull(pageParam.getTotalNumber())) {
            defaultValidationGroups.add(TotalRecordNumberValidator.class);
        }
        return defaultValidationGroups;
    }

    public interface TotalRecordNumberValidator {

    }

}