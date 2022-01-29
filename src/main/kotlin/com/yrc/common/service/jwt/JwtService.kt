package com.yrc.common.service.jwt

import com.yrc.common.pojo.common.ResponseDto
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws

interface JwtService {
    fun<T> encode(response: ResponseDto<T>, expirationTime: Long, issuer: String): String

    fun<T> decode(response: ResponseDto<T>, skewSeconds: Long): Jws<Claims>
}