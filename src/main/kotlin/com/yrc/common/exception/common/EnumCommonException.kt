package com.yrc.common.exception.common

import com.yrc.common.exception.common.impl.SimpleException

enum class EnumCommonException(private val exception: SimpleException) {
    UNKNOWN_METHOD(SimpleException(400, "unknown method")),
    NOT_SUPPORT_METHOD(SimpleException(400, "not support method")),
    ;
    fun build(): SimpleException {
        return exception
    }

}