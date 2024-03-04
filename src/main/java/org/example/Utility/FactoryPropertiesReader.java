package org.example.Utility;

import java.io.IOException;

public class FactoryPropertiesReader {
    /**
     * The default value <b>Database.properties</b> on resource folder
     * @return
     */
    public static PropertiesReader databasePropDefault(){
        PropertiesReader propertiesReader = new PropertiesReader("Database.properties");
        try {
            // load the file
            propertiesReader.ConfigDatabaseProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesReader;
    }
    public static PropertiesReader databaseConfigProp(String fileName){
        PropertiesReader propertiesReader = new PropertiesReader(fileName);
        try {
            propertiesReader.ConfigDatabaseProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesReader;
    }
}
