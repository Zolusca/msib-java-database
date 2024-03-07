package org.example.View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {

    private Scanner scanner = new Scanner(System.in);
    private RegionView regionView ;

    public MainView(RegionView regionView) {
        this.regionView = regionView;
    }
    public void start() {
        while (true){
            homeScreen();
            homeInput();
        }
    }

    public void homeInput() {
        try {
            int value = scanner.nextInt();

            switch (value) {
                case 1:
                    regionView.regionListView();
                    break;
                case 2:
                    regionView.addRegion();
                    break;
                case 3:
                    regionView.updateRegionName();
                    break;
                case 4:
                    regionView.deleteRegion();
                    break;
                case 5:
                    regionView.findRegion();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("** Notifikasi : inputan salah!! **");
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("** Notifikasi : inputan hanya angka!! **");
            scanner.next();
        }
    }

    public void homeScreen(){
        System.out.println("------------------");
        System.out.println("-----Dashboard----");
        System.out.println("------------------");
        System.out.println("1. List Region");
        System.out.println("2. Tambah Region");
        System.out.println("3. Ubah Nama Region");
        System.out.println("4. Hapus Region");
        System.out.println("5. Cari Region");
        System.out.println("6. Keluar");
        System.out.println("------------------");
        System.out.println("INPUT DENGAN ANGKA");
        System.out.println("------------------");
        System.out.print("input : ");

    }
}
