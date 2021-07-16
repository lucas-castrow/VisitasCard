package com.castro.visitascard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VisitasCard::class], version = 1)
abstract class AppDatabase: RoomDatabase(){

    abstract fun visitasDAO() : VisitasCardDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "visitasCard_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}