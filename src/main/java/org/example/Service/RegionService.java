package org.example.Service;

import org.example.Entity.Region;
import org.example.Repository.Implementation.RegionImpl;
import org.example.Repository.RegionRepository;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.AbstractList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RegionService {
    private RegionRepository regionRepository;
    private Region region;
    private List<Region> regionList;

    public RegionService(RegionImpl regionImpl) {
        regionRepository = regionImpl;
        region           = new Region();
    }

    public void insertingData(String name) throws Exception {
        region.setName(name);

        // pengecekan data
        if(isRegionExist(region.getName())){
            throw new Exception("region telah ada dengan nama "+name);
        }

        // inserting data
        regionRepository.save(region);
    }

    /**
     * <p>
     *     method ini digunakan untuk pengecekan ketersediaan
     *     data region di database
     * </p>
     * @param name
     * @return true (present/ada) and false (absent/tidak ada)
     */
    private boolean isRegionExist(String name){
        return regionRepository.getByName(name).isPresent();
    }

    public LinkedList<Region> getListOfRegion(){
        regionList = regionRepository.getAll();
        return new LinkedList<>(regionList);
    }

    /**
     * <p>
     *     Mendapatkan object region dari id region
     * </p>
     * @param id
     * @return Region
     * @throws RuntimeException jika data id tidak ada
     */
    public Region getRegionById(int id){
        region = regionRepository.getById(id)
                .orElseThrow(()-> new RuntimeException("Data region id tidak ditemukan "));
        return  region;
    }

    /**
     * <p>
     *     Mendapatkan object region dari nama region
     * </p>
     * @param name
     * @return Region
     * @throws RuntimeException jika data name tidak ada
     */
    public Region getRegionByName(String name){
        region = regionRepository.getByName(name)
                .orElseThrow(()-> new RuntimeException("Data region name tidak ditemukan"));
        return  region;
    }

    public void deleteRegion(Region region){
        regionRepository.delete(region);
    }

    public Region updateNameRegion(Region newRegion,Region oldRegion ){

        // jika isRegionExist =false atau newName tidak ditemukan di database
        // dan regionRepository.update = true, berhasil di update
        if(!isRegionExist(newRegion.getName()) && regionRepository.update(newRegion,oldRegion)){
            return newRegion;
        }
        // jika kondisi if gagal maka exception
        throw new RuntimeException("Data nama telah ada "+newRegion.getName());

    }
}
