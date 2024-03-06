package org.example.Controller;

import org.example.Entity.Region;
import org.example.Repository.Implementation.RegionImpl;
import org.example.Repository.RegionRepository;
import org.example.Service.RegionService;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RegionController {
    private RegionService regionService;
    private LinkedList<Region> listRegionLinkedList;
    private ArrayList<Region> listRegionArrayList;

    public RegionController(RegionRepository regionRepository) {
        this.regionService = new RegionService((RegionImpl) regionRepository);
    }

    public void listRegion() {
         List<Region> result    = regionService.getListOfRegion();
         listRegionLinkedList   = new LinkedList<>(result);

         listRegionLinkedList.forEach(val -> {
             int index = listRegionLinkedList.indexOf(val);
             System.out.println(1+index+". Region "+val.getName());
         });
    }

    public void addRegion(String inputName) {
        try{
            validatingInput(inputName);
            regionService.insertingData(inputName);
            System.out.println("** Sukses Tambah Data Region **");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void findRegionByName(String name){
        try {
            Region region = regionService.getRegionByName(name);
            System.out.println(region);

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
    public void deleteRegion(int number){

        List<Region> result    = regionService.getListOfRegion();
        listRegionArrayList    = new ArrayList<>(result);

        if(number>listRegionArrayList.size()){
            throw new RuntimeException("inputan invalid");
        }

        // pengambilan object dan penghapusan
        Region region = listRegionArrayList.get(number-1);
        regionService.deleteRegion(region);

        System.out.println("--barhasil dihapus--");
        listRegion();
    }


    public void changeNameRegion(int indexOfList,String newName){

        if(listRegionArrayList.isEmpty()){
            List<Region> result    = regionService.getListOfRegion();
            listRegionArrayList   = new ArrayList<>(result);
        }

        if(indexOfList>listRegionArrayList.size()){
            throw new RuntimeException("inputan invalid");
        }

        // index -1 untuk mendapatkan object di array
        Region oldRegion = listRegionArrayList.get(indexOfList-1);
        Region newRegion = new Region(oldRegion.getId(),newName);

        regionService.updateNameRegion(newRegion,oldRegion);

        System.out.println("** Nofifikasi : data telah di update "+newRegion);
    }
    public void validatingInput(String inputValue) throws Exception {
        if(inputValue.isEmpty()||inputValue.length()>25){
            throw new Exception("Tidak boleh kosong atau > 25 karakter");
        }
    }
}
