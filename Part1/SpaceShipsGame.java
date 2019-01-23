import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


 class SpaceShipsGame extends JFrame
 {
    public SpaceShipsGame()
    {
         this.setSize(1000,800); 
         this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         
         Container cp = this.getContentPane();
         
         spaceShipAnimation game = new spaceShipAnimation();
         cp.add(game);
           
    }
 
 
    public static void main(String args[])
    {
        SpaceShipsGame myFrame = new SpaceShipsGame();

        myFrame.setVisible(true);
    }
}


