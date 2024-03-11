package gameOfLife3;

import java.awt.Point;
import java.util.Scanner;

@SuppressWarnings( "resource" )
public class Movements {
  
  // player moves; also across the playground boarders
  static void movePlayer( int playgroundHight, int playgroundWidth, Point playerPosition ) {
    
    String move = new Scanner( System.in ).nextLine();
    switch ( move ) {
      case "w":
        if ( playerPosition.y == 1 )
          playerPosition.y = playgroundHight;
        else
          playerPosition.y--;
        break;
      case "s":
        if ( playerPosition.y == playgroundHight )
          playerPosition.y = 1;
        else
          playerPosition.y++;
        break;
      case "a":
        if ( playerPosition.x == 1 )
          playerPosition.x = playgroundWidth;
        else
          playerPosition.x--;
        break;
      case "d":
        if ( playerPosition.x == playgroundWidth )
          playerPosition.x = 1;
        else
          playerPosition.x++;
        break;
      default:
        PrintTextEnglish.nextScreen();
    }
  }
  
  // Snake1 moves towards the player
  static int moveSnake1( Point playerPosition, Point[] snakePositions1, int snake1Idx ) {
    
    Point snake1Head = new Point( snakePositions1[snake1Idx].x, snakePositions1[snake1Idx].y );
    
    if ( playerPosition.x < snake1Head.x )
      snake1Head.x--;
    else if ( playerPosition.x > snake1Head.x )
      snake1Head.x++;
    if ( playerPosition.y < snake1Head.y )
      snake1Head.y--;
    else if ( playerPosition.y > snake1Head.y )
      snake1Head.y++;
      
    // next element in the circular buffer is initialized with the new snake
    // head position
    snake1Idx = ( snake1Idx + 1 ) % snakePositions1.length;
    snakePositions1[snake1Idx] = snake1Head;
    return snake1Idx;
  }
  
  // Snake2 moves towards the player
  static int moveSnake2( Point playerPosition, Point[] snakePositions2, int snake2Idx ) {
    
    Point snake2Head = new Point( snakePositions2[snake2Idx].x, snakePositions2[snake2Idx].y );
    
    if ( playerPosition.x < snake2Head.x )
      snake2Head.x--;
    else if ( playerPosition.x > snake2Head.x )
      snake2Head.x++;
    if ( playerPosition.y < snake2Head.y )
      snake2Head.y--;
    else if ( playerPosition.y > snake2Head.y )
      snake2Head.y++;
      
    // next element in the circular buffer is initialized with the new snake
    // head position
    snake2Idx = ( snake2Idx + 1 ) % snakePositions2.length;
    snakePositions2[snake2Idx] = snake2Head;
    return snake2Idx;
  }
  
  static void separateSnakes( Point[] snakePositions1, Point[] snakePositions2, int snake1Idx, int snake2Idx ) {
    Point snakeRestart = new Point( 19, 10 );
    if ( snakePositions1[snake1Idx].equals( snakePositions2[snake2Idx] ) ) {
      snakePositions2[snake2Idx].setLocation( snakeRestart );
      for ( int i = 0; i < snakePositions2.length; i++ ) {
        if ( i != snake2Idx )
          snakePositions2[i] = null;
      }
    }
  }
  
}
