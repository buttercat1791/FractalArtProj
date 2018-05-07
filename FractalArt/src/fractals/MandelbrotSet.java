package fractals;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;


import java.util.Hashtable;

import javax.swing.JPanel;


/**
 * @author William Kostuch
 * Generates image of the Mandelbrot Set
 */
@SuppressWarnings("serial")
public class MandelbrotSet extends JPanel
{
	int width;
	int height;
	private int iter;
	
	BufferedImage img;
	
	Hashtable<String, BufferedImage> imgTable = new Hashtable<String, BufferedImage>();
	
	//Constructor
	public MandelbrotSet() 
	{}
	
    // Sets iteration number
    public void setIter(int newIter) 
    {
    	iter = newIter + 1;
    }
    
    //Overrides the paintComponent
    @Override
    public void paintComponent(Graphics g)
    {
    	//Checks hashtable to see if image has already been made
    	if(imgTable.containsKey(Integer.toString(iter))) {
    		img = imgTable.get(Integer.toString(iter));
    	} else {
    		img = buildMandelbrot(BufferedImage.TYPE_INT_ARGB);
    		imgTable.put(Integer.toString(iter), img);
    	}
    	g.drawImage(img, 0, 0, this);
    }
    
    
    public BufferedImage buildMandelbrot(int type)
    {
    	int w = getWidth();
    	int h = getHeight();
    	BufferedImage image = new BufferedImage(w, h, type);
    	image = buildImage(image, w, h);
    	return image;
    	
    }
    
    public BufferedImage buildImage(BufferedImage img, int width, int height)
    {	
    	//Base code from https://github.com/joni/fractals/blob/master/mandelbrot/MandelbrotColor.java
    	//Modified for this project
    	int max = iter*2;
    	int black = 0;
        int[] colors = new int[max];
        for (int i = 0; i<max; i++) {
            colors[i] = Color.HSBtoRGB(i/256f, 1, i/(i+8f));
           
    	
	    	for (int row = 0; row < height; row++) 
	    	{
	            for (int col = 0; col < width; col++) 
	            {
	                double c_re = (col - width/2)*(4.0/width);
	                double c_im = (row - height/2)*(4.0/width);
	                double x = 0, y = 0;
	                int iteration = 0;
	                while (((x*x)+(y*y)) <= 4 && iteration < max) 
	                {
	                    double x_new = (x*x)-(y*y)+c_re;
	                    y = (2*x*y)+c_im;
	                    x = x_new;
	                    iteration++;
	                } 
	                if (iteration < max) img.setRGB(col, row, colors[iteration]);
	                else img.setRGB(col, row, black);
	            }
	        }
        }
		return img;

    }
}
