package com.mohamedabdelaziz.trendingtask.domain.entities


import org.junit.Assert
import org.junit.Test
class TrendingResponseTest {
    @Test
    fun getAuthor()
       {
            val trendingResponse = TrendingResponse()
            trendingResponse.author = "Author"
            Assert.assertEquals("Author", trendingResponse.author)
        }

    @Test
    fun getName() {
            val trendingResponse = TrendingResponse()
            trendingResponse.name = "Name"
            Assert.assertEquals("Name", trendingResponse.name)
        }

    @Test
    fun getAvatar() {
            val trendingResponse = TrendingResponse()
            trendingResponse.avatar = "https://github.com/xingshaocheng.png"
            Assert.assertEquals("https://github.com/xingshaocheng.png", trendingResponse.avatar)
        }

    @Test
    fun getUrl() {
            val trendingResponse = TrendingResponse()
            trendingResponse.url = "https://github.com/xingshaocheng"
            Assert.assertEquals("https://github.com/xingshaocheng", trendingResponse.url)
        }

    @Test
    fun getDescription() {
            val trendingResponse = TrendingResponse()
            trendingResponse.description = "后端架构师技术图谱"
            Assert.assertEquals("后端架构师技术图谱", trendingResponse.description)
        }

    @Test
    fun getLanguage() {
            val trendingResponse = TrendingResponse()
            trendingResponse.language = "Go"
            Assert.assertEquals("Go", trendingResponse.language)
        }
}