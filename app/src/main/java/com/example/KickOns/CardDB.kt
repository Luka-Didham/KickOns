package com.example.KickOns

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//TODO("get this working wihtout having to incremeant version number after schema change")
@Database(entities = [CardItem::class], version = 3)
abstract class CardDB : RoomDatabase() {
    abstract fun cardDAO(): CardDAO

    companion object {
        @Volatile
        private var INSTANCE: CardDB? = null
        fun getDatabase(context: Context): CardDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDB::class.java,
                    "card_database"
                //TODO `remember to remove line bellow when not testing the db
                ).fallbackToDestructiveMigration().build();
                INSTANCE = instance
                return instance
            }
        }
    }
}