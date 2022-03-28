package com.yrc.common.pojo.ffmpeg

import com.fasterxml.jackson.annotation.JsonProperty
import com.yrc.common.pojo.ffmpeg.encode.FFmpegAudioCodecDto
import com.yrc.common.pojo.ffmpeg.encode.FFmpegVideoCodecDto
import com.yrc.common.pojo.ffmpeg.format.FFmpegFormatDto
import com.yrc.common.pojo.ffmpeg.output.FFmpegOutputDto

data class FFmpegOutputListItem(
    @JsonProperty("ffmpegAudioCodec") var ffmpegAudioCodec: FFmpegAudioCodecDto?,
    @JsonProperty("ffmpegVideoCodec") var ffmpegVideoCodec: FFmpegVideoCodecDto?,
    @JsonProperty("ffmpegFormat") var ffmpegFormat: FFmpegFormatDto?,
    @JsonProperty("ffmpegOutput") var ffmpegOutput: FFmpegOutputDto?
) : FFmpegConfigItem {
    companion object {
        fun getDefaultConfig(outputUri: String): FFmpegOutputListItem {
            return FFmpegOutputListItem(
                FFmpegAudioCodecDto.getCopyCodec(),
                FFmpegVideoCodecDto.getCopyCodec(),
                FFmpegFormatDto.getDefaultHlsFormat(),
                FFmpegOutputDto.getDefaultOutput(outputUri)
            )
        }
    }
    override fun toList(): List<String> {
        return ArrayList<String>().apply {
            addAll(ffmpegAudioCodec!!.toList())
            addAll(ffmpegVideoCodec!!.toList())
            addAll(ffmpegFormat!!.toList())
            addAll(ffmpegOutput!!.toList())
        }
    }

}