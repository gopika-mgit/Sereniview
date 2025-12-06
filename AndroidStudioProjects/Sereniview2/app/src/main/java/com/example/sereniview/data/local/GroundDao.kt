package com.example.sereniview.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GroundDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: GroundEntry)

    @Query("SELECT * FROM ground_entries ORDER BY timestamp DESC")
    fun getAllEntries(): Flow<List<GroundEntry>>

    @Query("DELETE FROM ground_entries WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM ground_entries")
    suspend fun clearAll()
}