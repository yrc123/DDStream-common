package com.yrc.common.exception.common.impl

import com.yrc.common.exception.common.CommonException

open class ParametersException(
    private val code: Int,
    private val part: String,
    private val keyValuePairs: List<Pair<String, Any?>>,
    message: String,
    private val extendCode: Int = -1,
) : CommonException(message){

     private val _reason by lazy {
        val sb = StringBuffer().append("[")
        keyValuePairs.forEach {
            sb.append("[key: ${it.first}, value: ${it.second}]")
        }
        sb.append("]")
        sb.toString()
    }

    override fun getReason(): String {
        return _reason
    }

    override fun getCode(): Int {
        return code
    }

    override fun getExtendCode(): Int {
        return extendCode
    }

}