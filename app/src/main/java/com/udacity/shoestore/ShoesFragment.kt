package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoesBinding
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [ShoesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoesFragment : Fragment() {

    private lateinit var binding: FragmentShoesBinding

    private val model: ShoesModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes, container, false)

        binding.fab.setOnClickListener {
            findNavController().navigate(ShoesFragmentDirections.actionShoesFragmentToShoeDetailFragment())
        }

        model.shoeList.observe(viewLifecycleOwner, Observer { list ->
            list.forEach {shoe ->
                Timber.i(shoe.toString())
                val inflatedView = View.inflate(binding.shoeListLayout.context, R.layout.shoe_item, null)
                inflatedView.findViewById<TextView>(R.id.shoe_name_item).text = shoe.name
                inflatedView.findViewById<TextView>(R.id.company_item).text = shoe.company
                inflatedView.findViewById<TextView>(R.id.shoe_size_item).text = shoe.size.toString()
                inflatedView.findViewById<TextView>(R.id.description_item).text = shoe.description
                binding.shoeListLayout.addView(inflatedView)
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout, menu)
    }
}