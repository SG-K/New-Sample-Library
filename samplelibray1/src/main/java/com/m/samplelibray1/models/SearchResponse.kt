package com.m.samplelibray1.models

import com.squareup.moshi.Json

data class SearchResponse (
    @field:Json(name = "Error")
    val Error: String,
    @field:Json(name = "list")
    val list: List<SearchDetails>
)