package com.yrc.common.exception.common.impl

import com.yrc.common.exception.common.CommonException


open class SimpleException(
    private var code: Int,
    message: String,
    private var reason: String,
) : CommonException(message){
    constructor(code: Int, reason: String): this(code, reason, reason)
    override fun getCode(): Int {
        return code
    }

    override fun getReason(): String {
        return reason
    }
}