package com.strukovsky.q;
import java.util.Scanner; 

class View
{
	private Scanner scanner;
	public View()
	{
		scanner = new Scanner(System.in);
	}
	public void write(String s)
	{
		System.out.println(s);
	}
	
	public void write(Exception s)
	{
		System.out.println(s);
	}
	
	public void write(Clothes s)
	{
		System.out.println(s);
	}
	
	public void write(ColouredClothes s)
	{
		System.out.println(s);
	}
	
	public Clothes readClothes()
	{
		int temperatureWashing;
		int temperatureIroning;
		System.out.println("Enter temperature of washing");
		temperatureWashing = scanner.nextInt();
		System.out.println("Enter temperature of ironing");
		temperatureIroning = scanner.nextInt();
		
		return new Clothes(temperatureWashing, temperatureIroning);
		
	}
	
	public Colour readColour()
	{
		System.out.println("Enter colour of clothes: Light Dark Coloured");
		String colour = scanner.next();
		switch(colour)
		{
			case "Light":
				return Colour.Light;
			case "Dark":
				return Colour.Dark;
			case "Coloured":
				return Colour.Coloured;
			default:
				return Colour.Coloured;
		}
	}

	public UserOption readUserOption()
	{
		String option = scanner.next();
		switch (option)
		{
			case "new":
				return UserOption.LoadNew;
			case "all":
				return UserOption.LookAtAll;
			case "edit":
				return UserOption.EditExisting;
		}
		return UserOption.Unknown;
	}
	
	public ClothesType readTypeOfClothes() {
		String result = scanner.next();
		switch (result) {
			case "Clothes":
			case "clothes":
				return ClothesType.Clothes;
			case "ColouredClothes":
			case "colouredclothes":
				return ClothesType.ColouredClothes;
		}
		return ClothesType.Clothes;
	}


	public ColouredClothes readColouredClothes()
	{
		Clothes c = readClothes();
		return new ColouredClothes(c, readColour());
		
	}
	
	public WashingMachine readWashingMachine()
	{
		String washingPowder;
		System.out.println("Give washing powder");
		washingPowder = scanner.next();
		String conditioner;
		System.out.println("Give conditioner");
		conditioner = scanner.next();
		Colour clothesColour = readColour();
		int temperature;
		System.out.println("Enter temperature of washing");
		temperature = scanner.nextInt();
		return new WashingMachine( washingPowder,  conditioner,  clothesColour, temperature);

	}
}