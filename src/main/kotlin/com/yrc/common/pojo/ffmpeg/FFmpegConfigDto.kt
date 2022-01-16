package com.yrc.common.pojo.ffmpeg

import com.yrc.common.pojo.ffmpeg.encode.FFmpegAudioCodecDto
import com.yrc.common.pojo.ffmpeg.encode.FFmpegVideoCodecDto
import com.yrc.common.pojo.ffmpeg.format.FFmpegFormatDto
import com.yrc.common.pojo.ffmpeg.input.FFmpegInputDto
import com.yrc.common.pojo.ffmpeg.output.FFmpegOutputDto
import com.yrc.common.pojo.ffmpeg.proxy.FFmpegProxyDto
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class FFmpegConfigDto (var ffmpegProxy: FFmpegProxyDto?,
                            var ffmpegInput: FFmpegInputDto?,
                            var ffmpegAudioCodec: FFmpegAudioCodecDto?,
                            var ffmpegVideoCodec: FFmpegVideoCodecDto?,
                            var ffmpegFormat: FFmpegFormatDto?,
                            var ffmpegOutput: FFmpegOutputDto?) : FFmpegConfigItem{
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
    init {
        validate(this) {
            validate(FFmpegConfigDto::ffmpegProxy).isNotNull()
            validate(FFmpegConfigDto::ffmpegInput).isNotNull()
            validate(FFmpegConfigDto::ffmpegAudioCodec).isNotNull()
            validate(FFmpegConfigDto::ffmpegVideoCodec).isNotNull()
            validate(FFmpegConfigDto::ffmpegFormat).isNotNull()
            validate(FFmpegConfigDto::ffmpegOutput).isNotNull()
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