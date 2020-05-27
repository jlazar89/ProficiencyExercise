package com.arinspect.proficiencyexercise.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arinspect.proficiencyexercise.R
import com.arinspect.proficiencyexercise.data.datasources.api.AboutCanadaResponse
import com.arinspect.proficiencyexercise.data.datasources.api.AboutCanadaResponse.AboutCanadaEntity

import com.arinspect.proficiencyexercise.databinding.ItemAboutCanadaRowBinding
import com.arinspect.proficiencyexercise.databinding.ItemFooterProgressRowBinding

import com.arinspect.proficiencyexercise.utils.extensions.autoNotify
import com.arinspect.proficiencyexercise.utils.extensions.inflate
import com.arinspect.proficiencyexercise.widgets.recycler.adapter.RecyclerBaseAdapter
import com.arinspect.proficiencyexercise.widgets.recycler.adapter.RecyclerListAdapter
import com.arinspect.proficiencyexercise.widgets.recycler.holder.BaseHolder
import com.arinspect.proficiencyexercise.widgets.recycler.holder.SimpleHolder
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created Date: 27-05-2020
 * Purpose: Adapter Class for the recycler view in 'AboutCanadaListFragment'
 */
class AboutCanadaAdapter () :
    RecyclerListAdapter<AboutCanadaEntity, RecyclerView.ViewHolder>(), CoroutineScope {

    private val parentJob = Job()
    override val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Main

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RecyclerBaseAdapter.FOOTER -> {
                val binding = parent.inflate<ItemFooterProgressRowBinding>(R.layout.item_footer_progress_row)
                SimpleHolder(binding.root)
            }
            else -> {
                val binding = parent.inflate<ItemAboutCanadaRowBinding>(R.layout.item_about_canada_row)
                //binding.clickHandler = clickHandler
                AboutCanadaViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, model: AboutCanadaEntity?, position: Int) {
        // set the data item to viewTransactionHolder class data item instance.
        (holder as? AboutCanadaViewHolder)?.binding?.dataItem = model
    }

    /**
     * Purpose: This function will set the data to recyclerview using diffutils.
     */
    fun setTransactionData(trans: List<AboutCanadaEntity>) {
        if (list == null) {
            list = ArrayList()
        }

        launch {
            val diffResult: DiffUtil.DiffResult = withContext(Dispatchers.IO) {
                autoNotify(list, trans) { o, n -> o.id == n.id }
            }

            diffResult.dispatchUpdatesTo(this@AboutCanadaAdapter)
            list = trans
        }
    }


    // ViewHolder class for the transaction.
    inner class AboutCanadaViewHolder(val binding: ItemAboutCanadaRowBinding) :
        BaseHolder<AboutCanadaEntity>(binding.root)
}