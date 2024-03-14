package org.example.Controller;

import org.example.Entity.Country;
import org.example.Entity.Region;
import org.example.Repository.CountryRepository;
import org.example.Repository.RegionRepository;
import org.example.Service.CountryService;
import org.example.Service.RegionService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CountryController {
    private CountryService countryService;
    private RegionService regionService;
    private LinkedList<Country> listCountryLinkedList;
    private ArrayList<Country> listCountryArrayList;

    public CountryController(CountryRepository countryRepository, RegionRepository regionRepository) {
        this.countryService = new CountryService(countryRepository);
        this.regionService  = new RegionService(regionRepository);
    }

    public void listCountry(){
        List<Country> listCountry = countryService.getListOfCountry();
        listCountryLinkedList     = new LinkedList<>(listCountry);

        listCountryLinkedList.forEach(value->
                {
                    int index = listCountryLinkedList.indexOf(value);
                    System.out.println(
                            1+index+". Country Id : "+value.getId()
                            +", Country Name : "+value.getName()
                            +", Region : "+value.getRegion().getName());
                });
    }
    public void addCountry(String id,String name,int idRegion){
        try{
            Region region = regionService.getRegionById(idRegion);
            countryService.insertingData(name,id,region);

            System.out.println("** Sukses Tambah Data **");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void getAllCountryByRegion(int regionId){
        try{
            // mendapatkan region dari id param
            Region region               = regionService.getRegionById(regionId);
            // mendapatkan list country
            List<Country> listCountry   = countryService.getListOfCountry();
            listCountryArrayList        = new ArrayList<>(listCountry);

            // melakukan filtering data sesuai id region yang dipilih user
            listCountry = listCountryArrayList.stream()
                    .filter(value->{
                        return value.getRegion().getName().equals(region.getName());
                    })
                    .collect(Collectors.toList());

            // data dicollect ke list untuk di iterate
            listCountryLinkedList       = new LinkedList<>(listCountry);
            listCountryLinkedList.forEach(value->
                    {
                        int index = listCountryLinkedList.indexOf(value);
                        System.out.println(
                                index+1+". Country Id : "+value.getId()
                                +", Country Name : "+value.getName()
                                +", Region : "+value.getRegion().getName());
                    });

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void getCountryById(String id){
        try{
            Country country = countryService.getCountryById(id);

            System.out.print("Country Detail");
            System.out.print("\n id\t: "+country.getId()
                    +"\n name\t: "+country.getName()
                    +"\n region\t: "+ country.getRegion().getName());
            System.out.println();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
    public void updateCountry(String oldCountryName,
                              String newCountryName, String regionName){

        try{
            // mendapatkan data old country
            Country oldCountry = countryService.getCountryByName(oldCountryName);

            // mendapatkan id region dari input
            Region region = regionService.getRegionByName(regionName);

            // mengcopy data dari object old ke new
            Country newCountry = (Country) oldCountry.clone();

            // merubah data nama country dan region id
            Region newRegion  = newCountry.getRegion();
            newRegion.setId(region.getId());
            newCountry.setName(newCountryName);
            newCountry.setRegion(newRegion);

            // update proses
            Country country = countryService.updateCountryData(newCountry,oldCountry);

            System.out.println("* data country baru *");
            System.out.println
                    ("\n country ID : "+ country.getName()
                        +"\n name : "+country.getName()
                        +"\n region ID : "+country.getRegion().getId());

        }catch (Exception e ){
            System.out.println(e.getMessage());
        }
    }

    public void deleteCountry(String idCountry){
        try{
            Country country = countryService.getCountryById(idCountry);
            countryService.deleteCountry(country);

            System.out.println("** sukses hapus data **");
            System.out.println("Nama Country : "+country.getName());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
