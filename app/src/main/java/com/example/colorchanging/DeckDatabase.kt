package com.example.colorchanging
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DeckItem::class], version = 1, exportSchema = false)

abstract class DeckDatabase : RoomDatabase() {
    abstract fun deckDao(): DeckDAO

    companion object {
        @Volatile
        private var INSTANCE: DeckDatabase? = null
        fun getDatabase(context: Context): DeckDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DeckDatabase::class.java,
                    "deck_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}