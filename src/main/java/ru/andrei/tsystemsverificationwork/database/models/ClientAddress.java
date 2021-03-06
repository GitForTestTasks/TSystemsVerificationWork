package ru.andrei.tsystemsverificationwork.database.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Entity of clientaddress table
 */
@Entity
@Table(name = "clientAddress")
public class ClientAddress implements Serializable {
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

    private Client clientId;

    @ManyToOne
    @JoinColumn(name = "ClientId")
    public Client getClientId() {
        return clientId;
    }


    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

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
    @Min(0)
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
    @Min(1)
    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Basic
    @Column(name = "Apartment")
    @Min(1)
    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ClientAddress that = (ClientAddress) o;

        return clientAddressId == that.clientAddressId;
    }

    @Override
    public int hashCode() {
        return (int) (clientAddressId ^ (clientAddressId >>> 32));
    }

    @Override
    public String toString() {
        return "Country " + country +
                ", City " + city +
                ", Post Index " + postIndex +
                ", Street " + street +
                ", HouseNumber " + houseNumber +
                ", Apartment " + apartment;
    }
}
