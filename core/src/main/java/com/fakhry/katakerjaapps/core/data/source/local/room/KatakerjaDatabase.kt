package com.fakhry.katakerjaapps.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
abstract class KatakerjaDatabase : RoomDatabase() {

    abstract fun katakerjaDao(): KatakerjaDao
}