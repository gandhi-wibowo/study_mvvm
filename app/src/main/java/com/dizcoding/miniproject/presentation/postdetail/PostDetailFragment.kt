package com.dizcoding.miniproject.presentation.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dizcoding.miniproject.R
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Comment
import com.dizcoding.miniproject.databinding.FragmentPostDetailBinding
import com.dizcoding.miniproject.databinding.ItemComentBinding
import com.dizcoding.miniproject.external.Navigations
import com.dizcoding.miniproject.external.adapter.setUp
import com.dizcoding.miniproject.external.extension.navigationTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostDetailFragment : Fragment() {

    private val viewModel: PostDetailViewModel by viewModel()
    private lateinit var binding: FragmentPostDetailBinding
    private val args: PostDetailFragmentArgs by navArgs()
    lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.vBack.setOnClickListener { requireActivity().onBackPressed() }
        mLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.requestPostDetailData(args.postId)
        viewModel.requestPostDetailCommentData(args.postId)

        viewModel.getPostDetailData().observe(viewLifecycleOwner, {
            binding.apply {
                tvPostTitle.text = it.title
                tvPostBody.text = it.body
            }
        })

        viewModel.getPostCommentData().observe(viewLifecycleOwner, {
            loadCommentData(it)
        })

        viewModel.getUserDetailData().observe(viewLifecycleOwner, { userData ->
            binding.apply {
                val author = this.root.context.resources.getString(
                    R.string.format_item_post_name_and_company_name,
                    userData.name,
                    userData.company?.name
                )
                tvPostAuthor.text = author
                tvPostAuthor.setOnClickListener {
                    userData.id?.let {
                        navigationTo(Navigations.URL_USER_DETAIL, listOf(it))
                    }
                }
            }
        })

    }

    private fun loadCommentData(items: List<Comment>) {
        binding.rvComment.setUp(items, R.layout.item_coment, {
            val itemBinding = ItemComentBinding.bind(this)
            itemBinding.apply {
                tvCommentBody.text = it.body
                tvCommentAuthor.text = it.name
            }
        }, { }, mLayoutManager)
    }
}