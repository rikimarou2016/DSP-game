import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class spaceShipAnimation extends JPanel implements ActionListener
{
  private final static String IMAGE_NAME = "blueSpaceShip"; // base image name

  protected ImageIcon images[]; // array of images
  private final int TOTAL_IMAGES = 16; // number of images
  private int currentImage = 0; // current image index
  private final int ANIMATION_DELAY = 50; // millisecond delay
  private int width; // image width
  private int height; // image height
  private Timer animationTimer; // Timer drives animation

  // constructor initializes LogoAnimatorJPanel by loading images
  public spaceShipAnimation()
  {
      images = new ImageIcon[ TOTAL_IMAGES ];

      for ( int count = 0; count < images.length; count++ )
      {
          //images[ count ] = new ImageIcon( getClass().getResource(
            //"images//" + IMAGE_NAME + count + ".png" ) );
   
           images[ count ] = new ImageIcon( IMAGE_NAME + "." + count + ".png" );
           
   
          setLayout(null);
          this.setSize(1000,400);
         
      }
      
      animationTimer = new Timer( ANIMATION_DELAY, this );
      animationTimer.start(); // start Timer
           
   } // end constructor



   // display current image
   public void paintComponent( Graphics g )
   {
      super.paintComponent( g ); // call superclass paintComponent
     
      
      
      images[ currentImage ].paintIcon( this, g, 150, 150 );

      // set next image to be drawn
      currentImage = ( currentImage + 1 ) % TOTAL_IMAGES;
   }

   public void  actionPerformed(ActionEvent e)
   {       
       repaint();
   }
  
}



