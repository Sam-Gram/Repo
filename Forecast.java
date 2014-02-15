import java.io.*;
import java.awt.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 */
public class Forecast extends JFrame
{
    public static void main(String[] args)
    {
	new Forecast();
    }


    private static final String FILE_NAME = "zip.txt";

    public Forecast()
    {
        setTitle("Forecast");
        setSize(500,500);
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


        JTextArea myTextArea = new JTextArea();
        myTextArea.setLineWrap(true);



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
	       myTextArea.append(currentLine);
               System.out.println("Hello");
	    }
	}
        catch(Exception e)
	{
        
	}
	
       
        myTextArea.setEditable(true);
        getContentPane().setLayout(null);
        getContentPane().add(myTextArea);

        setVisible(true);
    }
}
