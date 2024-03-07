package org.example;

import org.example.Controller.RegionController;
import org.example.Repository.Implementation.RegionImpl;
import org.example.Repository.RegionRepository;
import org.example.Service.RegionService;
import org.example.Utility.DatabaseConnection;
import org.example.Utility.FactoryPropertiesReader;
import org.example.Utility.PropertiesReader;
import org.example.View.MainView;
import org.example.View.RegionView;


public class App 
{
    public static void main(String[] args) {
        PropertiesReader propertiesDatabase      = FactoryPropertiesReader.databasePropDefault();
        DatabaseConnection databaseConfiguration = new DatabaseConnection(propertiesDatabase);

        RegionRepository regionRepository   = new RegionImpl(databaseConfiguration);
        RegionController regionController   = new RegionController(regionRepository);
        RegionView regionView               = new RegionView(regionController);
        MainView mainView                   = new MainView(regionView);

        mainView.start();

    }
}
