data class Article(
    val author: String? = "unknown",
    val content: String? = "no content available",
    val description: String? = "no description",
    val publishedAt: String? = "Not known",
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?= null
)