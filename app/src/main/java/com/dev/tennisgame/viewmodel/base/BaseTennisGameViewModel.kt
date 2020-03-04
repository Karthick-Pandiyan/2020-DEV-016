package com.dev.tennisgame.viewmodel.base

import androidx.lifecycle.ViewModel
import com.dev.tennisgame.utilities.GameConstant
import com.dev.tennisgame.utilities.Score

abstract class BaseTennisGameViewModel : ViewModel() {

    lateinit var playerOneName: String
    lateinit var playerTwoName: String
    var playerOneScore: Int = Score.LOVE.value
    var playerTwoScore: Int = Score.LOVE.value

    fun setPlayers(playerOneName: String, playerTwoName: String) {
        this.playerOneName = playerOneName
        this.playerTwoName = playerTwoName
    }

    fun translateToScore(score: Int): String {
        return when (score){
            Score.FORTY.value     -> GameConstant.FORTY
            Score.THIRTY.value    -> GameConstant.THIRTY
            Score.FIFTEEN.value   -> GameConstant.FIFTEEN
            Score.LOVE.value      -> GameConstant.LOVE
            else            -> GameConstant.illegalScore(score)
        }
    }

    fun resetScore(){
        playerOneScore = Score.LOVE.value
        playerTwoScore = Score.LOVE.value
    }

    internal fun hasWinner() = isPlayerTwoWinner() || isPlayerOneWinner()

    internal fun hasAdvantage() = isPlayerTwoHasAdvantage() || isPlayerOneHasAdvantage()

    internal fun isDeuce() = playerOneScore >= GameConstant.THREE_POINT && isPlayerScoresAreEqual()

    internal fun isPlayerScoresAreEqual() = playerOneScore == playerTwoScore

    private fun isPlayerOneWinner() = playerOneScore >= GameConstant.FOUR_POINT && playerOneScore >= playerTwoScore + GameConstant.TWO_POINT

    private fun isPlayerTwoWinner() = playerTwoScore >= GameConstant.FOUR_POINT && playerTwoScore >= playerOneScore + GameConstant.TWO_POINT

    private fun isPlayerOneHasAdvantage() = playerOneScore >= GameConstant.FOUR_POINT && playerOneScore == playerTwoScore + GameConstant.ONE_POINT

    private fun isPlayerTwoHasAdvantage() = playerTwoScore >= GameConstant.FOUR_POINT && playerTwoScore == playerOneScore + GameConstant.ONE_POINT
}