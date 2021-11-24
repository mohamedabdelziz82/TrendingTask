package com.mohamedabdelaziz.trendingtask.domain.entities

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class Converters {
    @TypeConverter
    fun fromBuiltByToGson(builtByList: List<TrendingResponse.BuiltBy>): String {
        return Gson().toJson(builtByList)
    }

    @TypeConverter
    fun fromGsonToBuiltBy(builtByString: String?): List<TrendingResponse.BuiltBy> {
        val listType = object : TypeToken<ArrayList<TrendingResponse.BuiltBy>>() {}.type
        return Gson().fromJson(builtByString, listType)
    }
}