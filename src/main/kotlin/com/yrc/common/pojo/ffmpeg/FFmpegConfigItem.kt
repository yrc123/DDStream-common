package com.yrc.common.pojo.ffmpeg

fun interface FFmpegConfigItem {
    fun toList(): List<String>
}