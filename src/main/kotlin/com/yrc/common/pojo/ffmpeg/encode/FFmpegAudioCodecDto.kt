package com.yrc.common.pojo.ffmpeg.encode

import com.yrc.common.pojo.ffmpeg.FFmpegConfigItem
import com.yrc.common.pojo.ffmpeg.encode.FFmpegAudioCodecType.COPY
import com.yrc.common.pojo.ffmpeg.encode.FFmpegAudioCodecType.UNKNOW_AUDIO_CODEC
import org.valiktor.functions.isNotIn
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class FFmpegAudioCodecDto(var codecType: FFmpegAudioCodecType? = null,
                               var bitrate: Int? = null) : FFmpegConfigItem{
    companion object {
        private const val AUDIO_CODEC_OPTION = "-c:a"
        private const val AUDIO_BITRATE_OPTION = "-b:a"
        fun getCopyCodec(): FFmpegAudioCodecDto{
            return FFmpegAudioCodecDto(COPY)
        }
    }
    init {
        validate(this) {
            validate(FFmpegAudioCodecDto::codecType)
                .isNotNull()
                .isNotIn(UNKNOW_AUDIO_CODEC)
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
