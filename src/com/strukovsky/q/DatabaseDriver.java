package com.strukovsky.q;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseDriver {

    private static final String keyDatabaseFilename = "database";
    private static DatabaseDriver instance;
    private static final Properties properties = new Properties();
    private static final ConfigLoader loader = new ConfigLoader(properties);
    private static final String databaseFilename = properties.getProperty(keyDatabaseFilename);

    private void writeChanges(List<WashingMachine> data) throws Exception
    {
        try(FileOutputStream outputStream = new FileOutputStream(databaseFilename, false))
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(data);
        }
    }

    public static DatabaseDriver getInstance() {
        if(instance == null)
            instance = new DatabaseDriver();
        return instance;
    }

    public void addWashingMachine(WashingMachine machine) throws Exception
    {
       List<WashingMachine> data = getAllWashingMachines();
       data.add(machine);

       writeChanges(data);

    }

    public void replaceById(WashingMachine machine, int id) throws Exception
    {
        List<WashingMachine> data = getAllWashingMachines();
        data.remove(id);
        data.add(id, machine);

        writeChanges(data);
    }

    public void removeById(int id) throws Exception
    {
        List<WashingMachine> data = getAllWashingMachines();
        data.remove(id);
        writeChanges(data);
    }

    public List<WashingMachine> getAllWashingMachines() throws Exception
    {
        File file = new File(databaseFilename);
        if(!file.exists())
            return new ArrayList<>();
        List<WashingMachine> data;

        try(FileInputStream inputStream = new FileInputStream(file))
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            data = (List<WashingMachine>) objectInputStream.readObject();
        }
        return data;
    }

    public WashingMachine getWashingMachineById(int id) throws Exception
    {
        return getAllWashingMachines().get(id);
    }

    public boolean existsWashingMachineWithId(int id) throws Exception
    {
        return id >= 0 && id < getAllWashingMachines().size();
    }



    private DatabaseDriver()
    {

    }
}
