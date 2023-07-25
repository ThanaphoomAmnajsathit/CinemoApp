package com.example.cinemoapp.models

import com.google.gson.annotations.SerializedName

data class MovieAvaiableModel(

    @field:SerializedName("movies")
    val movies: List<MoviesItem>? = null
)

data class MoviesItem(

    @field:SerializedName("poster_ori")
    val posterOri: String? = null,

    @field:SerializedName("sneak_date")
    val sneakDate: String? = null,

    @field:SerializedName("synopsis_en")
    val synopsisEn: String? = null,

    @field:SerializedName("tr_sd")
    val trSd: String? = null,

    @field:SerializedName("director")
    val director: String? = null,

    @field:SerializedName("rating")
    val rating: String? = null,

    @field:SerializedName("date_update")
    val dateUpdate: String? = null,

    @field:SerializedName("poster_url")
    val posterUrl: String? = null,

    @field:SerializedName("tr_ios")
    val trIos: String? = null,

    @field:SerializedName("tr_hd")
    val trHd: String? = null,

    @field:SerializedName("synopsis_th")
    val synopsisTh: String? = null,

    @field:SerializedName("duration")
    val duration: Int? = null,

    @field:SerializedName("actor")
    val actor: String? = null,

    @field:SerializedName("trailer")
    val trailer: String? = null,

    @field:SerializedName("title_th")
    val titleTh: String? = null,

    @field:SerializedName("trailer_cms_id")
    val trailerCmsId: String? = null,

    @field:SerializedName("rating_id")
    val ratingId: Int? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("tr_mp4")
    val trMp4: String? = null,

    @field:SerializedName("genre")
    val genre: String? = null,

    @field:SerializedName("title_en")
    val titleEn: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("trailer_ivx_key")
    val trailerIvxKey: String? = null,

    @field:SerializedName("show_buyticket")
    val showBuyticket: String? = null,

    @field:SerializedName("advance_ticket")
    val advanceTicket: String? = null,

    @field:SerializedName("now_showing")
    val nowShowing: String? = null,

    @field:SerializedName("priority")
    val priority: String? = null,

    @field:SerializedName("movieCode")
    val movieCode: List<String?>? = null
)
