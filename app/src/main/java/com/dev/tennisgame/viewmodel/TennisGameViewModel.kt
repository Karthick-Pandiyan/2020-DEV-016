package com.dev.tennisgame.viewmodel

import androidx.lifecycle.MutableLiveData
import com.dev.tennisgame.utilities.GameConstant
import com.dev.tennisgame.viewmodel.base.BaseTennisGameViewModel

class TennisGameViewModel: BaseTennisGameViewModel() {

    val winner = MutableLiveData<String>()
    val score = MutableLiveData<String>()

    fun getScore(): String {
        return when {
            hasWinner() -> getWinnerDetails()
            hasAdvantage() -> "${GameConstant.ADVANTAGE} ${getPlayerWithHighestScore()}${GameConstant.EXCLAMATION_SYMBOL}"
            isDeuce() -> GameConstant.DEUCE
            isPlayerScoresAreEqual() -> "${translateToScore(playerOneScore)} ${GameConstant.ALL}"
            else -> "${translateToScore(playerOneScore)}, ${translateToScore(playerTwoScore)}"
        }
    }

    fun getPlayerWithHighestScore(): String {
        return when {
            playerOneScore > playerTwoScore -> playerOneName
            else -> playerTwoName
        }
    }

    private fun getWinnerDetails(): String {
        val winnerDetails = "${getPlayerWithHighestScore()} ${GameConstant.WON_THE_GAME}"
        winner.value = winnerDetails
        return winnerDetails
    }

    fun addScoreToPlayerOne() {
        playerOneScore++
        score.value = getScore()
    }

    fun addScoreToPlayerTwo() {
        playerTwoScore++
        score.value = getScore()
    }

    fun resetGame(){
        super.resetScore()
        score.value = getScore()
    }
}