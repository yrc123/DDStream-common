package com.yrc.common.pojo.ffmpeg.encode

enum class FFmpegAudioCodecType(val codecName: String) {
    UNKNOW_AUDIO_CODEC("unknow"),
    COPY("copy"),
    ACC("aac"),
    APTX("aptx"),
    FLAC("flac"),
    MP3("mp3")
}