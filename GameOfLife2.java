package gameOfLife3;

import java.awt.Point;
import java.util.Arrays;

public class GameOfLife3 {
  
  /*
   * Game of Life 3 Author: René Sesgör Last Update: 01/30/2016
   */
  
  public static void main( String[] args ) {
    
    // Measurement of the playground
    int playgroundWidth = 30;
    int playgroundHight = 20;
    
    if ( args.length != 0 ) {
      playgroundWidth = Integer.parseInt( args[0] );
      playgroundHight = Integer.parseInt( args[1] );
    }
    
    int snake1Idx = 0;
    int snake2Idx = 0;
    
    // Declaration of the tokens
    Point playerPosition = new Point();
    Point[] snakePositions1 = new Point[5];
    Point[] snakePositions2 = new Point[5];
    snakePositions1[snake1Idx] = new Point();
    snakePositions2[snake2Idx] = new Point();
    Point goldPosition1 = new Point();
    Point goldPosition2 = new Point();
    Point guruPosition = new Point( 10, 15 );
    Point atGuruPosition = new Point( guruPosition.x, guruPosition.y + 1 );
    Point hillPosition1 = new Point( guruPosition.x - 1, guruPosition.y - 1 );
    Point hillPosition2 = new Point( guruPosition.x, guruPosition.y - 1 );
    Point hillPosition3 = new Point( guruPosition.x + 1, guruPosition.y - 1 );
    Point keyPosition = new Point( -1, -1 );
    Point heartPosition = new Point( 12, 5 );
    Point starPosition = new Point( -1, -1 );
    
    // Initializing the randomly placed tokens
    GUI.randomCoords( playerPosition, playgroundWidth, playgroundHight );
    GUI.randomCoords( snakePositions1[snake1Idx], playgroundWidth, playgroundHight );
    GUI.randomCoords( snakePositions2[snake2Idx], playgroundWidth, playgroundHight );
    GUI.randomCoords( goldPosition1, playgroundWidth, playgroundHight );
    GUI.randomCoords( goldPosition2, playgroundWidth, playgroundHight );
    
    // Declaration of the steps that can be reached by the player
    boolean gold1Collected = false;
    boolean gold2Collected = false;
    boolean rich = false;
    boolean keyOwner = false;
    boolean heart = false;
    boolean enlightened = false;
    boolean snakeGotU = false;
    
    PrintTextEnglish.introText();
    
    while ( true ) {
      
      // Prints the playground with items
      GUI.printScreen( playgroundHight, playgroundWidth, snake1Idx, snake2Idx, playerPosition, snakePositions1,
          snakePositions2, atGuruPosition, goldPosition1, goldPosition2, hillPosition1, hillPosition2, hillPosition3,
          guruPosition, keyPosition, heartPosition, starPosition, gold1Collected, gold2Collected, rich, keyOwner, heart,
          enlightened, snakeGotU );
          
      // End message when enlightened
      if ( enlightened ) {
        PrintTextEnglish.enlightenedText();
        break;
      }
      
      // Star is reached / enlightenment is attained
      if ( playerPosition.equals( starPosition ) ) {
        enlightened = true;
        PrintTextEnglish.nextScreen();
        continue;
      }
      
      // End message when caught by Snake
      if ( snakeGotU ) {
        PrintTextEnglish.snakeGotU();
        break;
      }
      
      // Snake catches player
      if ( Arrays.asList( snakePositions1 ).contains( playerPosition )
          || Arrays.asList( snakePositions2 ).contains( playerPosition ) ) {
        Stages.coughtBySnake( playerPosition, guruPosition, playgroundWidth ); 
        
        snakeGotU = true;
        continue;
      }
      
      // Gold collected and positioned next to the guru
      if ( !rich ) { // !rich --> prevents endless loops when player on
                     // goldPosition after both are collected.
        if ( playerPosition.equals( goldPosition1 ) ) {
          gold1Collected = true;
          goldPosition1.setLocation( guruPosition.x - 1, guruPosition.y );
        }
      }
      if ( playerPosition.equals( goldPosition2 ) ) {
        gold2Collected = true;
        goldPosition2.setLocation( guruPosition.x + 1, guruPosition.y );
      }
      if ( gold1Collected && gold2Collected ) {
        gold1Collected = false;
        rich = true;
        PrintTextEnglish.nextScreen();
        continue;
      }
      
      // At Guru and key becomes visible
      if ( rich && playerPosition.equals( atGuruPosition ) ) {
        keyPosition.setLocation( atGuruPosition.x + 1, atGuruPosition.y );
        rich = false;
        PrintTextEnglish.guruThxText();
        continue;
      }
      
      // Key is collected
      if ( playerPosition.equals( keyPosition ) ) {
        keyOwner = true;
        keyPosition.setLocation( -1, -1 );
        PrintTextEnglish.nextScreen();
        continue;
      }
      
      // Heart is reached
      if ( keyOwner && playerPosition.equals( heartPosition ) ) {
        heart = true;
        heartPosition.setLocation( -1, -1 );
        starPosition.setLocation( 10, 10 );
        PrintTextEnglish.gotHeartText();
        continue;
      }
      
      // Movements of the player and snakes
      // and reinitalizing of the snake head index counter (snakeIdx)
      
      Movements.movePlayer( playgroundHight, playgroundWidth, playerPosition );
      
      Movements.moveSnake1( playerPosition, snakePositions1, snake1Idx );
      snake1Idx = Movements.moveSnake1( playerPosition, snakePositions1, snake1Idx );
      
      Movements.moveSnake2( playerPosition, snakePositions2, snake2Idx );
      snake2Idx = Movements.moveSnake1( playerPosition, snakePositions1, snake2Idx );
      
      Movements.separateSnakes( snakePositions1, snakePositions2, snake1Idx, snake2Idx );
      
      PrintTextEnglish.nextScreen(); // fills the screen with empty lines to
                                     // create space for the next one
                                     
    } // end while
    
  } // end main
  
}
