package com.yrc.common.exception.common

import com.yrc.common.exception.common.impl.ParametersException

object ParametersMissingExceptionFactory {
    fun getHeaderParametersMissingException(keys: List<String>): ParametersException {
        return getException("header", keys)
    }
    fun getBodyParametersMissingException(keys: List<String>): ParametersException {
        return getException("body", keys)
    }
    fun getUriParametersMissingException(keys: List<String>): ParametersException {
        return getException("uri", keys)
    }
    private fun getException(path: String, key: List<String>): ParametersException{
        val keyValuePairs = key.map { Pair(it, null) }
        return ParametersException(400, path, keyValuePairs, "parameters missing")
    }
}