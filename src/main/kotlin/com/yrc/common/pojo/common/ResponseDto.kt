package com.yrc.common.pojo.common

import java.util.*

data class ResponseDto<T>(var code: Int? = null,
                          var message: String? = null,
                          var data: T? = null,
                          var time: Date? = null)

