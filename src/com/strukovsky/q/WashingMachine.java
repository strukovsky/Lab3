package com.strukovsky.q;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

class WashingMachine implements Serializable
{
	private static final String keyCapacity = "capacity";
	private static final Properties properties = new Properties();
	private static final ConfigLoader loader = new ConfigLoader(properties);

	private String washingPowder;
	private String conditioner;
	private Colour clothesColour;
	private final int temperature;
	private static final int capacity = Integer.parseInt(properties.getProperty(keyCapacity));

	public int getCapacity() {
		return capacity;
	}

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
		if(result.size() + 1 >= capacity)
			throw new ClothersMismatchException("WashingMachine is full!");
		if(c.getTemperatureWash() != temperature)
		{
			throw new ClothersMismatchException("Cloth and washing machine have different temperature:\nIn machine: " + temperature + "\nPresent: " + c.getTemperatureWash());
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
			else
			throw new ClothersMismatchException("Clothes are different\nIn machine: " + first + "Present: " + c);
		}
	
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder("Washing powder: ");
		builder.append(washingPowder);
		builder.append(" | conditioner: ");
		builder.append(conditioner);
		builder.append(" | clothes colour ");
		builder.append(clothesColour);
		builder.append( " | temperature of clothes ");
		builder.append(temperature);
		builder.append("\n");
		builder.append("Clothes inside:\n");
		for(Clothes clothes: result)
		{
			if(clothes instanceof ColouredClothes)
			builder.append((ColouredClothes) clothes);
			else
				builder.append(clothes);
		}
		builder.append("\n");
		return builder.toString();
	}
	
}