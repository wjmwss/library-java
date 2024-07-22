// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.contract.constant.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wjm
 * @since 2024-07-22 15:49
 */
@Getter
@AllArgsConstructor
public enum PictureMimeType {

    BMP("image/bmp", FileExtensionType.BMP),
    GIF("image/gif", FileExtensionType.GIF),
    JPEG("image/jpeg", FileExtensionType.JPEG),
    PNG("image/png", FileExtensionType.PNG),
    SVG("image/svg+xml", FileExtensionType.SVG),

    ;

    private final String value;

    private final FileExtensionType extensionType;

}