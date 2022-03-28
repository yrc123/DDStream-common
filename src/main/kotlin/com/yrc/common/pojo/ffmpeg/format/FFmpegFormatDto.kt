package com.yrc.common.pojo.ffmpeg.format

import com.yrc.common.pojo.ffmpeg.FFmpegConfigItem
import com.yrc.common.pojo.ffmpeg.format.FFmpegFormatType.HLS

data class FFmpegFormatDto(var formatType: FFmpegFormatType? = null,
                           var hlsInitTime: Int? = null,
                           var hlsTime: Int? = null,
                           var hlsListSize: Int? = null,
                           var hlsDeleteThreshold: Int? = null,
                           var ffmpegHlsFlags: List<FFmpegHlsFlag>? = null
) : FFmpegConfigItem{
    companion object {
        const val HLS_LIST_SIZE_ALL = 0
        private const val FORMAT_OPTION = "-f"
        private const val HLS_FLAGS_OPTION = "-hls_flags"
        private const val HLS_INIT_TIME_OPTION = "-hls_init_time"
        private const val HLS_TIME_OPTION = "-hls_time"
        private const val HLS_LIST_SIZE_OPTION = "-hls_list_size"
        private const val HLS_DELETE_THRESHOLD_OPTION = "-hls_delete_threshold"
        fun getDefaultHlsFormat(): FFmpegFormatDto{
            return FFmpegFormatDto(formatType = HLS,
                ffmpegHlsFlags =  listOf(FFmpegHlsFlag.DELETE_SEGMENTS))
        }
        fun getHlsFormat(hlsInitTime: Int? = null,
                         hlsTime: Int? = null,
                         hlsListSize: Int? = null,
                         hlsDeleteThreshold: Int? = null,
                         ffmpegHlsFlags: List<FFmpegHlsFlag>? = null): FFmpegFormatDto{
            return FFmpegFormatDto(HLS,
                hlsInitTime,
                hlsTime,
                hlsListSize,
                hlsDeleteThreshold,
                ffmpegHlsFlags)
        }
    }

    override fun toList(): List<String> {
        return ArrayList<String>().apply {
            when(formatType!!) {
                HLS -> {
                    if (hlsInitTime != null) {
                        addAll(listOf(HLS_INIT_TIME_OPTION, hlsInitTime.toString()))
                    }
                    if (hlsTime != null) {
                        addAll(listOf(HLS_TIME_OPTION, hlsTime.toString()))
                    }
                    if (hlsListSize != null) {
                        addAll(listOf(HLS_LIST_SIZE_OPTION, hlsListSize.toString()))
                    }
                    if (hlsDeleteThreshold != null) {
                        addAll(listOf(HLS_DELETE_THRESHOLD_OPTION, hlsDeleteThreshold.toString()))
                    }
                    if (!ffmpegHlsFlags.isNullOrEmpty()) {
                        add(HLS_FLAGS_OPTION)
                        ffmpegHlsFlags!!.forEach{
                            add(it.flagName)
                        }
                    }
                }
                else -> {}
            }
            addAll(listOf(FORMAT_OPTION, formatType!!.typeName))
        }
    }
}
