package sub;

//Description: This program draws a Submarine diving in an an ocean and 2 random fishes moving beside.
/*
* The program reacts as follows:
* 1) Makes the submarine stop diving deeper if you are at a depth of 1200feets.
* 2) Changes the color of the ocean, subamrine and fishes as you go below 659feets.
* 3) Dives deeper by 10feets with a left click and ascends by 10feets with a right click.
*/ 


//File Name: Sub

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Sub extends JPanel 
{

   int x_move=0;
   int y_move=0;
   int speed=1;

 boolean destroyed=false;
 Color fishColor = Color.lightGray;
 Color submarineColor=Color.yellow;
 Color portholesColor=Color.white;
 Color fish_eyesColor=Color.yellow;
 Color fish_mouthColor=Color.white;
 private Timer timer;
  Random number =new Random();
 
 int currentx=0;    //a starting x location variant, this will adjust as the mouse moves.
 int currenty=0;    //a starting y location variant, this currently is just a placeholder for mouse movement in the y plane.
 final  int MAX_DEPTH=1200;// Maximum depth the submarine is allowed to dive.
 

 int depth=20;// This represents the initial deph of the sunmarine.
 int move=0;

 
/*The constructer method sets the following: polygon listener, mouse listener, mouse motion listener, sets the 
* background color for the JFrame, and sets the dimensions of the JFrame*/
public Sub()
{      
 PolygonPanel listener = new PolygonPanel();  //create a polygon listener
 addMouseListener(listener);  //listen for mouse changes 
 addMouseMotionListener(listener);  //listen for mouse movement
 setBackground(Color.blue);   //set the background of the JFrame to blue
 setPreferredSize(new Dimension(1000,400)); //set default size of JFrame window 
 timer= new Timer(50, generateTimerActionListener());
timer.start();// starts the timer
}

/*This method will be called every time we invoke repaint and it will redraw the graphics window*/
public void paintComponent(Graphics page)
{
 super.paintComponent(page);
 
 page.setColor(submarineColor);  //set submarine color
 //what follows are two arrays with x and y coordinates for the yellow Submarine
 //The currentx and currenty values will vary as the mouse event will send over different mouse coordinates which
 //affect the values in the array, this gives the ability of motion--that is we redraw the image in a different
 //location every time this method is called.  With chaging currentx and currenty values coming in, we can change the
 //location of the submarine relative to where the mouse is in the JFrame.
if (!destroyed)    
{
 //creates and draw submarine
 int[] xloc_sub = {currentx+489,currentx+374,currentx+375,currentx+368,currentx+368,currentx+368,
                   currentx+368,currentx+368,currentx+368,currentx+366,currentx+366,
                  currentx+341,currentx+341,currentx+356,currentx+357,currentx+357,currentx+357,currentx+357,currentx+357,currentx+357,
                   currentx+357,currentx+357,currentx+357,currentx+357,currentx+357,
                   currentx+ 357,currentx+357,currentx+357,currentx+358,currentx+358,currentx+350,currentx+337,currentx+327,currentx+324,
                    currentx+303,currentx+290,currentx+291,currentx+247,currentx+228,currentx+223,currentx+171,currentx+67,
                      currentx+36,currentx+17,currentx+11,currentx+1,currentx+2,currentx+15,currentx+18,
                    currentx+34,currentx+54,currentx+160,currentx+190,currentx+462,
                  currentx+498,currentx+510,currentx+508,currentx+492,currentx+375,currentx+222 };
 int[] yloc_sub = {currenty+103,currenty+99,currenty+62,currenty+48,currenty+48,currenty+48,currenty+48,currenty+48,currenty+48
                   ,currenty+46,currenty+46,currenty+33,currenty+17,currenty+14,currenty+10,currenty+10,currenty+10,
                      currenty+10,currenty+10,currenty+10,currenty+10,currenty+10,currenty+10,currenty+10,currenty+10,
                    currenty+10,currenty+10,currenty+10,currenty+7,currenty+11,currenty+0,currenty+0,currenty+9,currenty+33,
                      currenty+37,currenty+58,currenty+69,
                      currenty+68,currenty+82,
                      currenty+102,currenty+102,currenty+121,currenty+152,currenty+150,currenty+128,currenty+132,currenty+192,currenty+189,
                     currenty+171,currenty+169,currenty+193,currenty+217,currenty+220,currenty+218,currenty+192,currenty+162,currenty+118,currenty+102,
                 currenty+102,currenty+101};
 page.fillPolygon(xloc_sub,yloc_sub,xloc_sub.length);  //this does the actual drawing of the yellow parts of the submarine 

 

 
 // The following draws the 8 portholes of the submarine
 page.setColor(portholesColor);
 page.fillOval(currentx+351,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+305,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+259,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+213,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+167,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+121,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+75,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+397,currenty+140,40,40);
 
 


// The following draws the first fish
 page.setColor(fishColor);// Set color of the fish
 page.fillOval(130+x_move,70+y_move,150,50);// draws fish
 page.setColor(fishColor);// set color of the tail
 page.fillOval(271+x_move,80+y_move,40,40);//draws the tail
 page.setColor(fish_eyesColor);//set color of the eye 
 page.fillOval(150+x_move,75+y_move,15,20);// draws the eyes
 page.setColor(fish_mouthColor);// set color of the mouth
 page.drawArc(132+x_move,75+y_move,75,30,190,110);//draws the mouth
 page.setColor(fishColor);// sets color of the fin
 page.fillOval(170+x_move,50+y_move,70,40);// draws the fin
 
 
 
 // The following draws the second fish
 page.fillOval(430+x_move,70+y_move,150,50);// draws fish
 page.setColor(fishColor);// set color of the tail
 page.fillOval(571+x_move,80+y_move,40,40);//draws the tail
 page.setColor(fish_eyesColor);//set color of the eye 
 page.fillOval(450+x_move,75+y_move,15,20);// draws the eyes
 page.setColor(fish_mouthColor);// set color of the mouth
 page.drawArc(432+x_move,75+y_move,75,30,190,110);//draws the mouth
 page.setColor(fishColor);// sets color of the fin
 page.fillOval(470+x_move,50+y_move,70,40);// draws the fin
 
   if(y_move>100){
       y_move=0;
       y_move=number.nextInt(200);
   }
 

 repaint();
 
 

    
 

}//end if not destroyed
else  //***************************** it done blow 1200feets *********************************************
{
  

 currentx=0;  
 currenty=0;
 setBackground(Color.BLUE.darker().darker());
      fishColor = Color.gray;
      submarineColor= Color.yellow.darker().darker();
      portholesColor=Color.white.darker().darker(); 
      fish_eyesColor=Color.yellow.darker().darker();
      fish_mouthColor=Color.white.darker().darker();
    
 int[] xloc_sub = {currentx+489,currentx+374,currentx+375,currentx+368,currentx+368,currentx+368,
                   currentx+368,currentx+368,currentx+368,currentx+366,currentx+366,
                  currentx+341,currentx+341,currentx+356,currentx+357,currentx+357,currentx+357,currentx+357,currentx+357,currentx+357,
                   currentx+357,currentx+357,currentx+357,currentx+357,currentx+357,
                   currentx+ 357,currentx+357,currentx+357,currentx+358,currentx+358,currentx+350,currentx+337,currentx+327,currentx+324,
                    currentx+303,currentx+290,currentx+291,currentx+247,currentx+228,currentx+223,currentx+171,currentx+67,
                      currentx+36,currentx+17,currentx+11,currentx+1,currentx+2,currentx+15,currentx+18,
                    currentx+34,currentx+54,currentx+160,currentx+190,currentx+462,
                  currentx+498,currentx+510,currentx+508,currentx+492,currentx+375,currentx+222 };
 
 
 int[] yloc_sub = {currenty+103,currenty+99,currenty+62,currenty+48,currenty+48,currenty+48,currenty+48,currenty+48,currenty+48
                   ,currenty+46,currenty+46,currenty+33,currenty+17,currenty+14,currenty+10,currenty+10,currenty+10,
                      currenty+10,currenty+10,currenty+10,currenty+10,currenty+10,currenty+10,currenty+10,currenty+10,
                    currenty+10,currenty+10,currenty+10,currenty+7,currenty+11,currenty+0,currenty+0,currenty+9,currenty+33,
                      currenty+37,currenty+58,currenty+69,
                      currenty+68,currenty+82,
                      currenty+102,currenty+102,currenty+121,currenty+152,currenty+150,currenty+128,currenty+132,currenty+192,currenty+189,
                     currenty+171,currenty+169,currenty+193,currenty+217,currenty+220,currenty+218,currenty+192,currenty+162,currenty+118,currenty+102,
                 currenty+102,currenty+101};
 page.fillPolygon(xloc_sub,yloc_sub,xloc_sub.length);  //this does the actual drawing of the yellow parts of the submarine
 
 
 
 
 // The following draws the 8 portholes of the submarine
 page.setColor(portholesColor);
 page.fillOval(currentx+351,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+305,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+259,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+213,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+167,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+121,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+75,currenty+140,40,40);
  page.setColor(portholesColor);
 page.fillOval(currentx+397,currenty+140,40,40);
 
 

 repaint();
 

}  //end else it is below 1200feets
} //end paintComponent

public ActionListener
 generateTimerActionListener(){
 ActionListener timerListener=new ActionListener(){
   
   public void actionPerformed(ActionEvent e){
     x_move++;
     y_move++;
     if(x_move>400){
       x_move=-200;
       x_move=number.nextInt(500)-200;
     }
     else
     {
       x_move+=speed;
     }

   }
 };
 return timerListener;
}



/*The following method is the listener for polygon changes*/
private class PolygonPanel implements MouseListener, MouseMotionListener
{
 public void mousePressed(MouseEvent event){}   //unused event
 public void mouseDragged(MouseEvent event){}   //unused event
 
 //The next method will determine what to do when a mouse button is clicked
 //In this case, a left click will cause the sub to dive 10feets deeper and a rightclick will make the sub ascends 10feets.
 public void mouseClicked(MouseEvent event)
 {
   if (depth<(MAX_DEPTH+1))
   {
  if (event.getButton() == MouseEvent.BUTTON1)  //left mouse button (Button 1) will dive deeper
   if (depth<(MAX_DEPTH+1))
  {
    if (depth<1200)
    {
     System.out.println ("Submarine descends by 10feets"+ " New depth: "+(depth+10)+"feets");  //cant go below first gear
     depth+=10;
    }
  }
  if(depth>1200){
    depth=1200;
  }
  if (event.getButton() == MouseEvent.BUTTON3)  //right mouse button (Button 3) will ascend
    if (depth<MAX_DEPTH)
  { 
     if (depth>0)
     {
     System.out.println ("Submarine ascends by 10 feets"+" New depth: "+(depth-10)+"feets");  //cant go above 4th gear
     depth-=10;
     }
     
  }
  if(depth<=0){
    depth=0;
  }
     
   }
    if (depth<(MAX_DEPTH+1))
     System.out.println ("The depth of the submarine is: "+depth+"feets ");
   if (depth>MAX_DEPTH)
     {
       System.out.println ("You're already at "+depth+"feets");
       setBackground(Color.BLUE.darker());
       destroyed=true;
     }  
   else if ((depth>=660)&&(depth<=1200))
    {
      setBackground(Color.BLUE.darker().darker());
      fishColor = Color.gray;
      submarineColor= Color.yellow.darker().darker();
      portholesColor=Color.white.darker().darker(); 
      fish_eyesColor=Color.yellow.darker().darker();
      fish_mouthColor=Color.white.darker().darker();
    }
   else
     setBackground(Color.blue);

   repaint();
 } //end mouseClicked

 public void mouseReleased(MouseEvent event) {}  //unused event
 public void mouseEntered(MouseEvent event) {}   //unused event
 public void mouseExited(MouseEvent event) {}    //unused event

 //The next method will determine what to do when a mouse is moved
 //In this case that constantly displays the depth of the submarine
 public void mouseMoved(MouseEvent event) 
 {
   
   if (depth<(MAX_DEPTH+1))
     System.out.println ("The depth of the submarine is: "+depth+"feets ");
   if (depth>MAX_DEPTH)
     {
       System.out.println ("You are at the maximum depth of "+depth+ "feets"+"you can't go below"+MAX_DEPTH);
       setBackground(Color.BLUE.darker());
       destroyed=true;
     }  
    currentx=event.getPoint().x;//grab the current mouse location and set currentx to that location, this will allow the submarine to move in relation to where the mouse is moving
    currenty=event.getPoint().y;//grab the current mouse location and set currenty to that location, this will allow the submarine to move in relation to where the mouse is moving
    repaint();  //redraw the image  -  this automatically calls the paintComponent method to redraw the frame
   
 } //end mouseMoved

}  //end PolygonPanel
} //end Sub
 