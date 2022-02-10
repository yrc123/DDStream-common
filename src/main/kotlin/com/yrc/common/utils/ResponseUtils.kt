package com.yrc.common.utils

import com.yrc.common.exception.common.CommonException
import com.yrc.common.pojo.common.ResponseDto
import java.util.*

object ResponseUtils {
    private const val SUCESS_CODE:Int = 200;
    private const val FAIL_CODE:Int = 500;

    fun <T> getResponse(data: T, message: String, code: Int): ResponseDto<T> {
        val time = Date(System.currentTimeMillis())
        return ResponseDto(code, message, data, time)
    }
    fun<T> successResponse(data: T, message: String = "success"): ResponseDto<T>{
        return getResponse(data, message, SUCESS_CODE)
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
        return getResponse(message, message, SUCESS_CODE)
    }
    data class ExceptionData(val code: Int, val message: String, val reason: String){
        constructor(e: CommonException) : this(e.getCode(), e.message ?: "", e.getReason())
    }

}