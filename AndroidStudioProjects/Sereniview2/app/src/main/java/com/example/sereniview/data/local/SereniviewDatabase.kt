package com.example.sereniview.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [GroundEntry::class],
    version = 1,
    exportSchema = false
)
abstract class SereniviewDatabase : RoomDatabase() {

    abstract fun groundDao(): GroundDao

    companion object {
        @Volatile
        private var INSTANCE: SereniviewDatabase? = null

        fun getDatabase(context: Context): SereniviewDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SereniviewDatabase::class.java,
                    SereniviewDatabase::class.java.simpleName
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}