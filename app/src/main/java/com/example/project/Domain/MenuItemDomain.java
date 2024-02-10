package com.example.project.Domain;

public class MenuItemDomain {
    private String title;
    private String price;
    private String pic;

    public MenuItemDomain(String title, String price, String pic) {
        this.title = title;
        this.price = price;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getPic() {
        return pic;
    }
}
