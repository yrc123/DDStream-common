package com.yrc.common.pojo.ffmpeg.proxy

import com.yrc.common.pojo.ffmpeg.FFmpegConfigItem
import com.yrc.common.pojo.ffmpeg.proxy.FFmpegProxyType.*
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class FFmpegProxyDto(var proxyType: FFmpegProxyType? = UNKNOW_PROXY,
                          var httpUrl: String? = null) : FFmpegConfigItem{
    companion object {
        private const val HTTP_PROXY_OPTION = "-http_proxy"
        fun getNoProxy(): FFmpegProxyDto {
            return FFmpegProxyDto(NO_PROXY)
        }
    }

    init {
        validate(this) {
            validate(FFmpegProxyDto::proxyType).isNotNull()
            when(proxyType) {
                HTTP_PROXY ->
                    validate(FFmpegProxyDto::httpUrl).isNotBlank()
                else -> {}
            }
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
