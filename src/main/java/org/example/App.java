package org.example;

import org.example.Utility.DatabaseConnection;
import org.example.Utility.FactoryPropertiesReader;
import org.example.Utility.PropertiesReader;


public class App 
{
    public static void main(String[] args) {

        PropertiesReader propertiesDatabase = FactoryPropertiesReader.databasePropDefault();
        DatabaseConnection databaseConfiguration = new DatabaseConnection(propertiesDatabase);
    }
}
