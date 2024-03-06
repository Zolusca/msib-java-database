package org.example.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Konfigurasi Database Connection/ pemasangan config
 */
public class DatabaseConnection {
    protected String DRIVER_MANAGER ;
    protected String USERNAME;
    protected String PASSWORD;
    protected String URL;
    private Connection connection;

    public DatabaseConnection(PropertiesReader propertiesReader) {
        Map<String,String> configValue = propertiesReader.getDataFileProperties();

        DRIVER_MANAGER  = configValue.get("driver.mysql");
        URL             = configValue.get("url");
        USERNAME        = configValue.get("username");
        PASSWORD        = configValue.get("password");
    }

    private void createConnection(){
        try {

            Class.forName(DRIVER_MANAGER);
            this.connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);

        } catch (ClassNotFoundException|SQLException e) {
            System.out.println("######### ERROR #########");
            System.out.println("Periksa kembali database, apakah sudah menyala");
            e.printStackTrace();
        }

    }

    public Connection getConnection(){
        if(this.connection == null){
            createConnection();
        }
        return this.connection;
    }

    public void closeConnection() {
        if (isClosedConn()) {
            try {
                this.connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isClosedConn(){
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
