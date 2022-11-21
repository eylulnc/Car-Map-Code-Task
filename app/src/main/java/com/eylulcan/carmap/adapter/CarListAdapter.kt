package com.eylulcan.carmap.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eylulcan.carmap.R
import com.eylulcan.carmap.databinding.CarListRecyclerRowBinding
import com.eylulcan.carmap.model.Car
import com.google.android.material.card.MaterialCardView


class CarListAdapter : RecyclerView.Adapter<CarListAdapter.ViewHolder>() {

    private var onItemClickListener: ((car: Car) -> Unit)? = null
    private var itemViewList: ArrayList<MaterialCardView> = ArrayList()
    private lateinit var binding: CarListRecyclerRowBinding

    class ViewHolder(val binding: CarListRecyclerRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            CarListRecyclerRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = carList[position]
        itemViewList.add(holder.binding.materialCardRow)
        holder.binding.materialCardRow.strokeColor = holder.binding.root.resources
            .getColor(R.color.dutch_white)
        Glide.with(holder.binding.root).load(car.getCarImageUrl())
            .placeholder(R.drawable.ic_image_not_supported_dw)
            .into(holder.binding.carImageView)
        holder.binding.modelTextView.text = car.getModelName()
        holder.binding.licensePlateTextView.text = car.getLicensePlate()
        holder.binding.infoButton.setOnClickListener {
            car.editData()
            val carBundle = bundleOf("selectedCar" to car)
            it.findNavController().navigate(
                R.id.action_mapFragment_to_carDetail,
                carBundle, null, null
            )
        }

        holder.itemView.setOnClickListener {
            onSelectedItem(holder.binding.materialCardRow)
            car.let { selectedCar -> onItemClickListener?.let { it1 -> it1(selectedCar) } }

        }
    }

    fun onSelectedItem(view: View) {
        for (tempItemView in itemViewList) {
            if (view === tempItemView) {
                tempItemView.strokeColor = binding.root.resources
                    .getColor(R.color.dark_sea_green)
            } else {
                tempItemView.strokeColor = binding.root.resources
                    .getColor(R.color.dutch_white)
            }
        }
    }

    fun onMapMarkClicked(plate: String): Int {
        for (car in carList) {
            if (car.getLicensePlate().equals(plate)) {
                return carList.indexOf(car)
            }
        }
        return 0
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(
            oldItem: Car,
            newItem: Car
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Car,
            newItem: Car
        ): Boolean {
            return oldItem.equals(newItem)
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var carList: List<Car>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    fun setOnItemClickListener(listener: (car: Car) -> Unit) {
        onItemClickListener = listener
    }

}