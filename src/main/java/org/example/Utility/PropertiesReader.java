package org.example.Utility;


import java.io.*;
import java.util.*;

public class PropertiesReader {
    private Map<String,String> dataFileProperties;
    protected Properties properties;
    private String fileName;

    public PropertiesReader(String fileName) {
        properties          = new Properties();
        dataFileProperties  = new HashMap<>();
        this.fileName       = fileName;
    }

    /**
     * <p>
     *    membaca key dan value yang ada pada file properties
     * </p>
     * Note : properties file harus disimpan pada <i>resources</i>
     * @throws IOException error pembacaan atau apabila key dan value kosong
     * @throws FileNotFoundException apabila file tidak ditemukan
     */
    public void ConfigDatabaseProperties() throws IOException {
        // pengecekan file, apabila tidak ditemukan maka terkena exception
        InputStream inputStream = isPropertiesExist(this.fileName)
                                                    .orElseThrow(()->new FileNotFoundException("file properties tidak ditemukan"));
        // setting nama properties file
        properties.load(inputStream);

        // pengecekan apakah ada value dalam properties file
        if(properties.isEmpty()){
            throw new IOException("key dan value pada properties tidak ditemukan");
        }

        // input key value ke map
        properties
                .forEach((key,value)-> dataFileProperties.put(key.toString(),value.toString()));
    }

    /**
     * <p>
     *     pengecekan file properties ada atau tidak
     * </p>
     * Note : ini membaca file properties yang ada di <i>resources</i>
     * @param nameOfProperties nama file beserta (.properties)
     * @return Optional Inputstream
     */
    public Optional<InputStream> isPropertiesExist(String nameOfProperties){
        // mendapatkan file dari path resource
        // digunakan inputstream karena properties memerlukannya
        // jika menggunakan getResource kembalian URL
        InputStream inputStream = PropertiesReader.class
                                                    .getClassLoader()
                                                    .getResourceAsStream(nameOfProperties);
        return Optional.ofNullable(inputStream);
    }

    public Map<String,String> getDataFileProperties(){
        return this.dataFileProperties;
    }

    public Optional<String> getEachValue(String nameOfKey){
        return Optional.ofNullable(dataFileProperties.get(nameOfKey));
    }
}
