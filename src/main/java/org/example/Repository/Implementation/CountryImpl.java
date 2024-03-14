package org.example.Repository.Implementation;

import org.example.Entity.Country;
import org.example.Entity.Region;
import org.example.Repository.CountryRepository;
import org.example.Repository.RegionRepository;
import org.example.Utility.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class CountryImpl implements CountryRepository {
    private DatabaseConnection databaseConnection;

    public CountryImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void save(Country country) {
        String SQL ="INSERT INTO country(id,name,region) VALUES(?,?,?)";

        try(PreparedStatement preparedStatement =
                    databaseConnection
                            .getConnection()
                            .prepareStatement(SQL)) {

            preparedStatement.setString(1,country.getId());
            preparedStatement.setString(2,country.getName());
            preparedStatement.setInt(3,country.getRegion().getId());
            preparedStatement.executeUpdate();


        }catch (SQLException e) {
            System.out.println("Kesalahan pada "+CountryImpl.class.getName());
            System.out.println("Mohon cek kembali pada method save");
            e.printStackTrace();

        }finally {
            databaseConnection.closeConnection();
        }
    }

    @Override
    public List<Country> getAll() {
        List<Country> listCountry         = new LinkedList<>();

        String SQL = "SELECT * FROM country INNER JOIN region ON country.region = region.id";

        // autocloseable preparedStatment
        try(PreparedStatement preparedStatement =
                    databaseConnection
                            .getConnection()
                            .prepareStatement(SQL))
        {
            // mengisi value dari SQL
            ResultSet resultSet = preparedStatement.executeQuery();

            // mendapatkan data dari resultSet
            // ubah data ke object
            while (resultSet.next()){
                Region region = new Region
                        (
                          resultSet.getInt("region.id"),
                          resultSet.getString("region.name")
                        );
                Country country = new Country
                        (
                            resultSet.getString("country.id"),
                            resultSet.getString("country.name"),
                            region
                        );

                listCountry.add(country);
            }

            // sorting list
            Collections.sort(listCountry);

        }catch (SQLException e) {
            System.out.println("Kesalahan pada "+CountryImpl.class.getName());
            System.out.println("Mohon cek kembali pada method getAll");
            e.printStackTrace();

        }finally {
            databaseConnection.closeConnection();
        }

        return listCountry;
    }

    @Override
    public Optional<Country> getById(String id) {
        Country country =null;
        String SQL = "SELECT * FROM country INNER JOIN region ON country.region = region.id WHERE country.id=(?)";

        // autocloseable preparedStatment
        try(PreparedStatement preparedStatement =
                    databaseConnection
                            .getConnection()
                            .prepareStatement(SQL))
        {
            // mengisi value dari SQL
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // mendapatkan data dari resultSet
            // ubah data ke object
            while (resultSet.next()){
                Region region = new Region
                        (
                          resultSet.getInt("region.id"),
                          resultSet.getString("region.name")
                        );
                country = new Country
                        (
                                resultSet.getString("country.id"),
                                resultSet.getString("country.name"),
                                region
                        );

            }

        }catch (SQLException e) {
            System.out.println("Kesalahan pada "+CountryImpl.class.getName());
            System.out.println("Mohon cek kembali pada method getById");
            e.printStackTrace();

        }finally {
            databaseConnection.closeConnection();
        }

        return Optional.ofNullable(country);
    }

    @Override
    public Optional<Country> getByName(String name) {
        Country country =null;
        String SQL = "SELECT * FROM country INNER JOIN region ON country.region = region.id WHERE country.name=(?)";

        // autocloseable preparedStatment
        try(PreparedStatement preparedStatement =
                    databaseConnection
                            .getConnection()
                            .prepareStatement(SQL))
        {
            // mengisi value dari SQL
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();

            // mendapatkan data dari resultSet
            // ubah data ke object
            while (resultSet.next()){
                Region region = new Region
                        (
                                resultSet.getInt("region.id"),
                                resultSet.getString("region.name")
                        );
                country = new Country
                        (
                                resultSet.getString("country.id"),
                                resultSet.getString("country.name"),
                                region
                        );

            }

        }catch (SQLException e) {
            System.out.println("Kesalahan pada "+CountryImpl.class.getName());
            System.out.println("Mohon cek kembali pada method getByName");
            e.printStackTrace();

        }finally {
            databaseConnection.closeConnection();
        }

        return Optional.ofNullable(country);
    }

    @Override
    public void delete(Country country) {
        String SQL = "DELETE FROM country WHERE id=(?)";

        // autocloseable preparedStatment
        try(PreparedStatement preparedStatement =
                    databaseConnection
                            .getConnection()
                            .prepareStatement(SQL))
        {
            // mengisi value dari SQL
            preparedStatement.setString(1,country.getId());
            preparedStatement.executeUpdate();


        }catch (SQLException e) {
            System.out.println("Kesalahan pada "+CountryImpl.class.getName());
            System.out.println("Mohon cek kembali pada method delete");
            e.printStackTrace();

        }finally {
            databaseConnection.closeConnection();
        }
    }

    @Override
    public boolean update(Country countryNew, Country countryOld) {
        String SQL = "UPDATE country SET name = ?, region = ? WHERE id = ?";

        // autocloseable preparedStatment
        try(PreparedStatement preparedStatement =
                    databaseConnection
                            .getConnection()
                            .prepareStatement(SQL))
        {
            // mengisi value dari SQL
            preparedStatement.setString(1,countryNew.getName());
            preparedStatement.setInt(2,countryNew.getRegion().getId());
            preparedStatement.setString(3,countryOld.getId());

            // apabila execute menghasilkan = 0, yakni gagal
            if(preparedStatement.executeUpdate()==0){
                throw new RuntimeException("Gagal update data "+ countryNew.getName());
            }

        }catch (SQLException e) {
            System.out.println("Kesalahan pada "+CountryImpl.class.getName());
            System.out.println("Mohon cek kembali pada method update");
            e.printStackTrace();

        }finally {
            databaseConnection.closeConnection();
        }

        return true;
    }
}
