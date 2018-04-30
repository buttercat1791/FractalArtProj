package fractals;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

import javax.swing.JPanel;


public class JuliaSet extends JPanel
{
	int width;
	int height;
	private int iter;
	
	BufferedImage img;
	
	/* 
	 * Constructor
	 * W and H have to be fed in as this uses a BufferedImage
	 */
	public JuliaSet()
	{
		width = 100;
		height = 100;
	}
	
    // Reruns constructor function code to refresh the image
    public void setIter(int newIter, int w, int h) 
    {
    	iter = newIter;
    	this.width = w;
    	this.height = h;
    }
    
    //Overrides the paintComponent
    @Override
    public void paintComponent(Graphics g)
    {
    	img = buildJulia(BufferedImage.TYPE_INT_ARGB);
    	g.drawImage(img, 0, 0, this);
    }
    
    //
    public BufferedImage buildJulia(int type)
    {
    	ComplexNumbers c = new ComplexNumbers(.2, .5);
    	BufferedImage image = new BufferedImage(width, height, type);
    	
    	//Build image by looping through each pixel and masking it
    	for(int x = 0; x < width; x++)
    	{
    		for(int y = 0; y < height; y++)
    		{
    			ComplexNumbers v = new ComplexNumbers((x - WIDTH/2), (y-HEIGHT/2));
    			for(int i = 0; i < iter; i++){
    			    v = v.multiply(v).add(c);
    			}
    			if(v.norm() > iter*100000)
    			   image.setRGB(x, y, 0xFF000000);
    			else
    			   image.setRGB(x, y, 0xFFFF0000);
    		    }
    		}
    	
    	return image;
    }
    
    
    
    
    
    
    
    
}
