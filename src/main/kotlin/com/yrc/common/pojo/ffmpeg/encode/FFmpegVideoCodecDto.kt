package com.yrc.common.pojo.ffmpeg.encode

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.yrc.common.pojo.ffmpeg.FFmpegConfigItem
import com.yrc.common.pojo.ffmpeg.encode.FFmpegVideoCodecType.COPY

data class FFmpegVideoCodecDto
@JsonCreator constructor(@JsonProperty("codecType") var codecType: FFmpegVideoCodecType?,
                         @JsonProperty("bitrate") var bitrate: Int? = null,
                         @JsonProperty("maxrate") var maxrate: Int? = null,
                         @JsonProperty("fps") var fps: Int? = null) : FFmpegConfigItem{
    companion object {
        private const val VIDEO_CODEC_OPTION = "-c:v"
        private const val VIDEO_BITRATE_OPTION = "-b:v"
        private const val VIDEO_MAXRATE_OPTION = "-maxrate"
        private const val FPS_OPTION = "-r"

        fun getCopyCodec(): FFmpegVideoCodecDto{
            return FFmpegVideoCodecDto(COPY)
        }
    }


    override fun toList(): List<String> {
        return ArrayList<String>().apply {
            if (bitrate != null) {
                addAll(listOf(VIDEO_BITRATE_OPTION, bitrate.toString()))
            }
            if (maxrate != null) {
                addAll(listOf(VIDEO_MAXRATE_OPTION, maxrate.toString()))
            }
            if (fps != null) {
                addAll(listOf(FPS_OPTION, fps.toString()))
            }
            addAll(listOf(VIDEO_CODEC_OPTION, codecType!!.codecName))
        }
    }
}
