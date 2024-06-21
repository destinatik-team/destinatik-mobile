package com.dicoding.destinatik.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dicoding.destinatik.R
import com.dicoding.destinatik.databinding.FragmentDetailBinding
import com.dicoding.destinatik.databinding.FragmentDetailsBinding
import com.dicoding.destinatik.utils.BlurUtils

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStatusBar()
        setupBlurView()
        setupPlaceDetails()
        setupBackButton() // Setup the back button
    }


    private fun setupStatusBar() {
        activity?.window?.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun resetStatusBar() {
        activity?.window?.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            statusBarColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary) // replace with your primary color
        }
    }
    private fun vectorToBitmap(context: Context, vectorResId: Int): Bitmap {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)!!
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return bitmap
    }

    private fun setupBlurView() {
        try {
            val radius = 20f
            val vectorResId = R.drawable.blur // Your vector drawable resource ID
            val backgroundBitmap = vectorToBitmap(requireContext(), vectorResId)

            // Check if backgroundBitmap is not null
            if (backgroundBitmap == null) {
                Log.e("DetailsFragment", "Background bitmap is null")
                return
            }

            val blurredBitmap = BlurUtils.blur(requireContext(), backgroundBitmap, radius)
            val blurredDrawable: Drawable = BitmapDrawable(resources, blurredBitmap)

            binding.blurView.setImageDrawable(blurredDrawable)

            binding.apply {
                placeRatingContainer.setOnClickListener { navigateToRating() }
                ivRating1.setOnClickListener { navigateToRating() }
                ivRating2.setOnClickListener { navigateToRating() }
                ivRating3.setOnClickListener { navigateToRating() }
                ivRating4.setOnClickListener { navigateToRating() }
                ivRating5.setOnClickListener { navigateToRating() }
                placeRating.setOnClickListener { navigateToRating() }
            }
        } catch (e: NullPointerException) {
            Log.e("DetailsFragment", "View not found", e)
        } catch (e: Exception) {
            Log.e("DetailsFragment", "Error in setupBlurView", e)
        }
    }

    private fun setupPlaceDetails() {
        val place = args.place
        val searchModel = args.searchModel

        if (place != null) {
            Log.d("DetailsFragment", "Place details: $place")
            binding.placeTitle.text = place.placeName
            binding.placeLocation.text = place.category
        } else if (searchModel != null) {
            Log.d("DetailsFragment", "SearchModel details: $searchModel")
            binding.placeTitle.text = searchModel.name
            binding.placeLocation.text = searchModel.address

            // Use Glide to load the image
            Glide.with(requireContext())
                .load(searchModel.photos.firstOrNull()?.url) // Assuming photos list is not empty and url is valid
                .placeholder(R.drawable.shape_content) // Placeholder image
                .into(binding.backgroundImage)
        } else {
            // Handle the case when both place and searchModel are null
            binding.placeTitle.text = getString(R.string.place_name)
            binding.placeLocation.text = getString(R.string.location)
        }
    }


    private fun navigateToRating() {
        val ratingFragment = RatingFragment()
        ratingFragment.show(childFragmentManager, ratingFragment.tag)
    }

    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        resetStatusBar()
        _binding = null
    }
}
