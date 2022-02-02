package com.yrc.common.service.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import java.util.*

interface JwtService {
    fun encode(data: Any, expirationTime: Long, issuer: String, time: Date): String
    fun decode(data: Any, jws: String, skewSeconds: Long): Jws<Claims>
}