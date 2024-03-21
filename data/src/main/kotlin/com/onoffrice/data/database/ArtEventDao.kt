package com.onoffrice.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArtEventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavorite(artwork:ArtEventEntity)

    @Query("SELECT * FROM `artevents`")
    fun getFavorites(): List<ArtEventEntity>

    @Delete
    fun removeFavorite(artwork:ArtEventEntity)
}