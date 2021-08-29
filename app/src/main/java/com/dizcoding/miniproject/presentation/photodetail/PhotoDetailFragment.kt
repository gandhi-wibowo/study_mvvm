package com.dizcoding.miniproject.presentation.photodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dizcoding.miniproject.databinding.FragmentPhotoDetailBinding
import com.squareup.picasso.Picasso

class PhotoDetailFragment : Fragment() {
    private lateinit var binding: FragmentPhotoDetailBinding
    private val args: PhotoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.vBack.setOnClickListener { requireActivity().onBackPressed() }
        binding.tvPhotoName.text = args.title
        Picasso.get()
            .load(args.link)
            .into(binding.ivPhoto)

        Toast.makeText(requireContext(),"Double tap image for zoom",Toast.LENGTH_LONG).show()
    }

}