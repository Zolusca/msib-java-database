package org.example.View;

import org.example.Controller.RegionController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RegionView {
    final String[] menus =
            { "List Region", "Tambah Region"
            ,"Cari Region dengan nama", "Ubah nama region"
            ,"Hapus region","Keluar"};
    protected boolean status = true;
    protected RegionController regionController;
    private Scanner scanner = new Scanner(System.in);

    public RegionView(RegionController regionController) {
        this.regionController = regionController;
    }



    public void deleteRegion() {
    }

    public void updateRegionName() {
    }

    public void findRegion() {
    }

    public void insertingRegionView() {
    }

    public void regionListView() {
        System.out.println("-------------------------");
        System.out.println("-------List Region-------");
        System.out.println("-------------------------");
        regionController.listRegion();
        System.out.println("-------------------------");
    }


}
