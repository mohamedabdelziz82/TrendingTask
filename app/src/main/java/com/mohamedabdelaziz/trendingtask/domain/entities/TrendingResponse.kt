package com.mohamedabdelaziz.trendingtask.domain.entities

import androidx.room.PrimaryKey
import androidx.annotation.Keep
import androidx.room.Entity

@Keep
@Entity(tableName = "trend_table")
data class TrendingResponse (
    @PrimaryKey(autoGenerate = true)
    var id :Int,
    var author: String,
    var name: String,
    var avatar: String,
    var url: String,
    var description: String,
    var language: String,
    var languageColor: String,
    var stars: Int,
    var forks: Int,
    var currentPeriodStars: Int,
    var builtBy: List<BuiltBy>
)
{
    constructor() : this(0,"","","","","","","",0,0,0, emptyList())

    @Keep
    data class BuiltBy(
            val avatar: String,
            val href: String,
            val username: String
    )
}
