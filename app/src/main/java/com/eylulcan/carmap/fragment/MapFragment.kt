package com.eylulcan.carmap.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eylulcan.carmap.R
import com.eylulcan.carmap.adapter.CarListAdapter
import com.eylulcan.carmap.databinding.BottomSheetFragmentBinding
import com.eylulcan.carmap.databinding.FragmentMapBinding
import com.eylulcan.carmap.model.Car
import com.eylulcan.carmap.viewmodel.CarListViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior


const val FULL_MAP_ZOOM_RATIO = 10f
const val ITEM_MAP_ZOOM_RATIO = 17f

class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding
    private val carListViewModel: CarListViewModel by activityViewModels()
    private lateinit var carListAdapter: CarListAdapter
    private lateinit var supportMapFragment: SupportMapFragment

    private lateinit var includeBinding: BottomSheetFragmentBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)
        includeBinding = binding.bottomSheetFragment
        val bottomSheet: LinearLayout = binding.linearLayout
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        observeViewModel()
        carListViewModel.getCarList()
    }

    private fun observeViewModel() {
        carListViewModel.cars.observe(viewLifecycleOwner) { list ->
            recyclerViewSetup(list)
            initializeMap(list)
        }
    }

    private fun initializeMap(list: List<Car>) {
        supportMapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        for (car in list) {
            createMarker(car)
        }
        arrangeZoom(list[0], FULL_MAP_ZOOM_RATIO)
        markerClicked()

    }

    private fun markerClicked() {
        supportMapFragment.getMapAsync { googleMap ->
            googleMap.setOnMarkerClickListener { marker ->
                val plateNo: String? = marker.title
                plateNo?.let { plate ->
                    val position = carListAdapter.onMapMarkClicked(plate)
                    carListAdapter.onSelectedItem(includeBinding.carListRecyclerView[position])
                }
                false
            }
        }
    }

    private fun arrangeZoom(car: Car, zoomRatio: Float) {
        supportMapFragment.getMapAsync {
            it.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        car.getLatitude(),
                        car.getLongitude()
                    ), zoomRatio
                )
            )
        }
    }

    private fun createMarker(car: Car) {
        supportMapFragment.getMapAsync { map ->
            map.addMarker(
                MarkerOptions()
                    .position(LatLng(car.getLatitude(), car.getLongitude()))
                    .title(car.getLicensePlate())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
        }
    }

    private fun recyclerViewSetup(list: List<Car>) {
        carListAdapter = CarListAdapter()
        val carListView = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        includeBinding.carListRecyclerView.apply {
            this.layoutManager = carListView
            this.adapter = carListAdapter
            carListAdapter.setOnItemClickListener { id -> onItemClicked(id) }
            carListAdapter.carList = list
        }
    }

    private fun onItemClicked(selectedCar: Car) {
        arrangeZoom(selectedCar, ITEM_MAP_ZOOM_RATIO)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

}

