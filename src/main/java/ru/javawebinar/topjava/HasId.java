package ru.javawebinar.topjava;

public interface HasId {

    Integer getId();

    void setId(Integer id);

    default boolean isNew() {
        return getId() == null;
    }

    // doesn't work for hibernate lazy proxy
    default int id() {
        if (getId() == null)
            throw new IllegalArgumentException("Entity must has id");
        return getId();
    }

}
