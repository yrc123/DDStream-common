package com.yrc.common.pojo.common

import java.util.*

data class ResponseDto<T>(var code: Int,
                          var message: String,
                          var data: T,
                          var time: Date) {

}

