package org.example;

import org.example.Controller.CountryController;
import org.example.Controller.RegionController;
import org.example.Repository.CountryRepository;
import org.example.Repository.Implementation.CountryImpl;
import org.example.Repository.Implementation.RegionImpl;
import org.example.Repository.RegionRepository;
import org.example.Service.RegionService;
import org.example.Utility.DatabaseConnection;
import org.example.Utility.FactoryPropertiesReader;
import org.example.Utility.PropertiesReader;
import org.example.View.CountryView;
import org.example.View.MainView;
import org.example.View.MainViewCountry;
import org.example.View.RegionView;


public class App 
{
    public static void main(String[] args) {
        PropertiesReader propertiesDatabase      = FactoryPropertiesReader.databasePropDefault();
        DatabaseConnection databaseConfiguration = new DatabaseConnection(propertiesDatabase);

        RegionRepository regionRepository   = new RegionImpl(databaseConfiguration);
        CountryRepository countryRepository = new CountryImpl(databaseConfiguration);
        RegionController regionController   = new RegionController(regionRepository);
        CountryController countryController = new CountryController(countryRepository,regionRepository);

        RegionView regionView               = new RegionView(regionController);
        CountryView countryView             = new CountryView(countryController);

        MainView mainView                   = new MainView(regionView);
        MainViewCountry mainViewCountry     = new MainViewCountry(countryView);

//        mainView.start();
        mainViewCountry.start();
    }
}
