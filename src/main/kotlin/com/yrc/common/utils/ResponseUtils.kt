package com.yrc.common.utils

import com.yrc.common.pojo.common.ResponseDto
import java.util.*

object ResponseUtils {
    private const val SUCESS_CODE:Int = 200;
    private const val FAIL_CODE:Int = 500;

    fun <T> getResponse(data: T, enableJwt: Boolean, message: String, code: Int): ResponseDto<T> {
        val time = Date(System.currentTimeMillis())
        return ResponseDto(code, message, data, time)
    }
    fun<T> successResponse(data: T, enableJwt: Boolean = false, message: String = "success"): ResponseDto<T>{
        return getResponse(data, enableJwt, message, SUCESS_CODE)
    }
    fun<T> failResponse(data: T, enableJwt: Boolean = false, message: String = "fail"): ResponseDto<T>{
        return getResponse(data, enableJwt, message, FAIL_CODE)
    }
    fun successStringResponse(enableJwt: Boolean = false, message: String = "success"): ResponseDto<String>{
        return getResponse(message, enableJwt, message, SUCESS_CODE)
    }
}