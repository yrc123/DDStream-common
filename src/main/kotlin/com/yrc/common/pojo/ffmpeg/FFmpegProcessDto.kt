package com.yrc.common.pojo.ffmpeg

import com.fasterxml.jackson.annotation.JsonIgnore
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class FFmpegProcessDto(
    var id: String? = null,
    var name: String? = null,
    var config: FFmpegConfigDto? = null,
    var advancedConfig: List<String>? = null,
    var alive: Boolean? = null,
    @get:JsonIgnore
    var process: Process? = null
) {
    companion object {
        const val NAME_MAX = 64
        const val NAME_MIN = 1

        val commonValidator = { ffmpegProcessDto: FFmpegProcessDto ->
            validate(ffmpegProcessDto) {
                validate(FFmpegProcessDto::name)
                    .isNotNull()
                    .hasSize(NAME_MIN, NAME_MAX)
                if (ffmpegProcessDto.config == null) {
                    validate(FFmpegProcessDto::advancedConfig)
                        .isNotNull()
                }
            }
        }

    }
    fun getProcessInfo(): String{
        return process.toString()
    }
}