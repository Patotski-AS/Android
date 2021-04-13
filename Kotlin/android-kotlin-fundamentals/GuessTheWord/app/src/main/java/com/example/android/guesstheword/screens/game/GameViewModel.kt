package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.core.graphics.alpha
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    // The current word
    val word = MutableLiveData<String>()

    // The current score
    val score = MutableLiveData<Int>()

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    /**
     * Вызывается при создании класса
     */
    init {
        word.value = ""
        score.value = 0
        resetList()
        nextWord()
    }

    /**
     * обратный вызов для очистки ресурсов.
     * Объект ViewModel уничтожается, когда связанный фрагмент отсоединяется или когда действие завершается.
     * Прямо перед ViewModel уничтожением вызывается onCleared()
     *
     */
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (wordList.isNotEmpty()) {
            //Select and remove a word from the list
            word.value = wordList.removeAt(0)
        }
    }

    /** Methods for buttons presses **/

     fun onSkip() {
        score.value = (score.value)?.minus(1)
        nextWord()
    }

     fun onCorrect() {
        score.value = (score.value)?.plus(1)
        nextWord()
    }

}