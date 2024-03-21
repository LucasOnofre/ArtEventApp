package com.onoffrice.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("artevents")
data class ArtEventEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean,
    @ColumnInfo("imageUrl")
    val imageUrl: String
)