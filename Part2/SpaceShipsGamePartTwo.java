import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.sound.sampled.*;

 class SpaceShipsGamePartTwo extends JFrame
 {
       public SpaceShipsGamePartTwo()
        {
           this.setSize(1300,900); 
           this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         
           Container cp = this.getContentPane();
         
           designingTheGame game = new designingTheGame();
        
           cp.add(game);
           
          
  
       }
        
  

    public static void main(String args[])
       {
            SpaceShipsGamePartTwo myFrame = new SpaceShipsGamePartTwo();
            myFrame.setVisible(true);
       
        
           
        try
         {
           AudioInputStream ais = AudioSystem.getAudioInputStream(new File("theBelkanWar.wav"));
           Clip test = AudioSystem.getClip();  

           test.open(ais);
           test.start();
           test.loop(10);
         
           while (!test.isRunning())
                  Thread.sleep(10);
           while (test.isRunning())
                  Thread.sleep(10);
                  test.close();
                
         }
       
       catch(Exception ex)
         {
            ex.printStackTrace();
         }

       
       
      }
    
   

}

