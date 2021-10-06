package com.strukovsky.q;


import java.util.Properties;

public class Main
{
	private static final View view = new View();
	private static WashingMachine machine;
	private static final String usernameKey = "username";
	private static final Properties properties = new Properties();
	private static final ConfigLoader loader = new ConfigLoader(properties);

	private static void performLoad(ClothesType type)
	{
		try
		{
			Clothes c;
			switch(type)
			{
				case Clothes:
					c = view.readClothes();
					break;
				case ColouredClothes:
					c = view.readColouredClothes();
					break;
				default:
					throw new Exception("Cannot understand what type do you want to load");
			}
			machine.load(c);
		}
		catch(Exception e)
		{
			view.write(e);
		}
	}

	private static UserOption getUsersChoice()
	{
		String username = properties.getProperty(usernameKey);
		view.write("Hello, " + username);
		view.write("What do you want to do?");
		view.write("new – load another washing machine");
		view.write("all – look at all washing machines");
		view.write("edit – edit already existing washing machine");
		return view.readUserOption();
	}
	
	public static void main(String args[])
	{
		UserOption option = getUsersChoice();

	}
}