package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.core.graphics.alpha
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // Event which triggers the end of the game (Событие, при котором игра заканчивается.)
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    /**
     * Вызывается при создании класса
     */
    init {
        _word.value = ""
        _score.value = 0
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
        if (wordList.isEmpty()) {
            //закончить игру , если список слов пуст
            onGameFinish()
        } else
        //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
    }

    /** Methods for buttons presses **/
    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    /** Method for the game completed event (Метод для события завершения игры) **/
    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    /** Method for the game completed event (метод сброса события завершения игры) **/
    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

}