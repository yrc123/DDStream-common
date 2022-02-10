package com.yrc.common.exception.common

import com.yrc.common.exception.common.impl.ParametersException

object ParametersExceptionFacotry {
    fun duplicateException(keyValuePairs: List<Pair<String, Any?>>): ParametersException {
        return getException(keyValuePairs, "parameters duplicate")
    }

    private fun getException(keyValuePairs: List<Pair<String, Any?>>,
                             message: String): ParametersException {
        return ParametersException(400, "all", keyValuePairs, message)
    }
}