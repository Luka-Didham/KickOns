package com.cosc345.kickons

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//TODO("get this working without having to increment version number after schema change")
@Database(entities = [CardItem::class, DeckItem::class], version = 21, exportSchema = false)
abstract class CardDB : RoomDatabase() {
    abstract fun cardDAO(): CardDAO
    abstract fun deckDAO(): DeckDAO

    companion object {

        /**
         * Initializes the Room DB
         *
         * @param context the apps current context
         * @return an instance of the DB
         *
         * @see com.cosc345.kickons.AddPlayerAdapter
         * @see com.cosc345.kickons.PlayerViewHolder
         */
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
                //TODO remember to remove line below when not testing the db
                ).fallbackToDestructiveMigration().build();
                INSTANCE = instance
                return instance
            }
        }
    }
}