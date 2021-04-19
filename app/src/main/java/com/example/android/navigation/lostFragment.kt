
package com.example.android.navigation

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentLostBinding


class lostFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLostBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_lost, container, false)
        binding.tryAgainButton.setOnClickListener { view: View ->
            view.findNavController().navigate(lostFragmentDirections.actionLostFragmentToGameFragment())
        }
        return binding.root
    }
}
