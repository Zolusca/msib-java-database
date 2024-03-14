package org.example.View;

import org.example.Controller.CountryController;
import org.example.Controller.RegionController;

import java.util.Scanner;

public class CountryView {
    protected CountryController countryController;
    private Scanner scanner = new Scanner(System.in);

    public CountryView() {
    }

    public CountryView(CountryController countryController) {
        this.countryController = countryController;
    }

    public void deleteCountry() {
        System.out.println("-------------------------");
        System.out.println("------Hapus Country------");
        System.out.print("Input ID Country : ");

        String input = scanner.nextLine();

        countryController.deleteCountry(input);
    }

    public void updateCountry() {
        System.out.println("-------------------------");
        System.out.println("------Update Country Data------");
        System.out.print("Input nama country : ");
        String inputNameCountry = scanner.next();
        System.out.println("Jika nama tidak diubah isi dengan nama country yang lama!!");
        scanner.nextLine();
        System.out.print("Input nama baru country : ");
        String inputNewName = scanner.nextLine();
        System.out.print("Input region name : ");
        String inputRegion = scanner.nextLine();


        countryController.updateCountry(inputNameCountry,inputNewName,inputRegion);
    }

    public void findCountry() {
        System.out.println("-------------------------");
        System.out.println("------Cari Country------");
        System.out.print("Input ID country : ");
        String inputID = scanner.next();

        countryController.getCountryById(inputID);
    }

    public void addCountry() {
        System.out.println("-------------------------");
        System.out.println("------Tambah Country------");
        System.out.print("Input ID country : ");
        String inputID = scanner.nextLine();
        System.out.print("Input name country : ");
        String inputName = scanner.nextLine();
        System.out.print("Input ID region : ");
        int inputIDRegion = scanner.nextInt();


        countryController.addCountry(inputID,inputName,inputIDRegion);
    }

    public void countryListView() {
        System.out.println("-------------------------");
        System.out.println("-------List Country-------");
        countryController.listCountry();
        System.out.println("-------------------------");
    }

    public void countryListByIdRegion(){
        System.out.println("-------------------------");
        System.out.println("-------List Country By Region-------");
        System.out.print("Input ID Region : ");
        int inputIdRegion = scanner.nextInt();
        countryController.getAllCountryByRegion(inputIdRegion);
    }
}
