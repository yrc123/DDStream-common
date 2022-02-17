package com.yrc.common.exception.valiktor

import com.yrc.common.exception.common.impl.ParametersException
import org.valiktor.ConstraintViolationException

class ValidateException(
    code: Int,
    private val e: ConstraintViolationException,
    message: String = "参数校验错误"
): ParametersException(
    code,
    "body",
    e.constraintViolations.map {
        it.property to it.constraint
    },
    message
)