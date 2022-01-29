package com.yrc.common.pojo.ffmpeg.encode

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.yrc.common.pojo.ffmpeg.FFmpegConfigItem
import com.yrc.common.pojo.ffmpeg.encode.FFmpegAudioCodecType.COPY

data class FFmpegAudioCodecDto
@JsonCreator constructor(@JsonProperty("codecType") var codecType: FFmpegAudioCodecType? = null,
                         @JsonProperty("bitrate") var bitrate: Int? = null) : FFmpegConfigItem{
    companion object {
        private const val AUDIO_CODEC_OPTION = "-c:a"
        private const val AUDIO_BITRATE_OPTION = "-b:a"
        fun getCopyCodec(): FFmpegAudioCodecDto{
            return FFmpegAudioCodecDto(COPY)
        }
    }

    override fun toList(): List<String> {
        return ArrayList<String>().apply {
            if (bitrate != null) {
                addAll(listOf(AUDIO_BITRATE_OPTION, bitrate.toString()))
            }
            addAll(listOf(AUDIO_CODEC_OPTION, codecType!!.codecName))
        }
    }
}
