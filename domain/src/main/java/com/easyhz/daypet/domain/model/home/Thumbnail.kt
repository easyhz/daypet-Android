package com.easyhz.daypet.domain.model.home


data class Thumbnail(
    val month: HashSet<String>,
    val thumbnailUrls: HashMap<String, String>
)