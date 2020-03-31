package pl.edu.agh.kt.model;

public class ChargerDetails {

    private Integer id;

    private String address;

    private Float rating;

    private String name;

    private String cost;

    public ChargerDetails(Integer id, String address, Float rating, String name, String cost) {
        this.id = id;
        this.address = address;
        this.rating = rating;
        this.name = name;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
