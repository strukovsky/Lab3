package com.strukovsky.q;
import java.io.Serializable;
import java.util.ArrayList;
class WashingMachine implements Serializable
{
	private String washingPowder;
	private String conditioner;
	private Colour clothesColour;
	private int temperature;
	
	public String getWashingPowder()
	{
		return washingPowder;
	}
	
	public void setWashingPowder(String w)
	{
		washingPowder = w;
	}
	
	public String getConditioner()
	{
		return conditioner;
	}
	
	public void setConditioner(String c)
	{
		conditioner = c;
	}
	
	public Colour getClothesColour()
	{
		return clothesColour;
	}
	
	public void setClothesColour(Colour c)
	{
		clothesColour = c;
	}

	public ArrayList<Clothes> getLoadedClothes() {
		return result;
	}

	public WashingMachine(String washingPowder, String conditioner, Colour clothesColour, int temperature)
	{
		this.washingPowder = washingPowder;
		this.conditioner = conditioner;
		this.clothesColour = clothesColour;
		this.temperature = temperature;
		
		result = new ArrayList<>();
	}
	
	private ArrayList<Clothes> result;
	public void load(Clothes c) throws ClothersMismatchException
	{
		if(c.getTemperatureWash() != temperature)
		{
			throw new ClothersMismatchException("Cloth and washing machine has different temperature:\nIn machine: " + temperature + "\nPresent: " + c.getTemperatureWash());
		}
		
		if(result.isEmpty())
		{
			result.add(c);
		}
		else
		{
			Clothes first = result.get(0);
			if(c instanceof ColouredClothes && first instanceof ColouredClothes)
			{
				ColouredClothes cCasted = (ColouredClothes) c;
				ColouredClothes firstCasted = (ColouredClothes) first;
				if(cCasted.getColour() != firstCasted.getColour())
					throw new ClothersMismatchException("Clothes are different in colour\nIn machine: " + firstCasted.getColour() + "\nPresent: " + cCasted.getColour());
			}
			if(c.equals(first))
			{
				result.add(first);
			}
			throw new ClothersMismatchException("Clothers are different\nIn machine: " + first + "Present: " + c);
		}
	
	}
	
	public String toString()
	{
		return "Washing powder: " + washingPowder + " | conditioner: " + conditioner +
		" | clothes colour " + clothesColour + " | temperature of clothes " + temperature + "\n"; 
	}
	
}