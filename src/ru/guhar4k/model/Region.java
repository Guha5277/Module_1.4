package ru.guhar4k.model;

public class Region {
    private long id;
    private String name;

    public Region(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
