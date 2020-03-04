package com.dev.tennisgame

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dev.tennisgame.utilities.GameConstant
import com.dev.tennisgame.utilities.GameConstant.Companion.ALL
import com.dev.tennisgame.utilities.GameConstant.Companion.DEUCE
import com.dev.tennisgame.utilities.GameConstant.Companion.EXCLAMATION_SYMBOL
import com.dev.tennisgame.utilities.GameConstant.Companion.FIFTEEN
import com.dev.tennisgame.utilities.GameConstant.Companion.FORTY
import com.dev.tennisgame.utilities.GameConstant.Companion.FOUR_POINT
import com.dev.tennisgame.utilities.GameConstant.Companion.LOVE
import com.dev.tennisgame.utilities.GameConstant.Companion.THIRTY
import com.dev.tennisgame.utilities.GameConstant.Companion.WON_THE_GAME
import com.dev.tennisgame.utilities.GameConstant.Companion.illegalScore
import com.dev.tennisgame.utilities.Score
import com.dev.tennisgame.viewmodel.TennisGameViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TennisGameViewModelTest {

    private val playerOne = "John"
    private val playerTwo = "Joe"
    private val tennisGame = TennisGameViewModel()

    @Before
    fun setUp(){
        tennisGame.setPlayers(playerOne, playerTwo)
    }

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `Given translateToScore function returns illegal score when invalid score passed`(){
        val expectedResult = illegalScore(5)

        val actualResult = tennisGame.translateToScore(5)

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given translateToScore function should return Love when score is 0`(){
        val expectedResult = LOVE

        val actualResult = tennisGame.translateToScore(Score.LOVE.value)

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given translateToScore function should return Fifteen when score is 1`(){
        val expectedResult = FIFTEEN

        val actualResult = tennisGame.translateToScore(Score.FIFTEEN.value)

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given translateToScore function should return Thirty when score is 2`(){
        val expectedResult = THIRTY

        val actualResult = tennisGame.translateToScore(Score.THIRTY.value)

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given translateToScore function should return Forty when score is 3`(){
        val expectedResult = FORTY

        val actualResult = tennisGame.translateToScore(Score.FORTY.value)

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given getScore function should return Love all when both player scores are equal to 0`(){
        val expectedResult = "$LOVE $ALL"

        tennisGame.playerOneScore = Score.LOVE.value
        tennisGame.playerTwoScore = Score.LOVE.value
        val actualResult = tennisGame.getScore()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given addScoreToPlayerOne function should add one point when player one scored a point`(){
        val expectedResult = Score.FIFTEEN.value

        tennisGame.playerOneScore = Score.LOVE.value
        tennisGame.addScoreToPlayerOne()
        val actualResult = tennisGame.playerOneScore

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given addScoreToPlayerTwo function should add one point when player two scored a point`(){
        val expectedResult = Score.FIFTEEN.value

        tennisGame.playerTwoScore = Score.LOVE.value
        tennisGame.addScoreToPlayerTwo()
        val actualResult = tennisGame.playerTwoScore

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given getScore function should return expected score when player one wins the first ball`(){
        val expectedResult = "$FIFTEEN, $LOVE"

        tennisGame.playerOneScore = Score.LOVE.value
        tennisGame.playerTwoScore = Score.LOVE.value
        tennisGame.addScoreToPlayerOne()
        val actualResult = tennisGame.getScore()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given getScore function should return expected score when player two wins the first ball`(){
        val expectedResult = "$LOVE, $FIFTEEN"

        tennisGame.playerOneScore = Score.LOVE.value
        tennisGame.playerTwoScore = Score.LOVE.value
        tennisGame.addScoreToPlayerTwo()
        val actualResult = tennisGame.getScore()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given getScore function should return expected score when player 1 and player 2 won the first ball`(){
        val expectedResult = "$FIFTEEN $ALL"

        tennisGame.playerOneScore = Score.LOVE.value
        tennisGame.playerTwoScore = Score.LOVE.value
        tennisGame.addScoreToPlayerOne()
        tennisGame.addScoreToPlayerTwo()
        val actualResult = tennisGame.getScore()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given getScore function should return expected score when both players are in Deuce`(){
        val expectedResult = DEUCE

        tennisGame.playerOneScore = Score.FORTY.value
        tennisGame.playerTwoScore = Score.FORTY.value
        val actualResult = tennisGame.getScore()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given getScore function should not return Deuce, when both players are not in Deuce`(){
        val expectedResult = DEUCE

        tennisGame.playerOneScore = Score.THIRTY.value
        tennisGame.playerTwoScore = Score.FORTY.value
        val actualResult = tennisGame.getScore()

        Assert.assertNotEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given getPlayerWithHighestScore function should return player name, when who scored highest points`(){
        val expectedResult = playerOne

        tennisGame.playerOneScore = Score.LOVE.value
        tennisGame.playerTwoScore = Score.FIFTEEN.value
        val actualResult = tennisGame.getPlayerWithHighestScore()

        Assert.assertNotEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given getScore function should return player one is the winner, when player one = Forty and player two = Love`(){
        val expectedResult = "$playerOne $WON_THE_GAME"

        tennisGame.playerOneScore = FOUR_POINT
        tennisGame.playerTwoScore = Score.LOVE.value
        val actualResult = tennisGame.getScore()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given getScore function should return player two is the winner, when player one = Love and player two = Forty`(){
        val expectedResult = "$playerTwo $WON_THE_GAME"

        tennisGame.playerOneScore = Score.LOVE.value
        tennisGame.playerTwoScore = FOUR_POINT
        val actualResult = tennisGame.getScore()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given getScore function should return player one has the Advantage, when player one = 5 and player two = 4`(){
        val expectedResult = "${GameConstant.ADVANTAGE} $playerOne$EXCLAMATION_SYMBOL"

        tennisGame.playerOneScore = 5
        tennisGame.playerTwoScore = FOUR_POINT
        val actualResult = tennisGame.getScore()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given getScore function should return player two has the Advantage, when player one = 4 and player two = 5`(){
        val expectedResult = "${GameConstant.ADVANTAGE} $playerTwo$EXCLAMATION_SYMBOL"

        tennisGame.playerOneScore = FOUR_POINT
        tennisGame.playerTwoScore = 5
        val actualResult = tennisGame.getScore()

        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given getScore function should update winner in the MutableLiveData, when any one player won the game`() {
        val expectedResult = "${playerTwo} ${WON_THE_GAME}"

        tennisGame.playerOneScore = Score.LOVE.value
        tennisGame.playerTwoScore = FOUR_POINT
        tennisGame.getScore()
        val actualResult = tennisGame.winner.value

        Assert.assertEquals(expectedResult, actualResult)
    }
}