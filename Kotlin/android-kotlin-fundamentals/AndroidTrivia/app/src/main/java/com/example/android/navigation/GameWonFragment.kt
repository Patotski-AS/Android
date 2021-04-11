/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)

        //извлекаем аргументы из пакета, затем используйте a Toast для отображения аргументов
        val args = GameWonFragmentArgs.fromBundle(requireArguments())

        Log.i("qwerty", "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}")

        Toast.makeText(this.context,
                "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}",
                Toast.LENGTH_LONG).show()


        // Add OnClick Handler for Next Match button
        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController()
                    .navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
        //МЕНЮ ПАРАМЕТРОВ
        setHasOptionsMenu(true)

        return binding.root
    }

    // Creating our Share Intent
    private fun getShareIntent() : Intent {
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
        return shareIntent
    }

    /**
     *Starting an Activity with our new Intent
     * Этот метод получает Intentот getShareIntent()и вызывает,
     * startActivity()чтобы начать совместное использование.
      */
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    /** Showing the Share Menu Item Dynamically
     * *
     * Используйте, getShareIntent()чтобы получить shareIntent.
     * Чтобы убедиться, что shareIntent проблема разрешена Activity,
     * обратитесь к диспетчеру пакетов Android ( PackageManager),
     * который отслеживает приложения и действия, установленные на устройстве.
     * Используйте package Manager свойство Activity,
     * чтобы получить доступ к диспетчеру пакетов, и позвоните resolveActivity().
     * Если результат равен null, что означает, что shareIntent проблема не разрешается,
     * найдите пункт меню общего доступа в расширенном меню и сделайте этот пункт меню невидимым.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        if(getShareIntent().resolveActivity(requireActivity().packageManager)==null){
            menu.findItem(R.id.share).isVisible = false
        }
    }

    /** Sharing from the Menu
     * Чтобы обработать пункт меню, переопределите onOptionsItemSelected()в GameWonFragment классе
     * Вызов shareSuccess()метода при щелчке по пункту меню:
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

}
