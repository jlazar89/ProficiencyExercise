package com.arinspect.proficiencyexercise.ui.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.arinspect.proficiencyexercise.R
import com.arinspect.proficiencyexercise.utils.*
import kotlinx.android.synthetic.main.fragment_about_canada_list.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created Date: 27-05-2020
 * Purpose: This Fragment is used to show the recyclerview with the data
 */
class AboutCanadaListFragment : Fragment(){

    // viewmodel instance provided by Koin
    private val aboutCanadaViewModel by viewModel<AboutCanadaViewModel>()
    private var aboutCanadaAdapter: AboutCanadaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var rootView = inflater.inflate(R.layout.fragment_about_canada_list, container, false)

        // Initialize the about canada adapter along
        aboutCanadaAdapter = AboutCanadaAdapter()

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvItems.adapter = aboutCanadaAdapter

        aboutCanadaViewModel.apply {

            // Observing the changes made into the state liveData instance
            mState.observe(viewLifecycleOwner, Observer { state ->
                when (state) {
                    is LoadingState -> {
                        loader.visibility = View.VISIBLE
                        rvItems.visibility = View.INVISIBLE
                    }
                    is EmptyState -> {
                        emptyView.visibility = View.VISIBLE
                        rvItems.visibility = View.INVISIBLE
                    }
                    is ErrorState -> {
                        loader.visibility = View.GONE
                        emptyView.visibility = View.VISIBLE
                        emptyView.text = "Error occured"
                    }
                    is SuccessState -> {
                        loader.visibility = View.INVISIBLE
                        rvItems.visibility = View.VISIBLE
                    }

                }
            })

            //Observer for List
            getList().observe(viewLifecycleOwner, Observer {
                // observing the changes made into the list and set the updated data to the adapter of recyclerview.
                aboutCanadaAdapter?.setTransactionData(it)

            })

            //Observer for Title
            getTitle().observe(viewLifecycleOwner, Observer {
                getActivity()?.setTitle(it)
            })

            // Load the first page data.
            loadData(0, Constants.Tag.LIST)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_about_canada, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_refresh -> {
            aboutCanadaViewModel.onPullToRefresh()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

}