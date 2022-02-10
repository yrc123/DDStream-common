package com.yrc.common.utils

import com.yrc.common.exception.ip.IpNotGetExcetption
import org.apache.commons.logging.LogFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

object IpUtils {
    var logger = LogFactory.getLog(IpUtils::class.java)
    private val ipApiList = listOf(
        "https://checkip.amazonaws.com",
        "https://ip.42.pl/raw"
    )

    fun getPublicIpAddress(): String{
        for (i in 0..ipApiList.size) {
            logger.info("开始通过 ${ipApiList[i]} 获取公网ip")
            val ipAddress = URL(ipApiList[i]).openStream().use {
                BufferedReader(InputStreamReader(it)).readLine()
            }
            if (!ipAddress.isNullOrBlank()) {
                logger.info("通过 ${ipApiList[i]} 获取公网ip为: $ipAddress")
                return ipAddress
            }
            logger.warn("通过 ${ipApiList[i]} 获取公网ip失败")
        }
        throw IpNotGetExcetption()
    }
}