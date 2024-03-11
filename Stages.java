package gameOfLife3;

import java.awt.Point;

public class Stages {
  
  
  // when cought by snake, Guru is positioned next to the player
  static void coughtBySnake( Point playerPosition, Point guruPosition, int playgroundWidth ) {
    
    if ( playerPosition.x == playgroundWidth )
      guruPosition.setLocation( playerPosition.x - 1, playerPosition.y );
    else
      guruPosition.setLocation( playerPosition.x + 1, playerPosition.y );
    PrintTextEnglish.nextScreen();
  }
  
}
