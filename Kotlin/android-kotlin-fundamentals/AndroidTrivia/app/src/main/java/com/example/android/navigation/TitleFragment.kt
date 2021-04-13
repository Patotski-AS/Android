package com.example.android.navigation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    /**
     *  Вызывается для расширения макета фрагмента
     */
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

        Log.i("TitleFragment", "onCreateView called")

        return binding.root
    }

    /**
     * Вызывается, когда фрагмент связан с действием его владельца
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("TitleFragment", "onAttach called")
    }

    /**
     * вызывается для создания начального фрагмента (кроме макета).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("TitleFragment", "onCreate called")
    }

    /**
     * Вызывается сразу после onCreateView()возврата, но до восстановления любого сохраненного состояния в представлении.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("TitleFragment", "onViewCreated called")
    }

    /**
     * Вызывается, когда фрагмент становится видимым; параллельно деятельности onStart()
     */
    override fun onStart() {
        super.onStart()
        Log.i("TitleFragment", "onStart called")
    }

    /**
     * Вызывается, когда фрагмент получает фокус пользователя; параллельно деятельности onResume()
     */
    override fun onResume() {
        super.onResume()
        Log.i("TitleFragment", "onResume called")
    }

    /**
     * Вызывается, когда фрагмент теряет фокус пользователя; параллельно деятельности onPause().
     */
    override fun onPause() {
        super.onPause()
        Log.i("TitleFragment", "onPause called")
    }

    /**
     * Вызывается, когда фрагмент больше не отображается на экране; параллельно деятельности onStop().
     */
    override fun onStop() {
        super.onStop()
        Log.i("TitleFragment", "onStop called")
    }

    /**
     *  Вызывается, когда представление фрагмента больше не требуется, для очистки ресурсов, связанных с этим представлением.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.i("TitleFragment", "onDestroyView called")

    }

    override fun onDetach() {
        super.onDetach()
        Log.i("TitleFragment", "onDetach called")
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

}
