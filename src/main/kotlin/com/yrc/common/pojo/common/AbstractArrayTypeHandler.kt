package com.yrc.common.pojo.common

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException

abstract class AbstractArrayTypeHandler<T>(
    private val typeReference: TypeReference<T>,
    private val objectMapper: ObjectMapper
) : AbstractJsonTypeHandler<T>(){
    override fun parse(json: String?): T {
        return try {
            objectMapper.readValue(json, typeReference)
        } catch (var3: IOException) {
            throw RuntimeException(var3)
        }
    }

    override fun toJson(obj: T?): String {
        return try {
            objectMapper.writeValueAsString(obj)
        } catch (var3: JsonProcessingException) {
            throw RuntimeException(var3)
        }
    }
}