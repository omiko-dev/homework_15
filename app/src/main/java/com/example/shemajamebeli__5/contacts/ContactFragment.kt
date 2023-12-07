package com.example.shemajamebeli__5.contacts

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shemajamebeli__5.BaseFragment
import com.example.shemajamebeli__5.R
import com.example.shemajamebeli__5.contacts.recycler.MessageBoxRecyclerAdapter
import com.example.shemajamebeli__5.databinding.FragmentContactBinding
import kotlinx.coroutines.launch

class ContactFragment : BaseFragment<FragmentContactBinding>(FragmentContactBinding::inflate) {
    private lateinit var adapter: MessageBoxRecyclerAdapter
    private val viewModel: ContactViewModel by viewModels()
    private var isClicked: Boolean = false

    override fun setUp() {
        setUpRecycler()
    }

    override fun setUpObserver() {
        fetchContactData()
        contactApiObserver()
    }

    override fun bind() {
        filterClick()
    }

    override fun listeners() {
        filterClick()
    }

    private fun setUpRecycler() {
        adapter = MessageBoxRecyclerAdapter()
        with(binding) {
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter
        }
    }

    private fun filterClick() {
        with(binding) {
            etSearch.addTextChangedListener { text ->
                btnFilter.setOnClickListener {
                    viewModel.filterData(text.toString().trim())
                    etSearch.setBackgroundResource(R.drawable.edit_text_border_rectangle_clicked)
                }
                if(text.toString() == ""){
                    etSearch.setBackgroundResource(R.drawable.edit_text_border_rectangle)
                    viewModel.filterData(text.toString())
                }
            }
        }
    }

    private fun contactApiObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.contactSharedFlow.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun fetchContactData() = viewModel.fetch()
}