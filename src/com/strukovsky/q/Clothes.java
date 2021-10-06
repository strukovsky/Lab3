package com.strukovsky.q;

import java.io.Serializable;

class Clothes implements Serializable
{
	protected int temperatureWash;
	protected int temperatureIroning;
	
	public void setTemperatureWash(int t)
	{
		temperatureWash = t;
	}
	
	public int getTemperatureWash()
	{
		return temperatureWash;
	}
	
	public void setTemperatureIroning(int t)
	{
		temperatureIroning = t;
	}
	
	public int getTemperatureIroning()
	{
		return temperatureIroning;
	}
	
	public Clothes(int tempWash, int tempIron)
	{
		temperatureWash = tempWash;
		temperatureIroning = tempIron;
	}
	
	public Clothes(Clothes c)
	{
		temperatureWash = c.temperatureWash;
		temperatureIroning = c.temperatureIroning;
	}
	
	public String toString()
	{
		return "Washing temperature: " + temperatureWash + " | Ironing temperature: " + temperatureIroning + "\n"; 
	}
	
	public boolean equals(Clothes other)
	{
		if(other.temperatureWash != temperatureWash)
			return false;
		if(other.temperatureIroning != temperatureIroning)
			return false;
		return true;
	}
}