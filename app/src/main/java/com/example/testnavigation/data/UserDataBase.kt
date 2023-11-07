package com.example.testnavigation.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDataBase: RoomDatabase() {

    abstract val userDao : UserDao

    companion object{
        @Volatile
        private var INSTANCE: UserDataBase? = null

        fun getDataBase(context: Context): UserDataBase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                     instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDataBase::class.java,
                        "user_table"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}