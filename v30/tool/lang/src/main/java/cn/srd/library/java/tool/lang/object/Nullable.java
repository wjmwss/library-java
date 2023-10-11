// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.object;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * an object to define what is null
 *
 * @author wjm
 * @since 2023-06-14 08:49
 */
public interface Nullable {

    /**
     * return true if null
     *
     * @return return true if null
     */
    @JsonIgnore
    boolean isNull();

    /**
     * return true if not null
     *
     * @return return true if not null
     */
    @JsonIgnore
    default boolean isNotNull() {
        return !isNull();
    }

}
