package guhar4k.crud.model;

import java.util.List;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private List<Post> posts;
    private Region region;

    public User(String firstName, String lastName, Region region) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
    }

    public User(Long id, String firstName, String lastName, Region region, List<Post> posts) {
        this(firstName, lastName, region);
        this.id = id;
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Region getRegion() {
        return region;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
