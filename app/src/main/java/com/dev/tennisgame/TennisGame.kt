package com.dev.tennisgame

import androidx.lifecycle.MutableLiveData
import com.dev.tennisgame.utilities.GameConstant
import com.dev.tennisgame.utilities.GameConstant.Companion.ADVANTAGE
import com.dev.tennisgame.utilities.GameConstant.Companion.ALL
import com.dev.tennisgame.utilities.GameConstant.Companion.DEUCE
import com.dev.tennisgame.utilities.GameConstant.Companion.EXCLAMATION
import com.dev.tennisgame.utilities.GameConstant.Companion.FOUR_POINT
import com.dev.tennisgame.utilities.GameConstant.Companion.ONE_POINT
import com.dev.tennisgame.utilities.GameConstant.Companion.THREE_POINT
import com.dev.tennisgame.utilities.GameConstant.Companion.TWO_POINT
import com.dev.tennisgame.utilities.GameConstant.Companion.WON_THE_GAME
import com.dev.tennisgame.utilities.GameConstant.Companion.illegalScore
import com.dev.tennisgame.utilities.Score.FORTY
import com.dev.tennisgame.utilities.Score.THIRTY
import com.dev.tennisgame.utilities.Score.FIFTEEN
import com.dev.tennisgame.utilities.Score.LOVE

class TennisGame(private val playerOneName: String, private val playerTwoName: String) {

    var playerOneScore: Int = LOVE.value
    var playerTwoScore: Int = LOVE.value
    val winner = MutableLiveData<String>()

    fun getScore(): String {
        if(hasWinner())
            return getWinnerDetails()
        if(hasAdvantage())
            return "$ADVANTAGE ${getPlayerWithHighestScore()}$EXCLAMATION"
        if(isDeuce())
            return DEUCE
        if(isPlayerScoresAreEqual())
            return "${translateToScore(playerOneScore)} $ALL"

        return "${translateToScore(playerOneScore)}, ${translateToScore(playerTwoScore)}"
    }

    fun translateToScore(score: Int): String {
        return when (score){
            FORTY.value -> GameConstant.FORTY
            THIRTY.value -> GameConstant.THIRTY
            FIFTEEN.value -> GameConstant.FIFTEEN
            LOVE.value -> GameConstant.LOVE
            else -> illegalScore(score)
        }
    }

    fun getPlayerWithHighestScore(): String {
        return when {
            playerOneScore > playerTwoScore -> playerOneName
            else -> playerTwoName
        }
    }

    private fun getWinnerDetails(): String {
        winner.value = getPlayerWithHighestScore()
        return "${getPlayerWithHighestScore()} $WON_THE_GAME"
    }

     private fun hasWinner() =
        playerTwoScore >= FOUR_POINT && playerTwoScore >= playerOneScore + TWO_POINT ||
                playerOneScore >= FOUR_POINT && playerOneScore >= playerTwoScore + TWO_POINT

    private fun hasAdvantage() = playerTwoScore >= FOUR_POINT && playerTwoScore == playerOneScore + ONE_POINT ||
            playerOneScore >= FOUR_POINT && playerOneScore == playerTwoScore + ONE_POINT

    private fun isDeuce() = playerOneScore >= THREE_POINT && isPlayerScoresAreEqual()

    private fun isPlayerScoresAreEqual() = playerOneScore == playerTwoScore

    fun addScoreToPlayerOne(){
        playerOneScore++
    }

    fun addScoreToPlayerTwo(){
        playerTwoScore++
    }

}