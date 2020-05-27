package com.arinspect.proficiencyexercise.services

import com.arinspect.proficiencyexercise.data.datasources.api.AboutCanadaResponse
import retrofit2.http.GET

/**
 * Created Date: 27-05-2020
 * Purpose: Interface for the APIs of 'About Canada'.
 */
interface AboutCanadaService {

    /**
     * Function to get all 'About Canada' data which are available on server.
     *
     * @return AboutCanadaResponse
     */
    @GET("facts.json")
    suspend fun getAllDataAsync(): AboutCanadaResponse
}