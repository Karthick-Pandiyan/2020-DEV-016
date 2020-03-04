# Tennis Game Rules:

This Kata is about implementing a simple tennis game.
I came up with it while thinking about Wii tennis, where they have simplified tennis, so each set is one game.

The scoring system is rather simple:

1. Each player can have either of these points in one game 0 15 30 40

2. If you have 40 and you win the ball you win the game, however there are special rules.

3. If both have 40 the players are deuce.
- If the game is in deuce, the winner of a ball will have advantage and game ball.
- If the player with advantage wins the ball he wins the game
- If the player without advantage wins they are back at deuce.

===== Alternate description of the rules per Wikipedia ( http://en.wikipedia.org/wiki/Tennis#Scoring ):

1.  A game is won by the first player to have won at least four points in total and at least
    two points more than the opponent.

2.  The running score of each game is described in a manner peculiar to tennis:
    scores from zero to three points are described as “love”, “fifteen”, “thirty”, and “forty” respectively.

3.  If at least three points have been scored by each player, and the scores are equal, the score is “deuce”.

4.  If at least three points have been scored by each side and a player has one more point than his opponent,
    the score of the game is “advantage” for the player in the lead.
    
    
# Prerequisites
- 4 GB RAM minimum, 8 GB RAM recommended

- 2 GB of available disk space minimum, 4 GB Recommended (500 MB for IDE + 1.5 GB for Android SDK and emulator system image)
1280 x 800 minimum screen resolution

- Mac OS X 10.10 or higher

- Microsoft Windows 7/8/10 (32- or 64-bit). The Android Emulator supports 64-bit Windows only

- Android Studio

- Android SDK

- GIT Bash

- Android Emulator / Physical Device to test

# Run Application
Step-1: Open "Android studio" then choose "Check out project from Version control"

Step-2: From the options choose "Git", then provide the following url "https://github.com/2020-DEV-016/Tennis-Game.git" and tap on "Clone"

Step-3: Once the checkout completes it should open up the project.

Step - 4: Click "Run App" from the toolbar to run the application on the Physical device or Emulator.

step - 5: Use Add Point buttons to add scores to the player and see the score points.

# Run Test Cases
Step-1: Click on "Run" option in toolbar and edit the run configure to point testcases within the application

Step-2: Please find the file "TennisGameTest" under the following path "app/src/test/java/com.dev.tennisgame"

Step-3: You can choose to test all the cases by running the whole file or choose any particular test cases by opening it
