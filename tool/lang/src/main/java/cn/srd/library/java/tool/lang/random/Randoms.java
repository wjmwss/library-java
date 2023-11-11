// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.lang.random;

import cn.hutool.core.util.RandomUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * random toolkit
 *
 * @author wjm
 * @since 2021-04-26 16:48
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Randoms {

    /**
     * see {@link RandomUtil#randomNumber()}
     *
     * @return random number
     */
    public static char randomNumber() {
        return RandomUtil.randomNumber();
    }

    /**
     * see {@link RandomUtil#randomString(int)}
     *
     * @return random string
     */
    public static String randomString(int length) {
        return RandomUtil.randomString(length);
    }

}
