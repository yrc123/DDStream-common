package com.yrc.common.config.mybatisplus

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import org.apache.ibatis.reflection.MetaObject
import java.time.LocalDateTime

class TimeMetaObjectHandler : MetaObjectHandler{
    override fun insertFill(metaObject: MetaObject?) {
        this.strictInsertFill(metaObject, "createTime", { LocalDateTime.now() }, LocalDateTime::class.java)
        this.strictInsertFill(metaObject, "updateTime", { LocalDateTime.now() }, LocalDateTime::class.java)
    }

    override fun updateFill(metaObject: MetaObject?) {
        this.strictInsertFill(metaObject, "updateTime", { LocalDateTime.now() }, LocalDateTime::class.java)
    }
}