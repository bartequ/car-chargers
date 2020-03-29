package pl.edu.agh.kt.model;

public class ChargerDetails {

    private Long id;

    private String address;

    private Integer rating;

    private String name;

    private String cost;

    public ChargerDetails(Long id, String address, Integer rating, String name, String cost) {
        this.id = id;
        this.address = address;
        this.rating = rating;
        this.name = name;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
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
