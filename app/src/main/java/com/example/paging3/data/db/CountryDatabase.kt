package com.example.paging3.data.db

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.paging3.data.model.Country
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class CountryDatabase: RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object{
        private var INSTANCE: CountryDatabase? = null

        fun getInstance(context: Context): CountryDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    CountryDatabase::class.java,
                    "country_database"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        INSTANCE?.let { database ->
                            CoroutineScope(Dispatchers.IO).launch {
                                val assetManager = context.assets
                                Log.d("RESULT OPEN FILE", "BEFORE")
                                val inputStream = assetManager.open("countries.json")
                                val jsonString = inputStream.bufferedReader().use { it.readText() }
                                val countries = Gson().fromJson(jsonString, Array<Country>::class.java).toList()
                                Log.d("RESULT OPEN FILE", countries.get(0).capital_en)
                                val countryDao = INSTANCE?.countryDao()
                                countryDao?.insertAll(countries)
                            }
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }
    }
}