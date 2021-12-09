package com.fakhry.katakerjaapps.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fakhry.katakerjaapps.core.data.source.local.entities.BookEntity

@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = false
)
abstract class KatakerjaDatabase : RoomDatabase() {

    abstract fun katakerjaDao(): KatakerjaDao
}