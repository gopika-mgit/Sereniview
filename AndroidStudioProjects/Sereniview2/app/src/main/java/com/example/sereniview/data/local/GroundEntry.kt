package com.example.sereniview.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ground_entries")
data class GroundEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val worry: String,
    val response: String,
    val timestamp: Long
)