package org.example.Repository.Implementation;

import org.example.Entity.Region;
import org.example.Repository.RegionRepository;
import org.example.Utility.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RegionImpl implements RegionRepository {
    protected DatabaseConnection databaseConnection ;
    public RegionImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void save(Region region) {
        String SQL = "INSERT INTO region(name) VALUES(?)";

        // autocloseable preparedStatment
        try(PreparedStatement preparedStatement =
                    databaseConnection
                            .getConnection()
                            .prepareStatement(SQL))
        {
            // mengisi value dari SQL
            preparedStatement.setString(1,region.getName());
            preparedStatement.executeUpdate();

        }catch (SQLException e) {
            System.out.println("Kesalahan pada "+RegionImpl.class.getName());
            System.out.println("Mohon cek kembali pada method save");
            e.printStackTrace();

        }finally {
            databaseConnection.closeConnection();
        }
    }

    @Override
    public List<Region> getAll() {
        List<Region> regionList = new LinkedList<>();
        String SQL = "SELECT * FROM region";

        // autocloseable preparedStatment
        try(PreparedStatement preparedStatement =
                    databaseConnection
                            .getConnection()
                            .prepareStatement(SQL))
        {
            // mengisi value dari SQL
            ResultSet resultSet = preparedStatement.executeQuery();

            // mendapatkan data dari resultSet
            // ubah data ke object region
            // isi ke List
            while (resultSet.next()){
                Region region = new Region
                        (
                          resultSet.getInt("id"),
                          resultSet.getString("name")
                        );
                regionList.add(region);
            }

            // sorting list
            Collections.sort(regionList);

        }catch (SQLException e) {
            System.out.println("Kesalahan pada "+RegionImpl.class.getName());
            System.out.println("Mohon cek kembali pada method getAll");
            e.printStackTrace();

        }finally {
            databaseConnection.closeConnection();
        }

        return regionList;
    }

    @Override
    public Optional<Region> getById(int id) {
        Region region =null;
        String SQL = "SELECT * FROM region WHERE id=(?)";

        // autocloseable preparedStatment
        try(PreparedStatement preparedStatement =
                    databaseConnection
                            .getConnection()
                            .prepareStatement(SQL))
        {
            // mengisi value dari SQL
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // mendapatkan data dari resultSet
            // ubah data ke object region
            // isi ke List
            while (resultSet.next()){
                region = new Region
                        (
                                resultSet.getInt("id"),
                                resultSet.getString("name")
                        );
            }

        }catch (SQLException e) {
            System.out.println("Kesalahan pada "+RegionImpl.class.getName());
            System.out.println("Mohon cek kembali pada method getById");
            e.printStackTrace();

        }finally {
            databaseConnection.closeConnection();
        }

        return Optional.ofNullable(region);
    }

    @Override
    public Optional<Region> getByName(String name) {
        Region region = null;
        String SQL = "SELECT * FROM region WHERE name=?";

        try (
                PreparedStatement preparedStatement =
                        databaseConnection
                                .getConnection()
                                .prepareStatement(SQL)
        ) {
            // Mengisi parameter pada PreparedStatement
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Jika ada hasil dari query, buat objek Region
            if (resultSet.next()) {
                region = new Region(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            System.out.println("Kesalahan pada " + RegionImpl.class.getName());
            System.out.println("Mohon cek kembali pada method getByName");
            e.printStackTrace();

        } finally {
            databaseConnection.closeConnection();
        }

        return Optional.ofNullable(region);
    }


    @Override
    public void delete(Region region) {
        String SQL = "DELETE FROM region WHERE id=(?)";

        // autocloseable preparedStatment
        try(PreparedStatement preparedStatement =
                    databaseConnection
                            .getConnection()
                            .prepareStatement(SQL))
        {
            // mengisi value dari SQL
            preparedStatement.setInt(1,region.getId());
            preparedStatement.executeUpdate();


        }catch (SQLException e) {
            System.out.println("Kesalahan pada "+RegionImpl.class.getName());
            System.out.println("Mohon cek kembali pada method delete");
            e.printStackTrace();

        }finally {
            databaseConnection.closeConnection();
        }
    }

    @Override
    public boolean update(Region regionNew,Region regionOld) {
        String SQL = "UPDATE region SET name = ? WHERE id = ?";

        // autocloseable preparedStatment
        try(PreparedStatement preparedStatement =
                    databaseConnection
                            .getConnection()
                            .prepareStatement(SQL))
        {
            // mengisi value dari SQL
            preparedStatement.setString(1,regionNew.getName());
            preparedStatement.setInt(2,regionOld.getId());

            // apabila execute menghasilkan = 0, yakni gagal
            if(preparedStatement.executeUpdate()==0){
                throw new RuntimeException("Gagal update data "+ regionNew.getName());
            }

        }catch (SQLException e) {
            System.out.println("Kesalahan pada "+RegionImpl.class.getName());
            System.out.println("Mohon cek kembali pada method update");
            e.printStackTrace();

        }finally {
            databaseConnection.closeConnection();
        }

        return true;
    }
}
