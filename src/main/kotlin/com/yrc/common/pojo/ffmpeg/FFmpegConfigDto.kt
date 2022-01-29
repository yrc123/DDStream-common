package com.yrc.common.pojo.ffmpeg

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.yrc.common.pojo.ffmpeg.encode.FFmpegAudioCodecDto
import com.yrc.common.pojo.ffmpeg.encode.FFmpegVideoCodecDto
import com.yrc.common.pojo.ffmpeg.format.FFmpegFormatDto
import com.yrc.common.pojo.ffmpeg.input.FFmpegInputDto
import com.yrc.common.pojo.ffmpeg.output.FFmpegOutputDto
import com.yrc.common.pojo.ffmpeg.proxy.FFmpegProxyDto

data class FFmpegConfigDto
@JsonCreator constructor(@JsonProperty("ffmpegProxy")var ffmpegProxy: FFmpegProxyDto?,
                         @JsonProperty("ffmpegInput")var ffmpegInput: FFmpegInputDto?,
                         @JsonProperty("ffmpegAudioCodec") var ffmpegAudioCodec: FFmpegAudioCodecDto?,
                         @JsonProperty("ffmpegVideoCodec") var ffmpegVideoCodec: FFmpegVideoCodecDto?,
                         @JsonProperty("ffmpegFormat") var ffmpegFormat: FFmpegFormatDto?,
                         @JsonProperty("ffmpegOutput") var ffmpegOutput: FFmpegOutputDto?) : FFmpegConfigItem{
    companion object {
        fun getDefaultConfig(inputUri: String, outputUri: String): FFmpegConfigDto{
            return FFmpegConfigDto(
                FFmpegProxyDto.getNoProxy(),
                FFmpegInputDto.getDefaultRateInput(inputUri),
                FFmpegAudioCodecDto.getCopyCodec(),
                FFmpegVideoCodecDto.getCopyCodec(),
                FFmpegFormatDto.getDefaultHlsFormat(),
                FFmpegOutputDto.getDefaultOutput(outputUri))
        }
    }

    override fun toList(): List<String> {
        return ArrayList<String>().apply {
            addAll(ffmpegProxy!!.toList())
            addAll(ffmpegInput!!.toList())
            addAll(ffmpegAudioCodec!!.toList())
            addAll(ffmpegVideoCodec!!.toList())
            addAll(ffmpegFormat!!.toList())
            addAll(ffmpegOutput!!.toList())
        }
    }

}