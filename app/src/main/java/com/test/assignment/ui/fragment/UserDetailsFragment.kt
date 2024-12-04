package com.test.assignment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.assignment.R
import com.test.assignment.databinding.FragmentUserDetyailsBinding
import com.test.assignment.ui.adapter.HitsListAdapter
import com.test.assignment.utils.Resource
import com.test.assignment.utils.showOrGone
import com.test.assignment.viewmodel.HitsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentUserDetyailsBinding
    private val hitsViewModel: HitsViewModel by viewModels()
    private lateinit var adapter: HitsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUserDetyailsBinding.inflate(
        inflater,
        container,
        false
    ).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        hitsViewModel.fetchArticleList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    private  fun init() {
        adapter = HitsListAdapter()
        with(binding) {
            recHit.adapter = adapter
            val layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            recHit.layoutManager = layoutManager
        }

        adapter.onItemClick = { resultItem ->
            Navigation.findNavController(binding.recHit)
                .navigate(
                    R.id.action_userDetailsFragment_to_hitsDetailsFragment,
                    bundleOf(Pair(HIT_DETAIL_DATA, resultItem))
                )
        }
    }

    private fun setObserver() {
        lifecycle.coroutineScope.launch {
            hitsViewModel.hitDataList.collect {
                when (it) {
                    is Resource.Success -> {
                        with(binding) {
                            txtNoData.showOrGone(false)
                            adapter.differ.submitList(it.data.hits)
                            recHit.showOrGone(true)
                            progressbar.showOrGone(false)
                        }
                    }
                    is Resource.Error -> {
                        //Handle Error
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                        binding.progressbar.showOrGone(false)
                    }
                    else -> {
                        binding.progressbar.showOrGone(true)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setObserver()
    }

    companion object {
        const val HIT_DETAIL_DATA = "HIT_DETAIL_DATA"
    }

}