package com.dizcoding.miniproject.presentation.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.dizcoding.miniproject.data.network.jsonplaceholder.response.Photo
import com.dizcoding.miniproject.databinding.FragmentUserDetailBinding
import com.dizcoding.miniproject.external.Navigations
import com.dizcoding.miniproject.external.extension.navigationTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailFragment : Fragment(), AlbumPhotoCallback {

    private lateinit var binding: FragmentUserDetailBinding
    private val viewModel: UserDetailViewModel by viewModel()
    private val args: UserDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.vBack.setOnClickListener { requireActivity().onBackPressed() }
        viewModel.requestGetUserData(args.userId)
        viewModel.requestGetAlbumData(args.userId)

        viewModel.getUserData().observe(viewLifecycleOwner, {
            with(binding) {
                // user
                tvUserName.text = it.name
                tvUserEmail.text = it.email

                // address
                tvStreet.text = it.address?.street
                tvSuite.text = it.address?.suite
                tvCity.text = it.address?.city
                tvZipcode.text = it.address?.zipcode

                // company
                tvName.text = it.company?.name
                tvCatchPhrase.text = it.company?.catchPhrase
                tvBs.text = it.company?.bs
            }
        })

        viewModel.getAlbumItemData().observe(viewLifecycleOwner, {
            binding.rvAlbum.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.rvAlbum.adapter = UserAlbumAdapter(it, this)

        })
    }

    override fun onPhotoClicked(photo: Photo) {
        navigationTo(
            Navigations.URL_PHOTO_DETAIL,
            listOf(photo.title.toString(), photo.url.toString())
        )
    }
}