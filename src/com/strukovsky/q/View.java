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
			case "delete":
				return UserOption.DeleteExisting;
			case "exit":
				return UserOption.Exit;
		}
		return UserOption.Unknown;
	}
	
	public ClothesType readTypeOfClothes() {
		System.out.println("Enter type of clothes Clothes or ColouredClothes");
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
		System.out.println("Now enter data for clothes inside");
		System.out.println("Enter temperature of washing");
		temperature = scanner.nextInt();
		return new WashingMachine( washingPowder,  conditioner,  clothesColour, temperature);

	}

	public boolean readFromUserIfContinue()
	{
		String s = scanner.next();
		return s.equals("Y") || s.equals("y");
	}

	public int readId() {
		System.out.println("Enter ID (from 0 upto N-1) of Washing Machine");
		return scanner.nextInt();
	}
}