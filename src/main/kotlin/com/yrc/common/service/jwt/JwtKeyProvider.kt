package com.yrc.common.service.jwt

import java.security.PrivateKey
import java.security.PublicKey

interface JwtKeyProvider {
    fun getPrivateKey(): PrivateKey
    fun getPublicKey(): PublicKey
}