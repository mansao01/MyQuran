package com.example.myqurancompose.network.response

import com.google.gson.annotations.SerializedName

data class ListSurahVerseResponse(

	@field:SerializedName("ListSurahVerseResponse")
	val listSurahVerseResponse: List<ListSurahVerseResponseItem>
)

data class ListSurahVerseResponseItem(

	@field:SerializedName("ar")
	val ar: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("nomor")
	val nomor: String,

	@field:SerializedName("tr")
	val tr: String
)
