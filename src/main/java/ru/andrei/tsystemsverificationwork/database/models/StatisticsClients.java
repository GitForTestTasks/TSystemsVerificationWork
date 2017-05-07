package ru.andrei.tsystemsverificationwork.database.models;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO of users statistics
 */
public class StatisticsClients implements Serializable {

    private String email;
    private String firstName;
    private String lastName;
    private BigDecimal total;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "StatisticsClients{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", total=" + total +
                '}';
    }
}
