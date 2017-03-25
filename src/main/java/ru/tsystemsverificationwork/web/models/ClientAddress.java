package ru.tsystemsverificationwork.web.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientAddress")
public class ClientAddress {
    private long clientAddressId;
    private String country;
    private String city;
    private Integer postIndex;
    private String street;
    private String houseNumber;
    private Integer apartment;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClientAddressId")
    public long getClientAddressId() {
        return clientAddressId;
    }

    private List<Order> orders;


    private Client client;

    public void setClientAddressId(long clientAddressId) {
        this.clientAddressId = clientAddressId;
    }

    @Basic
    @Column(name = "Country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "City")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "PostIndex")
    public Integer getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(Integer postIndex) {
        this.postIndex = postIndex;
    }

    @Basic
    @Column(name = "Street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "HouseNumber")
    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Basic
    @Column(name = "Apartment")
    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

//    @OneToMany(mappedBy = "clientAddressId")
    @OneToMany
    @JoinColumn(name = "ClientAddressId")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

//    @OneToOne(mappedBy = "clientAddressId")
    @OneToOne
    @JoinColumn(name = "ClientAddressId")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientAddress that = (ClientAddress) o;

        if (clientAddressId != that.clientAddressId) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (postIndex != null ? !postIndex.equals(that.postIndex) : that.postIndex != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (houseNumber != null ? !houseNumber.equals(that.houseNumber) : that.houseNumber != null) return false;
        if (apartment != null ? !apartment.equals(that.apartment) : that.apartment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (clientAddressId ^ (clientAddressId >>> 32));
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (postIndex != null ? postIndex.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        return result;
    }
}
