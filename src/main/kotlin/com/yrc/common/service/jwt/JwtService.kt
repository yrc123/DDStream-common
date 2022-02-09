package com.yrc.common.service.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.security.KeyPair
import java.util.*

interface JwtService {
    companion object {
        fun generateKeyPair(): KeyPair {
            return Keys.keyPairFor(SignatureAlgorithm.ES512)
        }
    }
    fun encode(data: Any, expirationTime: Long, issuer: String, time: Date): String
    fun decode(data: Any, jws: String, skewSeconds: Long): Jws<Claims>
}