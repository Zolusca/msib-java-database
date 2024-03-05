package org.example;

import org.example.Controller.RegionController;
import org.example.Repository.Implementation.RegionImpl;
import org.example.Repository.RegionRepository;
import org.example.Service.RegionService;
import org.example.Utility.DatabaseConnection;
import org.example.Utility.FactoryPropertiesReader;
import org.example.Utility.PropertiesReader;
import org.example.View.HomeView;
import org.example.View.RegionView;


public class App 
{
    public static void main(String[] args) {
        PropertiesReader propertiesDatabase      = FactoryPropertiesReader.databasePropDefault();
        DatabaseConnection databaseConfiguration = new DatabaseConnection(propertiesDatabase);
        RegionRepository regionRepository        = new RegionImpl(databaseConfiguration);
        RegionService regionService = new RegionService((RegionImpl) regionRepository);
        RegionController regionController        = new RegionController(regionService);
        RegionView regionView                    = new RegionView(regionController);
        HomeView homeView                        = new HomeView(regionView);
        homeView.start();
    }
}
