package com.yrc.common.utils

import org.apache.commons.logging.LogFactory

class JwtUtil(var privateKey: String) {
    var logger = LogFactory.getLog(JwtUtil::class.java)

}