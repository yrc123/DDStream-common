package com.yrc.common.pojo.ffmpeg

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.yrc.common.pojo.ffmpeg.input.FFmpegInputDto
import com.yrc.common.pojo.ffmpeg.proxy.FFmpegProxyDto

data class FFmpegConfigDto
@JsonCreator constructor(@JsonProperty("ffmpegProxy")var ffmpegProxy: FFmpegProxyDto?,
                         @JsonProperty("ffmpegInput")var ffmpegInput: FFmpegInputDto?,
                         @JsonProperty("ffmpegOutputList")var ffmpegOutputList: List<FFmpegOutputListItem>?) : FFmpegConfigItem{
    companion object {
        fun getDefaultConfig(inputUri: String, outputUri: String): FFmpegConfigDto{
            return FFmpegConfigDto(
                FFmpegProxyDto.getNoProxy(),
                FFmpegInputDto.getDefaultRateInput(inputUri),
                listOf(FFmpegOutputListItem.getDefaultConfig(outputUri)),
            )
        }
    }

    override fun toList(): List<String> {
        return ArrayList<String>().apply {
            addAll(ffmpegProxy!!.toList())
            addAll(ffmpegInput!!.toList())
            ffmpegOutputList!!.forEach{
                addAll(it.toList())
            }
        }
    }

}