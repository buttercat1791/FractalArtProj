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
    	iter = newIter + 1;
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
    	ComplexNumbers c = new ComplexNumbers(-0.7, 0.27015);
    	BufferedImage image = new BufferedImage(width, height, type);
    	
    	//Build image by looping through each pixel and masking it
    	for(int x = 0; x < width; x++)
    	{
    		for(int y = 0; y < height; y++)
    		{
    			ComplexNumbers v = new ComplexNumbers((x - WIDTH/20000000), (y-HEIGHT/20000000));
    			for(int i = 0; i < iter; i++){
    			    v = v.multiply(v).add(c);
    			}
    			double vN = v.norm();
    			if(vN <= iter*100000) image.setRGB(x, y, 0xFF000000);
    			else if(vN <= iter*200000 ) image.setRGB(x,  y,  0xFF00FF00);
    			else if(vN <= iter*300000 ) image.setRGB(x,  y,  0x045A6F);
    			else if(vN <= iter*400000 ) image.setRGB(x,  y,  0xFF35EA);
    			else if(vN <= iter*500000 ) image.setRGB(x,  y,  0xFBFF01);
    			else if(vN <= iter*600000 ) image.setRGB(x,  y,  0xFF355D);
    			else if(vN <= iter*700000 ) image.setRGB(x,  y,  0x0317DB);
    			else if(vN <= iter*800000 ) image.setRGB(x,  y,  0x6DC183);
    			else if(vN <= iter*900000 ) image.setRGB(x,  y,  0x60586C);
    			else if(vN <= iter*1000000 ) image.setRGB(x,  y,  0xFE0A63);
    			else image.setRGB(x, y, 0xFFFFFFFF);
    		    }
    		}
    	
    	return image;
    }
    
    
    
    
    
    
    
    
}
