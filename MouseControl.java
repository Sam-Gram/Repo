import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 * A class which explors the use of 
 * the Robot class
 *
 *
 * @version 0.1, 2/11/2014
 * @author Sam Graham
 *
 *
 * <p>
 *    This is still a test
 * </p>
 * <a href="http://www.javaprogrammingforums.com/java-se-api-tutorials/214-java-program-contol-computer-mouse-using-awt-robot-events.html">Got most ideas from here</a>
 * 
 */
public class MouseControl implements Runnable
{

   /**
    *
    *
    * @params The command-line arguments
    */
   public static void main(String[] args)
   {
       new MouseControl().run();
   }

   /**
    * Runs the class
    */
   public void run()
   {
       try
       {
          Robot robot = new Robot();
          
          // Move mouse over the screen
          robot.mouseMove(300, 500);
          // Click and release mouse
          robot.mousePress(InputEvent.BUTTON1_MASK);
          robot.mouseRelease(InputEvent.BUTTON1_MASK);

          // Scroll down
          robot.mouseWheel(-100);

          // Move Mouse down the screen
          int x = 0;
          int y = 0;
          for (; x < 500; x++, y++)
	  {
	      robot.mouseMove(x, y);
              robot.delay(10);
	  }
       }
       catch (Exception e)
       {
       }
   }

}