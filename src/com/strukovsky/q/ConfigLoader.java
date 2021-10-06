package com.strukovsky.q;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConfigLoader {

    private static Logger logger = Logger.getLogger("ConfigLoader");
    private static String defaultConfig = "config.ini";

    public ConfigLoader(String fileName, Properties properties)
    {
        try
        {
            getConfigFile(fileName, properties);
            ConfigLoader.class.getResource(defaultConfig);
        }
        catch (Exception e)
        {
            logger.log(Level.WARNING, "While load configuration: " + e.getMessage());
        }


    }

    public ConfigLoader(Properties properties)
    {
        this(defaultConfig, properties);
    }

    private static void getConfigFile(String filename, Properties props) throws IOException, FileNotFoundException, Exception {
        InputStream fs;
        File f = new File(filename);
        if(f.exists())
        {
            fs = new FileInputStream(f);
        }
        else
            fs = ConfigLoader.class.getResourceAsStream(filename);
        props.load(fs);
        if(fs != null)
        fs.close();

    }
}
