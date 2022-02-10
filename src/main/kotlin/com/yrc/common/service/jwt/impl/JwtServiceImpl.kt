package com.yrc.common.service.jwt.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.yrc.common.exception.common.ParametersMissingExceptionFactory
import com.yrc.common.service.jwt.JwtKeyProvider
import com.yrc.common.service.jwt.JwtService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.apache.commons.codec.digest.DigestUtils
import java.security.KeyPair
import java.util.*

class JwtServiceImpl(
    private val jwtKeyProvider: JwtKeyProvider,
    private val mapper: ObjectMapper) : JwtService{
    companion object {
        const val HASH_NAME = "hash"
    }

    override fun encode(data: Any,
                        expirationTime: Long,
                        issuer: String,
                        time: Date
    ): String {
        val hash = DigestUtils.md5Hex(mapper.writeValueAsString(data))
        val jws = Jwts.builder()
            .setExpiration(Date(time.time + expirationTime))
            .setIssuer(issuer)
            .setIssuedAt(time)
            .setNotBefore(time)
            .setClaims(mapOf(HASH_NAME to hash))
            .signWith(jwtKeyProvider.getPrivateKey())
            .compact()
        return jws

    }


    override fun decode(data: Any, jws: String, skewSeconds: Long): Jws<Claims> {
        val claimsJws = Jwts.parserBuilder()
            .setSigningKey(jwtKeyProvider.getPublicKey())
            .setAllowedClockSkewSeconds(skewSeconds)
            .build()
            .parseClaimsJws(jws)
        val hash = DigestUtils.md5Hex(mapper.writeValueAsString(data))
        val dataHash: String? = claimsJws.body[HASH_NAME] as String?
        if (dataHash == null) {
            throw ParametersMissingExceptionFactory
                .getHeaderParametersMissingException(listOf(HASH_NAME))
        } else if (!hash.equals(dataHash)){
            throw SignatureException("data hash not equals")
        }
        return claimsJws
    }
    fun getKeyPair(): KeyPair {
        return Keys.keyPairFor(SignatureAlgorithm.RS512)
    }

}