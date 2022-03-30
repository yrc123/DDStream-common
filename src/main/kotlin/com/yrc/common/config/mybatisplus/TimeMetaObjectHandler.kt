package com.yrc.common.config.mybatisplus

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import org.apache.ibatis.reflection.MetaObject
import java.time.LocalDateTime
import java.util.*
import java.util.function.Supplier

class TimeMetaObjectHandler : MetaObjectHandler{
    override fun insertFill(metaObject: MetaObject?) {
        this.strictInsertFill(metaObject, "createTime", { LocalDateTime.now() }, LocalDateTime::class.java)
        this.strictInsertFill(metaObject, "updateTime", { LocalDateTime.now() }, LocalDateTime::class.java)
    }

    override fun updateFill(metaObject: MetaObject?) {
        this.strictInsertFill(metaObject, "updateTime", { LocalDateTime.now() }, LocalDateTime::class.java)
    }

    override fun strictFillStrategy(metaObject: MetaObject, fieldName: String?, fieldVal: Supplier<*>): MetaObjectHandler? {
        val obj = fieldVal.get()
        if (Objects.nonNull(obj)) {
            metaObject.setValue(fieldName, obj)
        }
        return this
    }
}