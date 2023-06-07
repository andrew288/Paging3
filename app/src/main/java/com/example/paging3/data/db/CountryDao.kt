package com.example.paging3.data.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paging3.data.model.Country

interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<Country>)

    @Query("SELECT * FROM country ORDER BY id ASC")
    suspend fun getPagedList(): List<Country>

    @Query("DELETE FROM country")
    suspend fun  clearAll()
}