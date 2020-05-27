package com.arinspect.proficiencyexercise.respository

import com.arinspect.proficiencyexercise.data.datasources.api.AboutCanadaResponse
import com.arinspect.proficiencyexercise.services.AboutCanadaService


/**
 * Created Date: 27-05-2020
 * Purpose: Repository Class for managing the 'About Canada' related data, repository is responsible
 * for providing the data to viewmodel from local database, network or cached data.
 */
class AboutCanadaRepository (
    private val aboutCanadaService: AboutCanadaService
) {

    /**
     * This function is responsible to fetch 'About Canada' data from api or cached data.
     */
    suspend fun getAllData(): AboutCanadaResponse {
        val response = aboutCanadaService.getAllDataAsync()
        return response
        throw RuntimeException("Error")
    }
}