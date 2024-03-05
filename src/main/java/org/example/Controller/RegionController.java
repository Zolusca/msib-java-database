package org.example.Controller;

import org.example.Entity.Region;
import org.example.Service.RegionService;

import java.util.LinkedList;
import java.util.function.Function;

public class RegionController {
    private RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    public void listRegion(){
        Short i =0;
        // lambda expression
        Function<Short,Short> increment = new Function<Short, Short>() {
            @Override
            public Short apply(Short aShort) {
                return (short) (aShort+1);
            }
        };

        LinkedList<Region> listRegion = regionService.getListOfRegion();
        listRegion.stream()
                .map(value->increment.apply(i)+". Wilayah "+value.getName())
                .forEach(System.out::println);
    }
}
