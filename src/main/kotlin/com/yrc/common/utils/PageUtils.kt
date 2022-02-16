package com.yrc.common.utils

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO
import kotlin.reflect.KClass

object PageUtils {
    fun<U : Any, V : Any> IPage<U>.converterSearchPage(): Page<V> {
        return Page<V>(this.current, this.size, this.total, this.searchCount())
    }
    fun<U : Any, V : Any> IPage<U>.converterResultPage(
        resultClazz: KClass<V>,
        convertMethod: (U, KClass<V>) -> V
    ): PageDTO<V> {
        val resultPage = PageDTO<V>(this.current, this.size, this.total)
        resultPage.records = this.records.mapNotNull {
            convertMethod.invoke(it, resultClazz)
        }
        return resultPage
    }
}