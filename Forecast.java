import java.io.*;
import java.awt.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This will fetch the Forecast for an area
 * @version 0.1 
 * @author Sam Graham
 */
public class Forecast extends JFrame
{
    /**
     * Main class
     */ 
    public static void main(String[] args)
    {
	new Forecast();
    }


    /**
     * Filename of file containing the zip code of the area
     */ 
    private static final String FILE_NAME = "zip.txt";

    /**
     * Forecast method
     */ 
    public Forecast()
    {
        setTitle("Forecast");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
	String zipCode = "";

        File zipFile = new File(FILE_NAME);
        
        try
	{
	    if (zipFile.exists())
	    {
		FileReader fileReader = new FileReader(FILE_NAME);
                BufferedReader br = new BufferedReader(fileReader);
                zipCode = br.readLine();
	    }
            else
	    {
                zipCode = "83440";
	    }
	}
        catch (Exception e)
	{
	   
	}


        JTextArea myTextArea = new JTextArea(30,30);

        URL url;
	InputStream is = null;
        BufferedReader br = null;

        try
	{
	    url = new URL("http://weather.yahooapis.com/forecastrss?w=2480410&u=f");
	    is  = url.openStream();
            br  = new BufferedReader(new InputStreamReader(is));
            String currentLine = "";
	    while ((currentLine = br.readLine()) != null)
	    {
	       myTextArea.append(currentLine.replaceAll("<[^>]*>", ""));
	       
	    }
	}
        catch(Exception e)
	{
           System.out.println(e.getMessage());
	}
	
        JButton east = new JButton("Sam");
        myTextArea.setLineWrap(true);
        getContentPane().add(BorderLayout.CENTER, myTextArea);
        setSize(500,500);
        setVisible(true);
    }
}
