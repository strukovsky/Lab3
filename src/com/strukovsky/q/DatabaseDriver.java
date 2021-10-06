package com.strukovsky.q;

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
    private static final String DatabaseFilename = properties.getProperty(keyDatabaseFilename);

    public static DatabaseDriver getInstance() {
        if(instance == null)
            instance = new DatabaseDriver();
        return instance;
    }

    public void addWashingMachine(WashingMachine machine) throws Exception
    {
       List<WashingMachine> data = getAllWashingMachines();
       data.add(machine);

        try(FileOutputStream outputStream = new FileOutputStream(keyDatabaseFilename, false))
        {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(data);
        }

    }

    public List<WashingMachine> getAllWashingMachines() throws Exception
    {
        List<WashingMachine> data;

        try(FileInputStream inputStream = new FileInputStream(keyDatabaseFilename))
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            data = (List<WashingMachine>) objectInputStream.readObject();
        }
        return data;
    }



    private DatabaseDriver()
    {

    }
}
