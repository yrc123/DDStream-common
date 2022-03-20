package com.yrc.common.exception.common.impl

import com.yrc.common.exception.common.CommonException


open class SimpleException(
    private var code: Int,
    message: String,
    private var reason: String,
    private var extendCode: Int = -1,
) : CommonException(message){
    constructor(code: Int, reason: String, extendCode: Int = -1):
            this(code, reason, reason, extendCode)
    override fun getCode(): Int {
        return code
    }

    override fun getExtendCode(): Int {
        return extendCode
    }

    override fun getReason(): String {
        return reason
    }
}