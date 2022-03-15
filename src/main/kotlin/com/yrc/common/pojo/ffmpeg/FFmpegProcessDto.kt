package com.yrc.common.pojo.ffmpeg

import com.fasterxml.jackson.annotation.JsonIgnore

data class FFmpegProcessDto(val id: String,
                            val config: FFmpegConfigDto?,
                            val advancedConfig: List<String>?,
                            var alive: Boolean,
                            @get:JsonIgnore
                            var process: Process?
) {
    companion object
    fun getProcessInfo(): String{
        return process.toString()
    }
}