package com.premthomas.foodieapp.model;

import java.util.List;
import java.util.Objects;

public class Restaurant {
    /*
    add the following properties
    --------------------------------------
    Datatype                  variable
    --------------------------------------
    String                      id
    String                      name
    String                      address
    List<Dish>                  menu
     */

    /*
    1. All the fields should be private
    2. Create only no-arg constructor
    3. Create Getters and Setter methods
    4. Override hashCode() and equals() methods
    5. Override toString() methods
     */

    private String id;
    private String name;
    private String address;
    private List<String> menu;

    public Restaurant() {
    }

    public String getId() {
        return id;
    }

    public Restaurant setId(String id) {
        this.id = id;
        return this;
    }

    public List<String> getMenu() {
        return menu;
    }

    public Restaurant setMenu(List<String> menu) {
        this.menu = menu;
        return this;
    }

    public String getName() {
        return name;
    }

    public Restaurant setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Restaurant setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(menu, that.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, menu);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", menu=" + menu +
                '}';
    }
}
