package model;

public abstract class LibraryModel {

   protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String toStringForSearch();
}
