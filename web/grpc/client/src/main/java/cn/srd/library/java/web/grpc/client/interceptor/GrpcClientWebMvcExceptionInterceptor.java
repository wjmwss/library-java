// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.grpc.client.interceptor;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.spring.SpringInitializeConstant;
import cn.srd.library.java.contract.constant.web.HttpStatus;
import cn.srd.library.java.contract.model.protocol.WebResponse;
import cn.srd.library.java.contract.model.throwable.ClientException;
import cn.srd.library.java.contract.model.throwable.DataNotFoundException;
import cn.srd.library.java.contract.model.throwable.UnsupportedException;
import cn.srd.library.java.tool.spring.webmvc.interceptor.WebMvcExceptionInterceptor;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static cn.srd.library.java.contract.model.protocol.WebResponse.error;

/**
 * @author wjm
 * @since 2024-06-17 17:46
 */
@Slf4j
@Order(SpringInitializeConstant.LOWER_INITIALIZE_PRIORITY)
@RestControllerAdvice
public class GrpcClientWebMvcExceptionInterceptor extends WebMvcExceptionInterceptor {

    @Override
    protected String getModuleView() {
        return ModuleView.WEB_GRPC_SYSTEM;
    }

    @ExceptionHandler({StatusRuntimeException.class, StatusException.class})
    public WebResponse<Void> handleStatusException(HttpServletRequest httpServletRequest, StatusRuntimeException exception) {
        return switch (exception.getStatus().getCode()) {
            case Status.Code.UNIMPLEMENTED -> handleUnsupportedException(httpServletRequest, new UnsupportedException(exception.getStatus().getDescription()));
            case Status.Code.NOT_FOUND -> handleDataNotFoundException(httpServletRequest, new DataNotFoundException(exception.getStatus().getDescription()));
            case Status.Code.ABORTED -> handleClientException(httpServletRequest, new ClientException(exception.getStatus().getDescription()));
            default -> error(HttpStatus.INTERNAL_ERROR);
        };
    }

}