package org.example.View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainViewCountry {
    private Scanner scanner = new Scanner(System.in);
    private CountryView countryView ;

    public MainViewCountry(CountryView countryView) {
        this.countryView = countryView;
    }
    public void start() {
        while (true){
            homeScreenCountry();
            homeInput();
        }
    }

    public void homeInput() {
        try {
            int value = scanner.nextInt();

            switch (value) {
                case 1:
                    countryView.countryListView();
                    break;
                case 2:
                    countryView.countryListByIdRegion();
                    break;
                case 3:
                    countryView.updateCountry();
                    break;
                case 4:
                    countryView.deleteCountry();
                    break;
                case 5:
                    countryView.findCountry();
                    break;
                case 6:
                    countryView.addCountry();
                    break;
                case 7:
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
    public void homeScreenCountry(){
        System.out.println("------------------");
        System.out.println("-----Dashboard----");
        System.out.println("------------------");
        System.out.println("1. List Country");
        System.out.println("2. List Country By ID Region");
        System.out.println("3. Ubah Country");
        System.out.println("4. Hapus Country");
        System.out.println("5. Cari Country");
        System.out.println("6. tambah Country");
        System.out.println("7. Keluar");
        System.out.println("------------------");
        System.out.println("INPUT DENGAN ANGKA");
        System.out.println("------------------");
        System.out.print("input : ");

    }
}
