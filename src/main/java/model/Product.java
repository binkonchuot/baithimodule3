package model;

public class Product {
    private int id;
    private String name;
    private Double price;
    private int quantily;
    private String color;
    private String description;
    private int idCategory;
    private String nameCategory;

    public Product() {
    }

    public Product( String name, Double price, int quantily, String color, String description, int idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantily = quantily;
        this.color = color;
        this.description = description;
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    public Product(int id, String name, Double price, int quantily, String color, String description, String nameCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantily = quantily;
        this.color = color;
        this.description = description;
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    public Product(int id, String name, Double price, int quantily, String color, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantily = quantily;
        this.color = color;
        this.description = description;
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantily() {
        return quantily;
    }

    public void setQuantily(int quantily) {
        this.quantily = quantily;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
