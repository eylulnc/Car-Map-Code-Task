package com.eylulcan.carmap.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.eylulcan.carmap.R
import com.eylulcan.carmap.databinding.FragmentCarDetailBinding
import com.eylulcan.carmap.model.Car

class CarDetailFragment : Fragment() {

    private lateinit var binding: FragmentCarDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCarDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedCar = arguments?.get("selectedCar") as Car
        println("enc - ${selectedCar.getModelName()}")
        Glide.with(requireContext()).load(selectedCar.getCarImageUrl())
            .placeholder(R.drawable.ic_image_not_supported_db)
            .into(binding.imageView)
        binding.imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        binding.cleanlinessTextView.text = getString(R.string.cleanliness, selectedCar.getInnerCleanliness())
        binding.fuelLevelTextView.text = getString(R.string.fuel_level, selectedCar.getFuelLevel().toString())
        binding.fuelTypeTextView.text = getString(R.string.fuel_type, selectedCar.getFuelType())
        binding.licencePlateTextView.text = selectedCar.getLicensePlate()
        binding.seriesTextView.text = getString(R.string.series, selectedCar.getSeries())
        binding.transmissionTextView.text = getString(R.string.transmission, selectedCar.getTransmission())
        binding.modelNameTextView.text = selectedCar.getModelName()
        binding.colorTextView.text = getString(R.string.color, selectedCar.getColor())
    }


}