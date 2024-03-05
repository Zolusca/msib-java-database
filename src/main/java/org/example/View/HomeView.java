package org.example.View;

import java.util.Scanner;

public class HomeView {
    final String[] menus =
            {
                    "List Region", "Tambah Region",
                    "Cari Region dengan nama", "Ubah nama region",
                    "Hapus region","Keluar"
            };
    private Scanner scanner = new Scanner(System.in);
    private RegionView regionView ;

    public HomeView(RegionView regionView) {
        this.regionView = regionView;
    }

    public void start(){

        while (true){
            homeScreen();
            homeScreenInputHandler();
        }
    }

    public void homeScreenInputHandler(){
        short value = scanner.nextShort();

        switch (value) {
            case 1:
                regionView.regionListView();
                break;
            case 2:
                regionView.insertingRegionView();
                break;
            case 3:
                regionView.findRegion();
                break;
            case 4:
                regionView.updateRegionName();
                break;
            case 5:
                regionView.deleteRegion();
                break;
            case 6:
                System.out.println("------ User Exit -------");
                System.exit(10);
                break;
            default:
                HomeView.flashMessage("Gunakan inputan angka yang sesuai menu");
                break;
        }
    }

    public void homeScreen(){
        System.out.println("------------------------");
        System.out.println("*******Dashboard********");
        System.out.println("------------------------");
        for (int i = 0; i < menus.length; i++) {
            System.out.println(i + 1 + ". " + menus[i]);
        }
        System.out.println("-------------------------");
        System.out.println("=== Gunakan Angka Untuk Memilih ===");
        System.out.println("Input : ");
    }

    public static void flashMessage(String customvalue){
        System.out.println("Notifikasi : "+customvalue);
    }

}
