package fractals;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.util.Hashtable;

public class JuliaSet extends JPanel
{
	int width;
	int height;
	private int iter;
	
	BufferedImage img;
	
	Hashtable<String, BufferedImage> imgTable = new Hashtable<String, BufferedImage>();

	public JuliaSet() {}
	
    // Reruns constructor function code to refresh the image
    public void setIter(int newIter) 
    {
    	iter = newIter + 1;
    }
    
    //Overrides the paintComponent
    @Override
    public void paintComponent(Graphics g)
    {
    	if(imgTable.containsKey(Integer.toString(iter))) {
    		img = imgTable.get(Integer.toString(iter));
    	} else {
    		img = buildJulia(BufferedImage.TYPE_INT_ARGB);
    		imgTable.put(Integer.toString(iter), img);
    	}
    	g.drawImage(img, 0, 0, this);
    }
    

    public BufferedImage buildJulia(int type)
    {
    	width = getWidth();
    	height = getHeight();
    	
    	BufferedImage image = new BufferedImage(width, height, type);
    	image = buildImage(image, width, height);
    	return image;
    }
    
    //Build image by looping through each pixel and coloring
	/*
	 * This code taken from https://rosettacode.org/wiki/Julia_set#Java 
	 * and changed to use complex numbers
	 */
    private BufferedImage buildImage(BufferedImage image, int width, int height) {
    	int maxIter = iter*10;
        for (int x = 0; x < width; x++) 
        {
            for (int y = 0; y < height; y++) 
            {
                ComplexNumbers v = new ComplexNumbers(
                		(1.5 * (x - width / 2) / (0.5 * width)),
                		((y - height / 2) / (0.5 * height)));
                float i = maxIter;
                while (v.norm() < 4 && i > 0) 
                {
                    v = new ComplexNumbers(
                    		v.x * v.x - v.y * v.y + -0.7,
                    		2.0 * v.x * v.y + 0.27015);
                    
                    i--;
                }
                int col = Color.HSBtoRGB((maxIter / i) % 1, 1, i > 0 ? 1 : 0);
                image.setRGB(x, y, col);
        //End of taken code
            }
        }
        return image;
    }
}

