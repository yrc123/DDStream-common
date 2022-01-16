package com.yrc.common.pojo.ffmpeg.proxy

enum class FFmpegProxyType(val proxyName: String) {
    UNKNOW_PROXY("unknowProxy"),
    NO_PROXY("noProxy"),
    HTTP_PROXY("httpProxy")
}