package com.yrc.common.service.jwt.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.yrc.common.pojo.common.ResponseDto
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

    override fun<T> encode(response: ResponseDto<T>,
                           expirationTime: Long,
                           issuer: String
    ): String {
        val time = response.time
        val hash = DigestUtils.md5Hex(mapper.writeValueAsString(response.data))
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
    override fun<T> decode(response: ResponseDto<T>, skewSeconds: Long): Jws<Claims> {
        val claimsJws = Jwts.parserBuilder()
            .setSigningKey(jwtKeyProvider.getPublicKey())
            .setAllowedClockSkewSeconds(skewSeconds)
            .build()
            .parseClaimsJws(response.jwt.jws)
        val hash = DigestUtils.md5Hex(mapper.writeValueAsString(response.data))
        val dataHash: String? = claimsJws.body[HASH_NAME] as String?
        if (dataHash == null) {
            TODO("抛出异常")
        } else if (!hash.equals(dataHash)){
            throw SignatureException("")
        }
        return claimsJws
    }
    fun getKeyPair(): KeyPair {
        return Keys.keyPairFor(SignatureAlgorithm.RS512)
    }

}