package com.test.assignment.ui.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.test.assignment.databinding.FragmentHitsDetailsBinding
import com.test.assignment.domain.entities.Hits
import com.test.assignment.ui.LoadImageView
import com.test.assignment.ui.fragment.UserDetailsFragment.Companion.HIT_DETAIL_DATA

class HitsDetailsFragment : Fragment() {

    private lateinit var binding: FragmentHitsDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHitsDetailsBinding.inflate(
        inflater,
        container,
        false
    ).also {
        binding = it
    }.root

    @RequiresApi(33)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    @Suppress("DEPRECATION")
    private fun getData() {
        val data = if (Build.VERSION.SDK_INT >= 33) {
            requireArguments().getParcelable(HIT_DETAIL_DATA, Hits::class.java)
        } else {
            requireArguments().getParcelable(HIT_DETAIL_DATA)
        }

        with(binding) {
            data?.run {
                txtUploadedUser.text = "User: $user"
                txtImgSize.text = "Image size: $imageSize"
                txtImgTags.text = "Tags:  $tags"
                txtImgType.text = "Type:  $type"
                tvLikes.text = likes.toString()
                tvViews.text= views.toString()
                tvComments.text= comments.toString()
                tvDownloads.text = downloads.toString()
                LoadImageView.loadImage(imgBanner, largeImageURL)
            }
        }
    }
}