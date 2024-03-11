package gameOfLife3;

import java.awt.Point;
import java.util.Arrays;

public class GUI {
  
  static void randomCoords( Point p, int playgroundWidth, int playgroundHight ) {
    int randomX = ( int ) ( Math.random() * playgroundWidth + 1 );
    int randomY = ( int ) ( Math.random() * playgroundHight + 1 );
    p.setLocation( randomX, randomY );
  }
  
  static void printScreen( int playgroundHight, int playgroundWidth, int snake1Idx, int snake2Idx, Point playerPosition,
      Point[] snakePositions1, Point[] snakePositions2, Point atGuruPosition, Point goldPosition1, Point goldPosition2,
      Point hillPosition1, Point hillPosition2, Point hillPosition3, Point guruPosition, Point keyPosition,
      Point heartPosition, Point starPosition, boolean gold1Collected, boolean gold2Collected, boolean rich,
      boolean keyOwner, boolean heart, boolean enlightened, boolean snakeGotU ) {
    for ( int y = 1; y <= playgroundHight; y++ ) {
      for ( int x = 1; x <= playgroundWidth; x++ ) {
        Point boarderTop = new Point( x, 1 );
        Point boarderBottom = new Point( x, playgroundHight );
        Point boarderLeft = new Point( 1, y );
        Point boarderRight = new Point( playgroundWidth, y );
        if ( playerPosition.distance( x, y ) == 0 ) {
          if ( rich || playerPosition.equals( atGuruPosition ) && !( keyOwner || heart ) )
            System.out.print( "😀" );
          else if ( keyOwner && !heart )
            System.out.print( "😃" );
          else if ( heart && !enlightened )
            System.out.print( "😍" );
          else if ( enlightened )
            System.out.print( "👼" );
          else if ( snakeGotU )
            System.out.print( "😢" );
          else
            System.out.print( "🙂" );
        }
        else if ( Arrays.asList( snakePositions1 ).contains( new Point( x, y ) )
            || Arrays.asList( snakePositions2 ).contains( new Point( x, y ) ) ) {
          if ( enlightened )
            System.out.print( "🔥" );
          else if ( snakePositions1[snake1Idx].equals( new Point( x, y ) )
              || ( snakePositions2[snake2Idx].equals( new Point( x, y ) ) ) )
            System.out.print( "😈" );
          else
            System.out.print( "🐍" );
        }
        else if ( goldPosition1.distance( x, y ) == 0 || goldPosition2.distance( x, y ) == 0 )
          System.out.print( "💰" );
        else if ( guruPosition.distance( x, y ) == 0 )
          System.out.print( "👳🏽" );
        else if ( hillPosition1.distance( x, y ) == 0 || hillPosition2.distance( x, y ) == 0
            || hillPosition3.distance( x, y ) == 0 )
          System.out.print( "⛰" );
        else if ( keyPosition.distance( x, y ) == 0 )
          System.out.print( "🔑" );
        else if ( heartPosition.distance( x, y ) == 0 )
          System.out.print( "❤️" );
        else if ( starPosition.distance( x, y ) == 0 )
          System.out.print( "⭐️" );
        else if ( boarderTop.distance( x, y ) == 0 || boarderBottom.distance( x, y ) == 0
            || boarderLeft.distance( x, y ) == 0 || boarderRight.distance( x, y ) == 0 )
          System.out.print( "🌫" );
        else
          System.out.print( "🌴" );
      }
      System.out.println();
    }
  }
  
}
