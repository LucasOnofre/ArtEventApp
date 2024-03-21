package com.onoffrice.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArtEventEntity::class], version = 1)
abstract class ArtEventDatabase : RoomDatabase() {
    abstract fun artworkDao(): ArtEventDao
}