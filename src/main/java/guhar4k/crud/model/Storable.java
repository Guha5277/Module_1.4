package guhar4k.crud.model;

public interface Storable {
    void setId(long id);
    long getId();
    void cloneFrom(Storable storable);
}
