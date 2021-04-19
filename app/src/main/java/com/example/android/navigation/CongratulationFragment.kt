

package com.example.android.navigation

import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.app.ShareCompat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentCongratulationBinding


class CongratulationFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentCongratulationBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_congratulation, container, false)
        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                    CongratulationFragmentDirections.actionCongratulationToGameFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun getShareIntent() : Intent {
        val args = CongratulationFragmentArgs.fromBundle(requireArguments())
        return ShareCompat.IntentBuilder.from(activity!!)
                .setText(getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
                .setType("text/plain")
                .intent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            menu.findItem(R.id.share)?.isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
