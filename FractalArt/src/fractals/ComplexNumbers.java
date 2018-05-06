package fractals;


//Thank you to Professor Hochberg for letting us use his code
public class ComplexNumbers 
{
	double x;
	double y;
	
	public ComplexNumbers(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public ComplexNumbers add(ComplexNumbers n)
	{
		return new ComplexNumbers(this.x + n.x, this.y + n.y);
	}
	
	public ComplexNumbers multiply(ComplexNumbers n)
	{
		return new ComplexNumbers( 
				((this.x * n.x) - (this.y * n.y)),  //Real value
				((this.x * n.y) + (this.y * n.x))); //Imaginary value
				
	}
	
	public double norm()
	{
		return Math.sqrt((this.x * this.x) + (this.y * this.y));
	}
}
