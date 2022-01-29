package com.yrc.common.pojo.common

import org.valiktor.functions.isNotNull
import org.valiktor.validate


data class JwtDto(var enable: Boolean? = false,
                  var jws: String? = null) {
    companion object {
        fun disableJwt() = JwtDto(false, null)
    }
    init {
        validate(this) {
            validate(JwtDto::enable).isNotNull()
            if (enable == true) {
                validate(JwtDto::jws).isNotNull()
            }
        }
    }
}
