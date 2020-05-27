package com.arinspect.proficiencyexercise.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arinspect.proficiencyexercise.data.datasources.api.AboutCanadaResponse.AboutCanadaEntity
import com.arinspect.proficiencyexercise.respository.AboutCanadaRepository
import com.arinspect.proficiencyexercise.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created Date: 27-05-2020
 * Purpose: This ViewModel is used by the AboutCanadaListFragment.
 * 
 * We can make a call to the  server using this class which will takes the AboutCanadaRepository instance
 * using the Koin Module.
 */
class AboutCanadaViewModel(
    private val aboutCanadaRepository: AboutCanadaRepository
) : RxViewModel() {

    private var mListData: MutableLiveData<MutableList<AboutCanadaEntity>>? = null
    private var mTitle: MutableLiveData<String>? = null


    /**
     * Function returns the list of data
     */
    fun getList(): LiveData<MutableList<AboutCanadaEntity>> {
        if (mListData == null) {
            mListData = MutableLiveData()
        }
        return mListData as MutableLiveData<MutableList<AboutCanadaEntity>>
    }

    /**
     * Function returns title from the api
     */
    fun getTitle() : LiveData<String>{
        if (mTitle == null) {
            mTitle = MutableLiveData()
        }
        return mTitle as MutableLiveData<String>
    }

    /**
     * This function is used to get all the data from the server
     */
    fun loadData(page: Int, tag: Int) {
        launch {
            // Loading state
            mState.value = LoadingState(tag)
            try {
                withContext(Dispatchers.IO) {
                    // Get records from server.
                    val response = aboutCanadaRepository.getAllData()

                    // Set the data based on various tags.
                    when(tag) {
                        Constants.Tag.LIST -> {
                            mListData?.postValue(response.rows.toMutableList())
                            mTitle?.postValue(response.title)
                        }
                        Constants.Tag.REFRESH -> {
                            mListData?.postValue(response.rows.toMutableList())
                            mTitle?.postValue(response.title)
                        }
                        else -> {

                        }
                    }
                }

                // If no transaction data then set the empty state else set the success state.
                if(mListData?.value.isNullOrEmpty()) {
                    mState.value = EmptyState(tag)
                }else {
                    mState.value = SuccessState(tag)
                }
            } catch (e: Throwable) {
                // if any error to get the data then set the errorstate.
                mState.value = ErrorState(tag, e)
            }
        }
    }

    /**
     * This function is called when user swipes to refresh the recyclerview
     */
    fun onPullToRefresh() {
        loadData(0, Constants.Tag.REFRESH)
    }

}
