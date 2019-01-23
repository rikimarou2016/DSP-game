import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.sound.sampled.*;

public class designingTheGame extends JPanel implements ActionListener,KeyListener
{
    private final static String IMAGE_NAME = "blueSpaceShip"; // base image name
    private final static String IMAGE_NAME2 = "redSpaceShip";
    protected ImageIcon images[]; // array of images
    protected ImageIcon images2[]; // array of images
    private final int TOTAL_IMAGES = 16; // number of images
    private int currentImage = 0; // current image index
    private int currentImage2 = 0; // current image index
    private final int ANIMATION_DELAY = 50; // millisecond delay
    private int width; // image width
    private int height; // image height
    private Timer animationTimer; // Timer drives animation
    
 

    Timer t = new Timer(5, this);
    int x =180, y = 420 , vel1 = 0; //blue spaceship
    int a =90 , b = 420 , vel2 = 0; //red spaceship
    int recX = 180 ,recY= 420 ; //rectangle for blue spacceship
    int recA=90 , recB= 420 ; //rectangle for red spaceship
    int recO=70 ,recP =420 ;// start location for explosion red spaceship
    int recQ=170 ,recR =420 ;// start location for explosion blue spaceship
 

   // constructor initializes JPanel by loading images
    public designingTheGame()
  {
             t.start();
             addKeyListener(this);
             setFocusable(true);
             setFocusTraversalKeysEnabled(false);

             //Code to display the images of the blue spaceship
             images = new ImageIcon[ TOTAL_IMAGES ];
             for ( int count = 0; count < images.length; count++ )
                {
                   images[ count ] = new ImageIcon( IMAGE_NAME + "." + count + ".png" );
                   setLayout(null);
                   this.setSize(1300,900); 
                 }
         
              //Code to display the images of the red spaceship
                   images2 = new ImageIcon[ TOTAL_IMAGES ];
              for ( int count = 0; count < images2.length; count++ )
                 {
                    images2[ count ] = new ImageIcon( IMAGE_NAME2 + "." + count + ".png" );
                    setLayout(null);
                    this.setSize(1300,900); 
                 }
                 
                      animationTimer = new Timer( ANIMATION_DELAY, this );
                      animationTimer.start(); // start Timer 
  
       } // end constructor

 


             // Function used to trigger the explosion sound when the spaceships have a collision.
        public void AudioSound ()
            {
                try
                     {
                        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("Explosion.wav"));
                        Clip test = AudioSystem.getClip();  
                        test.open(ais);
                        test.start();
          
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
       
                //function used to display the arena and the spaceships
    public void paintComponent( Graphics g )
      {
             super.paintComponent( g ); // call superclass paintComponent
   
             ImageIcon c = new ImageIcon ("nightsky1.png");
             c.paintIcon  (this , g , 0,0); 
 
             Color c3 = Color.white; 
             g.setColor( c3 ); 
             g.drawLine(310, 380, 0,380 ); // start line 

             testForBoundaries();
    
             Rectangle r1 = new Rectangle (recX , recY ,37,36) ;//rectangle r1 is used to detect collison from the blue spaceship
             g.setColor(Color.BLACK ); 
             g.fillRect(r1.x , r1.y ,r1.width,r1.height);
          
             Rectangle r2 = new Rectangle (recA , recB ,37,36) ;//rectangle r2 is used to detect collison from the red spaceship
             g.setColor(Color.BLACK ); 
             g.fillRect(r2.x , r2.y ,r2.width,r2.height);
           
             Rectangle r4 = new Rectangle (360 , 320 , 230,200) ;//collision with the asteroid 
             g.setColor(Color.BLACK ); 
             g.fillRect(r4.x , r4.y ,r4.width,r4.height);
        
             Rectangle r3 = new Rectangle (1000 , 650 , 270,210) ; // collision with the sun 
             g.setColor(Color.BLACK ); 
             g.fillRect(r3.x , r3.y ,r3.width,r3.height);

          
             images[ currentImage ].paintIcon ( this, g, x,y );// Display blue spaceship
             images2[ currentImage2 ].paintIcon ( this, g, a,b );//Display red spaceship
       
       
            // All the images below make the design of the arena
            ImageIcon d = new ImageIcon ("starBattleShip.png");
            d.paintIcon  (this , g , 900,200);
          
            ImageIcon e = new ImageIcon ("saturn.png");
            e.paintIcon  (this , g , 0,0);
         
            ImageIcon f = new ImageIcon ("asteroid_large.png");
            f.paintIcon  (this , g , 200,200);
     
            ImageIcon h = new ImageIcon ("comete1.png");
            h.paintIcon  (this , g , 350,170);
        
            ImageIcon i = new ImageIcon ("comete3.png");
            i.paintIcon  (this , g , 150,600);
     
            ImageIcon j = new ImageIcon ("comete3.png");
            j.paintIcon  (this , g , 1050,100);
     
            ImageIcon k = new ImageIcon ("comete5.png");
            k.paintIcon  (this , g , 900,50);
             
            ImageIcon l = new ImageIcon ("sun1.png");
            l.paintIcon  (this , g , 835,520);
      
            ImageIcon m = new ImageIcon ("galaxie.png");
            m.paintIcon  (this , g , 1150,0);
              
            ImageIcon n = new ImageIcon ("comete.png");
            n.paintIcon  (this , g ,645,300);
        
        
      
           //  collision detection betwen the spaceships
         if ( r1.intersects(r2)||r2.intersects(r1) ) 
             {
           
                 AudioSound ();
                 ImageIcon p = new ImageIcon ("explosion666.png"); //explosion image for red Spaceship
                 p.paintIcon  (this , g ,recO,recP);
         
                 ImageIcon q = new ImageIcon ("explosion666.png");//explosion image for blue Spaceship
                 q.paintIcon  (this , g ,recQ,recR);
             
            
                 //pause the game for 3 secs
              try {
                    Thread.sleep(3000);
                   } 
                   
             catch  (InterruptedException ex) 
                  {
                    Thread.currentThread().interrupt();
                  }
          
          
           // create a new JFrame
            JFrame end = new JFrame();
             end.setBounds(200,250, 600,459);
             end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit game when user click on the cross,
             Container cp = end.getContentPane();               //top right corner of the JFrame
             JPanel endmessage = new JPanel();
            
             
            // Create a Label inside the Jframe(Game over )
             JLabel mes = new JLabel();
             mes.setIcon (new ImageIcon("gameOver.png" ));
             cp.add(endmessage);
             endmessage.add(mes);
             end.setVisible(true);
     
            }  
         
          
           //  collision detection for each spaceships with the asteroid or the sun 
       if    (r1.intersects(r3) || r1.intersects(r4)||r2.intersects(r3)||r2.intersects(r4))
          {
            vel1 = 0;  // reset the spaceship at the original coordinates
            x= 180;
            recX= 180;
            y =420;
            recY=420;
            currentImage = 0 ;// if spaceship hit side of the JPanel reset image to position 0
            
            vel2 = 0;  // reset the spaceship at the original coordinates
            a= 90;
            recA=90;
            b =420;
            recB=420;
            currentImage2 = 0 ;//if spaceship hit side of the JPanel reset image to position 0
             AudioSound ();
           }
     }
    
          
      public void rotateBluespaceshipRight() //rotate blue spaceship to the right
         {
             if( currentImage == 15)
                currentImage = 0;
           
             else
               currentImage = currentImage+1;//rotate image and change image +1 at every press
         }
            
       public void rotateRedspaceshipRight() //rotate red spaceship to the right
          {
            if( currentImage2 == 15)
                currentImage2 = 0;
            
            else
              currentImage2 = currentImage2+1; //rotate image and change image +1 at every press
           }
      
      
       public void rotateBluespaceshipLeft() //rotate blue spaceship to the left
         {
            if (currentImage == 0 )
               currentImage = 15;
          
            else
               currentImage = currentImage-1; //rotate image and change image -1 at every press
         }
            
        public void rotateRedspaceshipLeft() //rotate red spaceship to the left
           {
            if (currentImage2 == 0 )
                currentImage2 = 15;
             
              else
                 currentImage2 = currentImage2-1;//rotate image and change image -1 at every press
           }
           
           
           
           
      public void actionPerformed(ActionEvent e)       
         { 
           //  blue spaceship moving 
         if(currentImage == 0)
             {
                 y = y-2 * vel1;  //movement for blue spaceship
                 recY =y-2*vel1;  // movement for rectange used for collison detection
                 recR =y-2*vel1; // movement for image explosion
            }
            
         else if( currentImage == 1) // if the current image is equal to 1 
            {                           
                 x = x+1 *vel1; // x is equal to x plus one multiply be the velocity "vel1"
                 recX= x+1 *vel1;
                 recQ= x+1 *vel1;
                                
                 y = y - 2 * vel1; //movement for blue spaceship
                 recY=  y - 2 * vel1; // movement for rectange used for collison detection
                 recR=  y - 2 * vel1;  // movement for image explosion   
            }
            
            else if( currentImage == 2 )
            {
                 x = x + 2 * vel1;
                 recX=x + 2 * vel1;
                 recQ=x + 2 * vel1;
                 
                 y = y - 2 * vel1; 
                 recY= y - 2 * vel1;
                 recR= y - 2 * vel1;
                       
            }
            
            else if( currentImage == 3 )
            {
                 x = x + 2 * vel1;
                 recX= x + 2 * vel1;
                 recQ= x + 2 * vel1;
                 
                 y = y - 1 * vel1; 
                 recY= y - 1 * vel1; 
                 recR= y - 1 * vel1;
            }
            
            else if( currentImage == 4 )
            {
                 x = x + 2 * vel1;
                 recX=x + 2 * vel1;
                 recQ=x + 2 * vel1;
                   
            }
            
            else if( currentImage == 5 )
            {
                 x = x + 2 * vel1;
                 recX=x + 2 * vel1;
                  recQ=x + 2 * vel1;
                 
                 y = y + 1 * vel1;
                 recY=y + 1 * vel1;
                 recR=y + 1 * vel1;      
            }
            
            else if( currentImage == 6 )
            {
                 x = x + 2 * vel1;
                 recX=x + 2 * vel1;
                 recQ=x + 2 * vel1;
                 
                 y = y + 2 * vel1; 
                 recY= y + 2 * vel1; 
                 recR= y + 2 * vel1;     
            }
            
            else if( currentImage == 7 )
            {
                 x = x + 1 * vel1;
                 recX=x + 1 * vel1;
                 recQ=x + 1 * vel1;
                 
                 y = y + 2 * vel1; 
                 recY= y + 2 * vel1; 
                 recR= y + 2 * vel1;       
            }
            
            else if( currentImage == 8 )
            {
                y = y + 2 * vel1; 
                recY=y + 2 * vel1; 
                recR=y + 2 * vel1;      
            }
            
            else if( currentImage == 9 )
            {
                 x = x - 1 * vel1;
                 recX=x - 1 * vel1;
                 recQ=x - 1 * vel1;
                 
                 y = y + 2 * vel1;
                 recY=y + 2 * vel1;
                 recR=y + 2 * vel1;
                       
            }
            
            else if( currentImage == 10 )
            {
                 x = x - 2 * vel1;
                 recX=x - 2 * vel1;
                 recQ=x - 2 * vel1;
                 
                 y = y + 2 * vel1;
                 recY=  y + 2 * vel1;
                 recR=  y + 2 * vel1;     
            }
            
            else if( currentImage == 11 )
            {
                 x = x - 2 * vel1;
                 recX=x - 2 * vel1;
                 recQ=x - 2 * vel1;
                 
                 y = y + 1 * vel1;
                 recY =y + 1 * vel1; 
                 recR =y + 1 * vel1;           
            }
            
            else if( currentImage == 12 )
            {
                 x = x - 2 * vel1;
                 recX=  x - 2 * vel1;   
                 recQ=  x - 2 * vel1;  
            }
            
            else if( currentImage == 13 )
            {
                 x = x - 2 * vel1;
                 recX=x - 2 * vel1;
                 recQ=x - 2 * vel1;
                 
                 y = y - 1 * vel1; 
                 recY= y - 1 * vel1;  
                 recR= y - 1 * vel1;    
            }
            
            else if( currentImage == 14 )
            {
                 x = x - 2 * vel1;
                 recX=x - 2 * vel1;
                 recQ=x - 2 * vel1;
                 
                 y = y - 2 * vel1;
                 recY= y - 2 * vel1;  
                 recR= y - 2 * vel1;     
            }
            
            else if( currentImage == 15 )
            {
                 x = x - 1 * vel1;
                 recX=x - 1 * vel1;
                 recQ=x - 1 * vel1;
                 
                 y = y - 2 * vel1;
                 recY= y - 2 * vel1;  
                 recR= y - 2 * vel1;     
            }

                 // Red spaceship moving
   
     
             if(currentImage2 == 0)
            {
                 b = b-2 * vel2; //movement for blue spaceship
                 recB=b-2 * vel2; // movement for rectange used for collison detection
                 recP=b-2 * vel2;// movement for image explosion
            }
          
           else if( currentImage2 == 1)
            {
                 a = a + 2 *vel2;
                 recA =a + 2 *vel2; 
                 recO =a + 2 *vel2; 
                 
                 b = b - 2 * vel2;
                 recB= b - 2 * vel2;
                 recP= b - 2 * vel2;     
            }
            
            else if( currentImage2 == 2 )
            {
                 a = a + 2 * vel2;
                 recA=a + 2 * vel2;
                 recO=a + 2 * vel2;
                 
                 b = b - 2 * vel2; 
                 recB= b - 2 * vel2;
                 recP= b - 2 * vel2;    
            }
            
            else if( currentImage2 == 3 )
            {
                 a = a + 2 * vel2;
                 recA=a + 2 * vel2;
                 recO=a + 2 * vel2;
                 
                 
                 b = b - 1 * vel2;
                 recB=b - 1 * vel2; 
                 recP=b - 1 * vel2;     
            }
            
            else if( currentImage2 == 4 )
            {
                 a = a + 2 * vel2;
                 recA=a + 2 * vel2;
                 recO=a + 2 * vel2;
                     
            }
            
            else if( currentImage2 == 5 )
            {
                 a = a + 2 * vel2;
                 recA=a + 2 * vel2;
                 recO=a + 2 * vel2;
                 
                 b = b + 1 * vel2;
                 recB= b + 1 * vel2;
                 recP= b + 1 * vel2;     
            }
            
            else if( currentImage2 == 6 )
            {
                 a = a + 2 * vel2;
                 recA= a + 2 * vel2;
                 recO= a + 2 * vel2;
                 
                 
                 b = b + 2 * vel2; 
                 recB=b + 2 * vel2;
                 recP=b + 2 * vel2;     
            }
            
            else if( currentImage2 == 7 )
            {
                 a = a + 1 * vel2;
                 recA= a + 1 * vel2;
                 recO= a + 1 * vel2;
                 
                 b = b + 2 * vel2; 
                 recB= b + 2 * vel2;  
                 recP= b + 2 * vel2;    
            }
            
            else if( currentImage2 == 8 )
            {
                 b = b + 2 * vel2; 
                 recB=  b + 2 * vel2; 
                 recP=  b + 2 * vel2;    
            }
            
            else if( currentImage2 == 9 )
            {
                 a = a - 1 * vel2;
                 recA= a - 1 * vel2;
                 recO= a - 1 * vel2;
                 
                 b = b + 2 * vel2;
                 recB=b + 2 * vel2; 
                 recP=b + 2 * vel2;      
            }
            
            else if( currentImage2 == 10 )
            {
                 a = a - 2 * vel2;
                 recA=a - 2 * vel2;
                 recO=a - 2 * vel2;
                 
                 b = b + 2 * vel2;
                 recB=  b + 2 * vel2; 
                 recP=  b + 2 * vel2;    
            }
            
            else if( currentImage2 == 11 )
            {
                 a = a - 2 * vel2;
                 recA=a - 2 * vel2;
                 recO=a - 2 * vel2;
                 
                 b = b + 1 * vel2;
                 recB=  b + 1 * vel2;
                 recP=  b + 1 * vel2;     
            }
            
            else if( currentImage2 == 12 )
            {
                 a = a - 2 * vel2;
                 recA=a - 2 * vel2;
                 recO=a - 2 * vel2;
                   
            }
            
            else if( currentImage2 == 13 )
            {
                 a = a - 2 * vel2;
                 recA=a - 2 * vel2;
                 recO=a - 2 * vel2;
                  
                 b = b - 1 * vel2;
                 recB= b - 1 * vel2; 
                 recP= b - 1 * vel2;     
            }
            
            else if( currentImage2 == 14 )
            {
                 a = a - 2 * vel2;
                 recA=a - 2 * vel2;
                 recO=a - 2 * vel2;
                 
                 b = b - 2 * vel2;
                 recB= b - 2 * vel2; 
                 recP= b - 2 * vel2;       
            }
            
            else if( currentImage2 == 15 )
            {
                 a = a - 1 * vel2;
                 recA=a - 1 * vel2;
                 recO=a - 1 * vel2;
                 
                 b = b - 2 * vel2; 
                 recB=b - 2 * vel2; 
                 recP=b - 2 * vel2;       
            }
   
              repaint();           
      
   }
        
    public void testForBoundaries()
        {       
              // Code for the Blue spaceship entering in collision with any border of the JPanel
              if  (x < 0 ) // if the x value of the spaceship is less than zero trigger the sound ( explosion)
                 { 
                    vel1 = 0;  //then reset the spaceship at the original coordinates
                    x= 180;
                    recX= 180;
                    y =420;
                    recY=420;
                    currentImage = 0 ;// if spaceship hit side of the JPanel reset image to position 0
                    AudioSound ();  
                }
               
             if (x > 1250 )   //if the x value of the spaceship is greater than 1250  trigger the sound ( explosion)
                { 
                   vel1 = 0;          //then reset the spaceship at the original coordinates
                   x= 180;
                    recX= 180;
                   y =420;
                   recY=420;
                   currentImage = 0 ; //if spaceship hit side of the JPanel reset image to position 0
                   AudioSound ();
                }
            
             if  (y < 0 ) // if the y value of the spaceship is less than zero trigger the sound ( explosion)
               { 
                   vel1 = 0;    //then reset the spaceship at the original coordinates.
                   x= 180;
                    recX= 180;
                   y =420;
                   recY=420;
                   currentImage = 0 ;//if spaceship hit side of the JPanel reset image to position 0
                   AudioSound ();
                }

             if  (y > 820 )  //if the y value of the spaceship is greater than 820  trigger the sound ( explosion)
                { 
                   vel1 = 0;     //then reset the spaceship at the original coordinates.
                   x= 180; 
                    recX= 180;
                   y =420;
                   recY=420;
                   currentImage = 0 ;//if spaceship hit side of the JPanel reset image to position 0
                   AudioSound ();
                }
              
          
           // Code for the Red spaceship entering in collision with any border of the JPanel
             
              if  (a < 0 ) // if the a value (X axis ) of the spaceship is less than zero trigger the sound ( explosion)
                { 
                   vel2 = 0;  //then reset the spaceship at the original coordinates
                   a= 90;
                   recA=90;
                   b =420;
                   recB=420;
                   currentImage2 = 0 ;//if spaceship hit side of the JPanel reset image to position 0
                   AudioSound ();
                }
                
             if  (a > 1250 )   //if the a value (X axis ) of the spaceship is greater than 1250  trigger the sound ( explosion)
                { 
                   vel2 = 0;          //then reset the spaceship at the original coordinates
                   a= 90;
                    recA=90;
                   b =420;
                   recB=420;
                   currentImage2 = 0 ;//if spaceship hit side of the JPanel reset image to position 0
                   AudioSound ();
                 }
            
              if  (b < 0 ) // if the b value (Y axis ) of the spaceship is less than zero trigger the sound ( explosion)
                 { 
                    vel2 = 0;    //then reset the spaceship at the original coordinates.
                    a= 90;
                     recA=90;
                    b =420;
                    recB=420;
                    currentImage2 = 0 ;//if spaceship hit side of the JPanel reset image to position 0
                    AudioSound ();
                  }

              if  (b > 820 )  //if the b (Y axis ) value of the spaceship is greater than 820  trigger the sound ( explosion)
                 { 
                    vel2 = 0;     //then reset the spaceship at the original coordinates.
                    a= 90; 
                     recA=90;
                    b =420;
                    recB=420;
                    currentImage2 = 0 ;//if spaceship hit side of the JPanel reset image to position 0
                    AudioSound ();
                  }
         }
           

       public void accelerateBlueSpaceship() 
          {
             vel1= vel1 + 1;
          }

        public void slowDownBlueSpaceship ()
          {
            vel1= vel1 - 1;
          }
      
       public void accelerateRedSpaceship() 
          {
           vel2= vel2 + 1;
          }

        public void slowDownRedSpaceship ()
          {
            vel2= vel2 - 1;
          }


    public void keyPressed(KeyEvent e) 
          { 
              int keyCode = e.getKeyCode();
        
             // keyEvent for the blue spaceship 
            if( keyCode == KeyEvent.VK_UP) // press UP arrow to go up 
                {
                   accelerateBlueSpaceship() ;
                }
       
            if (keyCode == KeyEvent.VK_DOWN)// press DOWN arrow to go down
    
               {   
                  slowDownBlueSpaceship ();
               }

            if (keyCode == KeyEvent.VK_LEFT) // press LEFT arrow to go left
               {
                  rotateBluespaceshipLeft();
               }
              
           if (keyCode == KeyEvent.VK_RIGHT) // press RIGHT arrow to go right
               {
                rotateBluespaceshipRight();
               } 
              
          
            // keyEvent for the red spaceship   
           if( keyCode == KeyEvent.VK_W)//  Press W to go up or accelerate
               {
                accelerateRedSpaceship() ;
               }
     
            if (keyCode == KeyEvent.VK_SPACE) // Press G to go down or slow down
               {  
                  slowDownRedSpaceship();
               }

            if (keyCode == KeyEvent.VK_A) //Press A to go left
                {
                  rotateRedspaceshipLeft();
                 }
            
            if (keyCode == KeyEvent.VK_D) //press D to go right
               {
                 rotateRedspaceshipRight();
               }
     
        }

            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}

 }





             


