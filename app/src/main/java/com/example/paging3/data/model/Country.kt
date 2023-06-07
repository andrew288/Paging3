package com.example.paging3.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class Country(
    @PrimaryKey
    val id: Int = 0,
    var name_en: String,
    var name_es: String,
    var continent_en: String,
    var continent_es: String,
    var capital_en: String,
    var capital_es: String,
    var dial_code: String,
    var code_2: String,
    var code_3: String,
    var tld: String,
    var km2: String
)
