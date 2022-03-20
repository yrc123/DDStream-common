package com.yrc.common.exception.common

abstract class CommonException(message: String) : Exception(message){
    abstract fun getCode(): Int
    abstract fun getExtendCode(): Int
    abstract fun getReason(): String
}