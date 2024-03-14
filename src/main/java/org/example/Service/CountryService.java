package org.example.Service;

import org.example.Entity.Country;
import org.example.Entity.Region;
import org.example.Repository.CountryRepository;

import java.util.List;

public class CountryService {
    private CountryRepository countryRepository;
    private Country country;
    private List<Country> listCountry;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        country                = new Country();
    }

    public void insertingData(String name,String id,Region region) throws Exception {
        country.setName(name);
        country.setId(id);
        country.setRegion(region);

        // pengecekan data
        if(isCountryExist(country.getName())){
            throw new Exception("country telah ada dengan nama "+name);
        }

        // inserting data
        countryRepository.save(country);
    }

    /**
     * <p>
     *     method ini digunakan untuk pengecekan ketersediaan
     *     data country di database
     * </p>
     * @param name
     * @return true (present/ada) and false (absent/tidak ada)
     */
    public boolean isCountryExist(String name) {
        return countryRepository.getByName(name).isPresent();
    }

    public List<Country> getListOfCountry(){
        listCountry = countryRepository.getAll();
        return listCountry;
    }

    /**
     * <p>
     *     Mendapatkan object Country dari id Country
     * </p>
     * @param id
     * @return Country
     * @throws RuntimeException jika data id tidak ada
     */
    public Country getCountryById(String id){
        id.toUpperCase();
        country = countryRepository.getById(id)
                .orElseThrow(()-> new RuntimeException("Data Country id tidak ditemukan "));
        return country;
    }

    /**
     * <p>
     *     Mendapatkan object country dari nama country
     * </p>
     * @param name
     * @return country
     * @throws RuntimeException jika data name tidak ada
     */
    public Country getCountryByName(String name){
        country = countryRepository.getByName(name)
                .orElseThrow(()-> new RuntimeException("Data country name tidak ditemukan"));
        return country;
    }

    public void deleteCountry(Country country){
        countryRepository.delete(country);
    }

    public Country updateCountryData(Country newCountry, Country oldCountry) {

        if(!isCountryExist(newCountry.getName()) && countryRepository.update(newCountry,oldCountry)) {
            return newCountry;
        }
        throw new RuntimeException("gagal update data");
    }
}
