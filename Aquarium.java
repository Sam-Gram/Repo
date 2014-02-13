import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Vector;
import java.lang.Math;

/**
 *
 *
 * <p>I got the basic idea from Steven Holzner's "Java After Hours"</p>
 * @author Sam Graham
 */
public class Aquarium extends Frame implements Runnable
{
    MediaTracker tracker;
    Image aquariumImage;
    Image memoryImage;
    Image[] fishImages = new Image[2];  
    Graphics memoryGraphics;
    Thread thread;
    private static final int NUM_FISH = 12;
    private static final int SLEEP_TIME = 110;
    Vector<Fish> fishes = new Vector<Fish>();
    boolean  runOK = true;  
  
    /**
     * Constructor
     */
    Aquarium()
    {
       
	setTitle("The Aquarium");

        tracker = new MediaTracker(this);

        fishImages[0] = Toolkit.getDefaultToolkit().getImage("fish1.gif");

        tracker.addImage(fishImages[0], 0);

        fishImages[1] = Toolkit.getDefaultToolkit().getImage("fish2.gif");

        tracker.addImage(fishImages[1], 0);

        aquariumImage = Toolkit.getDefaultToolkit().getImage("bubbles.gif");

        tracker.addImage(aquariumImage, 0);
       
        try {
            tracker.waitForID(0);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // Variably set size to image size
        setSize(aquariumImage.getWidth(this), aquariumImage.getHeight(this));

        setResizable(false);

        setVisible(true); 

        memoryImage = createImage(getSize().width, getSize().height);
        memoryGraphics = memoryImage.getGraphics();


        thread = new Thread(this);
        thread.start();


        this.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent windowEvent)
		{
                    runOK = false;
		    System.exit(0);
		}
        });

        
    }

    public void update(Graphics g)
    {
	memoryGraphics.drawImage(aquariumImage, 0,0, this);

        for (int i = 0; i < NUM_FISH; i++)
	{
	    ((Fish) fishes.elementAt(i)).drawFishImage(memoryGraphics);
	}

        g.drawImage(memoryImage, 0, 0, this);
    }



    public static void main(String[] args)
    {
	new Aquarium();
    }

    public void run()
    {
	Rectangle edges = new Rectangle(0 + getInsets().left, 0 
            + getInsets().top, getSize().width - (getInsets().left
            + getInsets().right), getSize().height - (getInsets().top 
            + getInsets().bottom));

        for (int i = 0; i < NUM_FISH; i++)
	{
	    fishes.add(new Fish(fishImages[0], fishImages[1], edges, this));
            try
	    {
		Thread.sleep(20);
	    }
            catch(Exception e)
	    {
               
	    }
	}
 
        Fish fish;

        while(runOK)
	{
            for (int i = 0; i < NUM_FISH; i++)
	    {
		fish = (Fish) fishes.elementAt(i);
                fish.swim();
	    }

            try
	    {
               Thread.sleep(SLEEP_TIME);
	    }
            catch (Exception e)
	    {
	    }

            repaint();
	}

    }

    class Fish
    {
	Component tank;
        Image image1;
        Image image2;
        Point location;
        Point velocity;
        Rectangle edges;
        Random random;

        public Fish(Image pImage1, Image pImage2, Rectangle pEdges, Component pTank)
	{
	    random = new Random(System.currentTimeMillis());
            tank = pTank;
            image1 = pImage1;
            image2 = pImage2;
            edges = pEdges;
            location = new Point(100 + Math.abs(random.nextInt() % 300), 100 + (Math.abs(100 + random.nextInt()) % 100));
    
            velocity = new Point(random.nextInt() % 8, random.nextInt() % 8);
             
	}

        public void drawFishImage(Graphics g)
	{
            if (velocity.x > 0)
	    {
		g.drawImage(image1, location.x, location.y, tank);
                
	    }
            else
	    {
                g.drawImage(image2, location.x, location.y, tank);
	    }
	}


        public void swim()
	{
	    if (random.nextInt() % 7 <= 1)
	    {
		velocity.x += random.nextInt() % 4;
   
                velocity.x = Math.min(velocity.x, 8);
                velocity.x = Math.max(velocity.x, -8);

                velocity.y += random.nextInt() % 4;
   
                velocity.y = Math.min(velocity.y, 8);
                velocity.y = Math.max(velocity.y, -8);                
	    }

            location.x += velocity.x;
            location.y += velocity.y;

            if (location.x < edges.x)
	    {
                location.x = edges.x;
                velocity.x = -velocity.x;
	    }

            if ((location.x + image1.getWidth(tank)) > (edges.x + edges.width))
	    {
		location.x = edges.x + edges.width - image1.getWidth(tank);
                velocity.x = -velocity.x;
	    }

            if (location.y < edges.y)
	    {
                location.y = edges.y;
                velocity.y = -velocity.y;
	    }

            if ((location.y + image1.getHeight(tank)) > (edges.y + edges.height))
	    {
		location.y = edges.y + edges.height - image1.getHeight(tank);
                velocity.y = -velocity.y;
	    }
	}
    }
}
