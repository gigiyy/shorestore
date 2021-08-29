package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe


/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var shoeDetailModel: ShoeDetailModel

    private val model: ShoesModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        shoeDetailModel = ViewModelProvider(this).get(ShoeDetailModel::class.java)
        binding.shoeDetailModel = shoeDetailModel
        binding.lifecycleOwner = this

        binding.addButton.setOnClickListener {
            model.addShoe(
                Shoe(
                    shoeDetailModel.name.value ?: "No Name",
                    shoeDetailModel.size.value.let {
                        if (it.isNullOrEmpty()) 0.0
                        else it.toDouble()
                    },
                    shoeDetailModel.company.value ?: "No Company",
                    shoeDetailModel.description.value ?: "No Description",
                    emptyList()
                )
            )
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment())
        }
        binding.cancelButton.setOnClickListener {
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment())
        }

        return binding.root
    }

}