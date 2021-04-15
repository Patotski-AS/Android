package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.core.graphics.alpha
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    companion object {
        // Time when the game is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 60000L
    }


    // Countdown time
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val timer: CountDownTimer

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
     *Версия String текущего времени
     *
     * @param Transformations.map() Способ обеспечивает способ выполнить обработку данных на источнике LiveData
     * и возвращает результат LiveDataобъект. Эти преобразования не вычисляются,
     * если наблюдатель не наблюдает за возвращаемым LiveDataобъектом.
     * Этот метод принимает в LiveData качестве параметров источник и функцию. Функция манипулирует источником LiveData.
     * Примечание . Переданная лямбда-функция Transformation.map()выполняется в основном потоке, поэтому не включайте длительные задачи.
     *
     * @param  DateUtils.formatElapsedTime() служебного метода, который занимает long несколько секунд и форматирует ее в " MM:SS" строковый формат.
     */
    val currentTimeString = Transformations.map(currentTime) { time -> DateUtils.formatElapsedTime(time) }


    /**
     * Вызывается при создании класса
     */
    init {
        _word.value = ""
        _score.value = 0
        resetList()
        nextWord()

        /**
         * Создает таймер, который запускает конец игры, когда она заканчивается.
         */
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            /**
             * метод обратного вызова, который вызывается на каждом интервале или на каждом тике
             * @param millisUntilFinished Это количество времени , пока таймер не будет закончен в миллисекундах
             */
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished / ONE_SECOND
            }

            /**
             * метод обратного вызова, вызывается когда таймер заканчивает работу
             */
            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }
        }
        timer.start()
    }

    /**
     * обратный вызов для очистки ресурсов.
     * Объект ViewModel уничтожается, когда связанный фрагмент отсоединяется или когда действие завершается.
     * Прямо перед ViewModel уничтожением вызывается onCleared()
     *
     */
    override fun onCleared() {
        super.onCleared()
        /**
         * отключите таймер, чтобы избежать утечки памяти
         */
        timer.cancel()
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
            resetList()
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