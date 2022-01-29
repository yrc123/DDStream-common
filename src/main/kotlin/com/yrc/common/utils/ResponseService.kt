package com.yrc.common.utils

import com.yrc.common.pojo.common.JwtDto
import com.yrc.common.pojo.common.ResponseDto
import com.yrc.common.service.jwt.JwtService
import java.util.*

class ResponseService(val jwtService: JwtService, val issuer: String) {
    companion object {
        private const val SUCESS_CODE:Int = 200;
        private const val FAIL_CODE:Int = 500;
    }
    private fun<T> enableJwt(responseDto: ResponseDto<T>): ResponseDto<T> {
        val jws = jwtService.encode(
            responseDto,
            120 * 1000,
            this.issuer
        )
        return ResponseDto(responseDto.code,
            responseDto.message,
            responseDto.data,
            responseDto.time,
            JwtDto(true, jws))
    }
    fun<T> getResponse(data: T, enableJwt: Boolean, message: String, code: Int): ResponseDto<T> {
        val time = Date(System.currentTimeMillis())
        var responseDto = ResponseDto(code, message, data, time)
        if (enableJwt) {
            responseDto = enableJwt(responseDto)
        }
        return responseDto
    }
    fun<T> successResponse(data: T, enableJwt: Boolean = false, message: String = "success"): ResponseDto<T>{
        return getResponse(data, enableJwt, message, SUCESS_CODE)
    }
    fun<T> failResponse(data: T, enableJwt: Boolean = false, message: String = "fail"): ResponseDto<T>{
        return getResponse(data, enableJwt, message, FAIL_CODE)
    }
}