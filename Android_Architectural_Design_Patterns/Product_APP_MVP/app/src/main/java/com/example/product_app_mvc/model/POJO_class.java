package com.example.product_app_mvc.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Entity class for the products table in Room
@Entity(tableName = "FavProducts")
public class POJO_class {
    @PrimaryKey
    private int id;

    private String title;
    private String description;
    private double price;
    private double rating;
    private String thumbnail;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getThumbnail() { return thumbnail; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }

    @Override
    public String toString() {
        return "POJO_class{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", ID=" + id +
                ", description='" + description + '\'' +
                ", img_src='" + thumbnail + '\'' +

                '}' + "\n";
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}

