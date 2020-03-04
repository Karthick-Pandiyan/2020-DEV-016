package com.dev.tennisgame.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dev.tennisgame.R
import com.dev.tennisgame.viewmodel.TennisGameViewModel
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tennisGameViewModel: TennisGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playerOne = "John"
        val playerTwo = "Harry"
        tennisGameViewModel = TennisGameViewModel()
        tennisGameViewModel.setPlayers(playerOne, playerTwo)

        btnPlayerOne.setOnClickListener {
            tennisGameViewModel.addScoreToPlayerOne()
        }

        btnPlayerTwo.setOnClickListener {
            tennisGameViewModel.addScoreToPlayerTwo()
        }

        tennisGameViewModel.winner.observe(this, Observer { winnerDetails ->
            showWinnerInDialog(winnerDetails)
        })
        tennisGameViewModel.score.observe(this, Observer { score ->
            txtScoreView.text = score
        })
    }

    private fun showWinnerInDialog(winnerDetails: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(winnerDetails)
        dialogBuilder.setMessage(getString(R.string.game_over_description))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.yes)) { dialog, id ->
                tennisGameViewModel.resetGame()
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.no)) { dialog, id ->
                dialog.dismiss()
                finish()
            }
        dialogBuilder.show()
    }
}
