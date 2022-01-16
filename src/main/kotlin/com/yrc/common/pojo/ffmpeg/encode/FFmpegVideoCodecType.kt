package com.yrc.common.pojo.ffmpeg.encode

enum class FFmpegVideoCodecType(val codecName: String) {
    UNKNOW_VIDEO_CODEC("unknow"),
    COPY("copy"),
    H264("h264"),
    MPEG4("mpeg4")
}