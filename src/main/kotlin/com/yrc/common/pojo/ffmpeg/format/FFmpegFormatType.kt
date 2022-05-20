package com.yrc.common.pojo.ffmpeg.format

enum class FFmpegFormatType(val typeName: String) {
    NONE("none"),
    FLV("flv"),
    HLS("hls"),
    DASH("dash"),
    MP4("mp4"),
}