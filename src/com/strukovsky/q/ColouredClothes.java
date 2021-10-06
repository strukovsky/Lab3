package com.strukovsky.q;
class ColouredClothes extends Clothes
{
	private Colour colour;
	
	public Colour getColour()
	{
		return colour;
	}
	
	public void setColour(Colour c)
	{
		colour = c;
	}
	
	public ColouredClothes(int tempWash, int tempIron, Colour c)
	{
		super(tempWash, tempIron);
		colour = c;
	}
	
	public ColouredClothes(Clothes clothes, Colour c)
	{
			super(clothes);
			colour  = c;
	}
	
	
	public String toString()
	{
		return "Washing temperature: " + temperatureWash + " | Ironing temperature: " + temperatureIroning + " | colour " + colour + "\n"; 
	}
}