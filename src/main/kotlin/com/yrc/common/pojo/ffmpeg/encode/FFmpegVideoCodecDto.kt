package com.yrc.common.pojo.ffmpeg.encode

import com.yrc.common.pojo.ffmpeg.FFmpegConfigItem
import com.yrc.common.pojo.ffmpeg.encode.FFmpegVideoCodecType.COPY
import com.yrc.common.pojo.ffmpeg.encode.FFmpegVideoCodecType.UNKNOW_VIDEO_CODEC
import org.valiktor.functions.isNotIn
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class FFmpegVideoCodecDto(var codecType: FFmpegVideoCodecType?,
                               var bitrate: Int? = null,
                               var maxrate: Int? = null,
                               var fps: Int? = null) : FFmpegConfigItem{
    companion object {
        private const val VIDEO_CODEC_OPTION = "-c:v"
        private const val VIDEO_BITRATE_OPTION = "-b:v"
        private const val VIDEO_MAXRATE_OPTION = "-maxrate"
        private const val FPS_OPTION = "-r"

        fun getCopyCodec(): FFmpegVideoCodecDto{
            return FFmpegVideoCodecDto(COPY)
        }
    }

    init {
        validate(this) {
            validate(FFmpegVideoCodecDto::codecType)
                .isNotNull()
                .isNotIn(UNKNOW_VIDEO_CODEC)
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
