package com.example.android.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentTitleBinding.inflate(inflater, container, false)

        binding.playButton.setOnClickListener{ view : View ->
            view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }
        /**
         * этот фрагмент хотел бы участвовать в заполнении меню параметров,
         * получая вызов onCreateOptionsMenu(Menu, MenuInflater) и связанные методы.
         */
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    /**
     * метод выполняет соответствующее действие при касании пункта меню.
     * В этом случае действие заключается в переходе к фрагменту,
     * который совпадает id с выбранным пунктом меню.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
