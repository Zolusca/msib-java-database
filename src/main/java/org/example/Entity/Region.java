package org.example.Entity;

import java.util.Objects;

public class Region implements Comparable<Region>{
    private int id;
    private String name;

    public Region() {
    }
    public Region(String name) {
        this.name=name;
    }
    public Region(int id,String name){
        this.id     = id;
        this.name   = name;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return id == region.id && Objects.equals(name, region.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(Region o) {
        return this.name.compareTo(o.getName());
    }
}
