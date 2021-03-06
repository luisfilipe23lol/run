package com.example.run.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.run.model.Corrida
import com.example.run.model.User

@Database(entities = [User::class, Corrida::class], version = 3, exportSchema = true, autoMigrations = [AutoMigration (from = 2, to = 3)])
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
   abstract fun corridaDao(): CorridaDao
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context:Context): UserDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}