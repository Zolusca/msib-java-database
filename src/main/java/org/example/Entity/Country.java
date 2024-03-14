package org.example.Entity;

import java.util.Objects;

public class Country implements Comparable<Country>,Cloneable{
    private String id;
    private String name;
    private Region region;

    public Country(){

    }
    public Country(String id, String name, Region region) {
        this.id = id;
        setName(name);
        this.region = region;
    }

    public Country(String id, String name) {
        this.id = id;
        setName(name);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", region=" + region +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id && Objects.equals(name, country.name) && Objects.equals(region, country.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, region);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(id.length()>2){
            throw new RuntimeException("id length cant over 2 char");
        }
        this.id = id.toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length()>40){
            throw new RuntimeException("Name cant over 40+ char");
        }
        // kapitalisasi karakter pertama ex : apa -> Apa
        this.name = name.substring(0,1).toUpperCase()+name.substring(1);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public int compareTo(Country o) {
        return this.name.compareTo(o.getName());
    }
}
