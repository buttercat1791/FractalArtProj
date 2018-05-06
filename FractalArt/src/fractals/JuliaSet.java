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
	
	//private Hashtable<Dimension, BufferedImage> imgTable = new Hashtable();
	//private Hashtable<String, Dimension> dimTable = new Hashtable();
	private Hashtable<Integer, Dimension> iTable = new Hashtable();
	private Hashtable<Dimension, BufferedImage> imgTable = new Hashtable();

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
    	img = buildJulia(BufferedImage.TYPE_INT_ARGB);
    	g.drawImage(img, 0, 0, this);
    }
    
    // TODO: Method is perceivable faster with just a Hashtable of images, but using two Hashtables,
    // one of images and one of dimensions, has no perceivable benefit.
    public BufferedImage buildJulia(int type)
    {
    	width = getWidth();
    	height = getHeight();
    	
    	Dimension dim = new Dimension(width, height);
    	
    	// c is never used
    	//ComplexNumbers c = new ComplexNumbers(-0.7, 0.27015);
    	BufferedImage image = new BufferedImage(width, height, type);
    	
    	
    	//If image for right iteration + dimension is in table
    	if(iTable.get(iter) == dim)
    	{
    		image = imgTable.get(iTable.get(iter));
    	}
    	else 
    	{
    		image = buildImage(image);
    		imgTable.put(dim, image);
    		iTable.put(iter, dim);
    	}
    	
    	// Check the Hashtable to see if the image has already been created in the
    	// dimensions required.  If it has, use it.  If it hasn't, create a new image.
    	/* if  (imgTable.containsKey(Integer.toString(iter)) && dimTable.contains(Integer.toString(iter))) {
    		image = imgTable.get(Integer.toString(iter));
    		
    		/**
    		// If both Hashtables contain the key, make sure the image associate with that key is the
    		// right size.  If it isn't, make the image from scratch.
    		// TODO: This block seems ineffective, with it in place, images don't load any faster.
    		if (new Dimension(width, height) == dimTable.get(Integer.toString(iter))) {
    			image = imgTable.get(Integer.toString(iter));
    		} else {
    			image = buildImage(image);
    			imgTable.put(Integer.toString(iter), image);
    	        dimTable.put(Integer.toString(iter), new Dimension(width, height));
    		}
    		*/
    	/*} else {
	    	image = buildImage(image);
	        imgTable.put(Integer.toString(iter), image);
	        //dimTable.put(Integer.toString(iter), new Dimension(width, height));
    	} */
    	return image;
    }
    
    //Build image by looping through each pixel and coloring
	/*
	 * This code taken from https://rosettacode.org/wiki/Julia_set#Java 
	 * and changed to use complex numbers
	 */
    private BufferedImage buildImage(BufferedImage image) {
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

