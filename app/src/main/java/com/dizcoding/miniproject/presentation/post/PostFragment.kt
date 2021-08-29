package com.dizcoding.miniproject.presentation.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dizcoding.miniproject.R
import com.dizcoding.miniproject.databinding.FragmentPostListBinding
import com.dizcoding.miniproject.databinding.ItemPostBinding
import com.dizcoding.miniproject.external.Navigations
import com.dizcoding.miniproject.external.adapter.setUp
import com.dizcoding.miniproject.external.extension.navigationTo
import org.koin.androidx.viewmodel.ext.android.viewModel


class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostListBinding
    lateinit var mLayoutManager: RecyclerView.LayoutManager
    private val viewModel: PostViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.vBack.setOnClickListener { requireActivity().onBackPressed() }
        mLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.requestPostData()
        viewModel.getPostData().observe(viewLifecycleOwner, {
            loadPostData(it)
        })
    }

    private fun loadPostData(items: List<PostItem>) {
        binding.rvPost.setUp(items, R.layout.item_post, {
            val itemBinding = ItemPostBinding.bind(this)
            itemBinding.apply {
                val author = this.root.context.resources.getString(
                    R.string.format_item_post_name_and_company_name,
                    it.authorName,
                    it.authorCompanyName
                )
                tvPostTitle.text = it.title
                tvPostBody.text = it.body
                tvPostAuthor.text = author
            }
        }, {
           navigationTo(Navigations.URL_POST_DETAIL, listOf(it.id))
        }, mLayoutManager)
    }
}