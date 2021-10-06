package com.strukovsky.q;


import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{
	private static final View view = new View();
	private static final String usernameKey = "username";
	private static final Properties properties = new Properties();
	private static final ConfigLoader loader = new ConfigLoader(properties);
	private static final Logger log = Logger.getLogger("Controller");

	private static void performLoad(ClothesType type, WashingMachine machine)
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

		view.write("What do you want to do?");
		view.write("new – load another washing machine");
		view.write("all – look at all washing machines");
		view.write("edit – edit already existing washing machine");
		view.write("delete – delete already existing machine by id");
		view.write("exit – exit the program");
		return view.readUserOption();
	}

	private static void addNewMachine()
	{
		view.write("Configure new machine");
		WashingMachine washingMachine = view.readWashingMachine();
		while(true)
		{
			performLoad(view.readTypeOfClothes(), washingMachine);
			view.write("Load another one? Y/N");
			if(!view.readFromUserIfContinue())
				break;
		}
		try
		{
			DatabaseDriver.getInstance().addWashingMachine(washingMachine);
		} catch (Exception e) {
			log.log(Level.WARNING, "Error on add new WashingMachine " + e);
		}

	}
	
	public static void main(String[] args)
	{
		String username = properties.getProperty(usernameKey);
		view.write("Hello, " + username);
		while(true)
		{
			UserOption option = getUsersChoice();
			switch (option)
			{
				case LoadNew:
					addNewMachine();
					break;
				case EditExisting:
					editById();
					break;
				case LookAtAll:
					showAllMachines();
					break;
				case DeleteExisting:
					deleteById();
					break;
				case Exit:
					return;
			}
		}

	}

	private static void deleteById() {
		int id = view.readId();
		try {
			if(!DatabaseDriver.getInstance().existsWashingMachineWithId(id))
				throw new Exception("There is no machine with such id");
			DatabaseDriver.getInstance().removeById(id);
		} catch (Exception e) {
			log.log(Level.WARNING, "Error on edit by id " + e);
		}
	}

	private static void showAllMachines() {
		try {
			List<WashingMachine> machineList = DatabaseDriver.getInstance().getAllWashingMachines();
			int id = 0;
			for(WashingMachine machine: machineList)
			{
				System.out.println(id);
				System.out.println(machine);
				id++;
			}
		} catch (Exception e) {
			log.log(Level.WARNING, "Error on loading all machines " + e);
		}
	}

	private static void editById()
	{
		int id = view.readId();
		try {
			if(!DatabaseDriver.getInstance().existsWashingMachineWithId(id))
				throw new Exception("There is no machine with such id");
			view.write("Configure machine");
			WashingMachine washingMachine = view.readWashingMachine();
			while(true)
			{
				performLoad(view.readTypeOfClothes(), washingMachine);
				view.write("Load another one? Y/N");
				if(!view.readFromUserIfContinue())
					break;
			}
			DatabaseDriver.getInstance().replaceById(washingMachine, id);

		} catch (Exception e) {
			log.log(Level.WARNING, "Error on edit by id " + e);
		}
	}
}