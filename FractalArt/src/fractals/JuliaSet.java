package fractals;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
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

    	/*
    	 * This code taken from https://rosettacode.org/wiki/Julia_set#Java 
    	 * and changed to use complex numbers
    	 */
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

/*
for(int x = 0; x < width; x++)
{
	for(int y = 0; y < height; y++)
	{
		ComplexNumbers v = new ComplexNumbers(x - width, y-height);
		for(int i = 0; i < iter; i++){
		    v = v.multiply(v).add(c);
		}
		double vN = v.norm();
		if(vN <= iter*1000) image.setRGB(x, y, 0xFF00000);
		else if(vN <= iter*2000 ) image.setRGB(x,  y,  0xFF00FF00);
		else if(vN <= iter*3000 ) image.setRGB(x,  y,  0x045A6F);
		else if(vN <= iter*4000 ) image.setRGB(x,  y,  0xFF35EA);
		else if(vN <= iter*5000 ) image.setRGB(x,  y,  0xFBFF01);
		else if(vN <= iter*6000 ) image.setRGB(x,  y,  0xFF355D);
		else if(vN <= iter*7000 ) image.setRGB(x,  y,  0x0317DB);
		else if(vN <= iter*8000 ) image.setRGB(x,  y,  0x6DC183);
		else if(vN <= iter*9000 ) image.setRGB(x,  y,  0x60586C);
		else if(vN <= iter*200000 ) image.setRGB(x,  y,  0xFE0AF3);
		else image.setRGB(x, y, 0xFFFFFFFF);
	    }
	}
*/
