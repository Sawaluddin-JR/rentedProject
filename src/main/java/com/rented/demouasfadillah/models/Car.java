package com.rented.demouasfadillah.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCar;

    @Column(length = 64)
    private String image;
    private String brand;
    private String typeCar;
    private Integer productionYear;
    private Long price;
    private Integer seats;
    private Integer carTrunk;
    private Integer stock;
    private String driver;

    @OneToMany(mappedBy = "car")
    private List<Rented> renteds;

    @OneToMany(mappedBy = "car")
    private List<Review> reviews;

    // Getter and Setter methods
    // public Integer getIdCar() {
    // return idCar;
    // }

    // public void setIdCar(Integer idCar) {
    // this.idCar = idCar;
    // }

    // public String getImage() {
    // return image;
    // }

    // public void setImage(String image) {
    // this.image = image;
    // }

    // public String getBrand() {
    // return brand;
    // }

    // public void setBrand(String brand) {
    // this.brand = brand;
    // }

    // public String getTypeCar() {
    // return typeCar;
    // }

    // public void setTypeCar(String typeCar) {
    // this.typeCar = typeCar;
    // }

    // public long getPrice() {
    // return price;
    // }

    // public void setPrice(long price) {
    // this.price = price;
    // }

    // public int getSeats() {
    // return seats;
    // }

    // public void setSeats(int seats) {
    // this.seats = seats;
    // }

    // public int getCarTrunk() {
    // return carTrunk;
    // }

    // public void setCarTrunk(int carTrunk) {
    // this.carTrunk = carTrunk;
    // }

    // public int getStock() {
    // return stock;
    // }

    // public void setStock(int stock) {
    // this.stock = stock;
    // }

    // public String getDriver() {
    // return driver;
    // }

    // public void setDriver(String driver) {
    // this.driver = driver;
    // }

    // public List<Rented> getRenteds() {
    // return renteds;
    // }

    // public void setRenteds(List<Rented> renteds) {
    // this.renteds = renteds;
    // }

    // public List<Review> getReviews() {
    // return reviews;
    // }

    // public void setReviews(List<Review> reviews) {
    // this.reviews = reviews;
    // }
}