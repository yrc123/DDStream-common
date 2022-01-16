package com.yrc.common.pojo.ffmpeg.input

import com.yrc.common.pojo.ffmpeg.FFmpegConfigItem
import org.valiktor.functions.isNotBlank
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class FFmpegInputDto(var inputUri: String? = null, var rate: Boolean? = true) : FFmpegConfigItem{
    companion object {
        private const val RATE_OPTION = "-re"
        private const val INPUT_URI_OPTION = "-i"
        fun getDefaultRateInput(inputUri: String): FFmpegInputDto {
            return FFmpegInputDto(inputUri)
        }
    }
    init {
        validate(this) {
            validate(FFmpegInputDto::inputUri).isNotBlank()
            validate(FFmpegInputDto::rate).isNotNull()
        }
    }
    override fun toList(): List<String> {
        return ArrayList<String>().apply {
            when(rate!!) {
                true -> add(RATE_OPTION)
                else -> {}
            }
            addAll(listOf(INPUT_URI_OPTION, inputUri!!))
        }
    }
}
