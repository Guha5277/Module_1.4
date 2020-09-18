package main.java.guhar4k.crud.model;

public class Region {
    private long id;
    private String name;

    public Region(String name) {
        this.name = name;
    }

    public Region(long id, String name) {
        this(name);
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + ":" + name;
    }
}
