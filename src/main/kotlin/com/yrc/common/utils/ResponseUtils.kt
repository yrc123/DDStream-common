package com.yrc.common.utils

import com.yrc.common.exception.common.CommonException
import com.yrc.common.pojo.common.ResponseDto
import java.util.*

object ResponseUtils {
    private const val SUCCESS_CODE:Int = 200;
    private const val FAIL_CODE:Int = 400;

    fun <T> getResponse(data: T, message: String, code: Int): ResponseDto<T> {
        val time = Date(System.currentTimeMillis())
        return ResponseDto(code, message, data, time)
    }
    fun<T> successResponse(data: T, message: String = "success"): ResponseDto<T>{
        return getResponse(data, message, SUCCESS_CODE)
    }
    fun<T> failResponse(data: T, message: String = "fail"): ResponseDto<T>{
        return getResponse(data, message, FAIL_CODE)
    }
    fun exceptionResponse(commonException: CommonException): ResponseDto<ExceptionData> {
        return getResponse(ExceptionData(commonException),
            commonException.message ?: "",
            commonException.getCode())
    }
    fun successStringResponse(message: String = "success"): ResponseDto<String>{
        return getResponse(message, message, SUCCESS_CODE)
    }
    fun failStringResponse(message: String = "fail"): ResponseDto<String>{
        return getResponse(message, message, FAIL_CODE)
    }
    data class ExceptionData(var code: Int? = null,
                             var message: String? = null,
                             var reason: String? = null){
        constructor(e: CommonException) : this(e.getCode(), e.message ?: "", e.getReason())
    }

}