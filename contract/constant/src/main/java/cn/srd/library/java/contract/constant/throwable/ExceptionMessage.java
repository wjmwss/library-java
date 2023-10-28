// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.throwable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * exception message template
 *
 * @author wjm
 * @since 2021-02-01 20:38
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessage {

    public static final String NULL_CHECKED_MESSAGE = "[Assertion failed] - the argument must be null";

    public static final String EMPTY_CHECKED_MESSAGE = "[Assertion failed] - the argument must be empty";

    public static final String BLANK_CHECKED_MESSAGE = "[Assertion failed] - the argument must be blank";

    public static final String TRUE_CHECKED_MESSAGE = "[Assertion failed] - the argument must be true";

    public static final String FALSE_CHECKED_MESSAGE = "[Assertion failed] - the argument must be false";

    public static final String POSITIVE_CHECKED_MESSAGE = "[Assertion failed] - the argument must > 0";

    public static final String EQUAL_CHECKED_MESSAGE = "[Assertion failed] - the compared arguments must be equal";

    public static final String NOT_NULL_CHECKED_MESSAGE = "[Assertion failed] - the argument must be not null";

    public static final String NOT_EMPTY_CHECKED_MESSAGE = "[Assertion failed] - the argument must be not empty";

    public static final String NOT_BLANK_CHECKED_MESSAGE = "[Assertion failed] - the argument must be not blank";

    public static final String NOT_POSITIVE_CHECKED_MESSAGE = "[Assertion failed] - the argument must <= 0";

    public static final String NOT_EQUAL_CHECKED_MESSAGE = "[Assertion failed] - the compared arguments must be not equal";

}
