package org.example.View;

import org.example.Controller.RegionController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RegionView {
    protected RegionController regionController;
    private Scanner scanner = new Scanner(System.in);

    public RegionView() {
    }

    public RegionView(RegionController regionController) {
        this.regionController = regionController;
    }

    public void deleteRegion() {
        System.out.println("-------------------------");
        System.out.println("------Hapus region------");
        System.out.print("Input angka urutan region : ");

        int input = scanner.nextInt();

        regionController.deleteRegion(input);
    }

    public void updateRegionName() {
        System.out.println("-------------------------");
        System.out.println("------Update Nama Region------");
        System.out.print("Input angka urutan region : ");
        int inputUrutan = scanner.nextInt();
        System.out.print("Input nama baru region : ");
        String inputName = scanner.next();

        regionController.changeNameRegion(inputUrutan,inputName);
    }

    public void findRegion() {
        System.out.println("-------------------------");
        System.out.println("------Cari Region------");
        System.out.print("Input nama region : ");
        String inputName = scanner.next();

        regionController.findRegionByName(inputName);
    }

    public void addRegion() {
        System.out.println("-------------------------");
        System.out.println("------Tambah region------");
        System.out.print("Input Nama : ");

        String nameInput = scanner.nextLine();

        regionController.addRegion(nameInput);
    }

    public void regionListView() {
        System.out.println("-------------------------");
        System.out.println("-------List Region-------");
        regionController.listRegion();
        System.out.println("-------------------------");
    }


}
