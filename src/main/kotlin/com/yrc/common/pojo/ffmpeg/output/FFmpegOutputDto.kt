package com.yrc.common.pojo.ffmpeg.output

import com.yrc.common.pojo.ffmpeg.FFmpegConfigItem
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class FFmpegOutputDto(var outputUri: String? = null,
                           var overrideOutputFiles: Boolean? = true) : FFmpegConfigItem {
    companion object {
        private const val OVERRIDE_OPTION = "-y"
        fun getDefaultOutput(outputUri: String): FFmpegOutputDto{
            return FFmpegOutputDto(outputUri)
        }
    }
    init {
        validate(this) {
            validate(FFmpegOutputDto::outputUri).isNotBlank()
            validate(FFmpegOutputDto::overrideOutputFiles).isNotNull()
        }
    }
    override fun toList(): List<String> {
        return ArrayList<String>().apply {
            when(overrideOutputFiles!!) {
                true -> add(OVERRIDE_OPTION)
                else -> {}
            }
            add(outputUri!!)
        }
    }
}
