package com.arinspect.proficiencyexercise.data.datasources.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created Date: 27-05-2020
 * Purpose: Class to handle the data which we recieved from server for about Canada.
 */
data class AboutCanadaResponse(
    @Expose @SerializedName("rows") val rows: List<AboutCanadaEntity>,
    @Expose @SerializedName("title") val title: String? = ""
) {

    data class AboutCanadaEntity(
        var id: Int,
        @Expose @SerializedName(TITLE)
        var title: String? = "",
        @Expose @SerializedName(DESCRIPTION)
        var description: String? = "",
        @Expose @SerializedName(IMAGEHREF)
        var imageHref: String? = ""
    ) {
        companion object {
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val IMAGEHREF = "imageHref"
        }
    }
}
