package com.yrc.common.pojo.ffmpeg.proxy

import com.yrc.common.pojo.ffmpeg.FFmpegConfigItem
import com.yrc.common.pojo.ffmpeg.proxy.FFmpegProxyType.*

data class FFmpegProxyDto(var proxyType: FFmpegProxyType? = UNKNOW_PROXY,
                          var httpUrl: String? = null) : FFmpegConfigItem{
    companion object {
        private const val HTTP_PROXY_OPTION = "-http_proxy"
        fun getNoProxy(): FFmpegProxyDto {
            return FFmpegProxyDto(NO_PROXY)
        }
    }

    override fun toList(): List<String> {
        return ArrayList<String>().apply {
            when(proxyType!!) {
                HTTP_PROXY ->
                    addAll(listOf(HTTP_PROXY_OPTION, httpUrl!!))
                else -> {}
            }
        }
    }

}
