package com.yrc.common.pojo.common

import java.time.LocalDateTime

abstract class AbstractDto (
    var createTime: LocalDateTime? = null,
    var updateTime: LocalDateTime? = null,
)