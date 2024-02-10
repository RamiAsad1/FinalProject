package com.example.project.Domain;

public class CategoryDomain {
    String title;
    String pic;
    String price;

    public CategoryDomain(String title, String pic, String price) {
        this.title = title;
        this.pic = pic;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CategoryDomain{" +
                "title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
