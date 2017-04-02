package ru.tsystemsverificationwork.database.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client implements Serializable {
    private long clientId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String password;
    private boolean isEnabled;
    private List<Role> roles;
    private List<Order> orders;
    private Set<ClientAddress> clientAddresses;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "clientId", cascade = CascadeType.MERGE)
    public Set<ClientAddress> getClientAddresses() {
        return clientAddresses;
    }

    public void setClientAddresses(Set<ClientAddress> clientAddresses) {
        this.clientAddresses = clientAddresses;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClientId")
    public long getClientId() {
        return clientId;
    }



    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "ClientsRoles", joinColumns = {@JoinColumn(name = "ClientId", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "RoleId", nullable = false, updatable = false)})
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "FirstName")
    @NotBlank
    @Size(min = 2, max = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName")
    @NotBlank
    @Size(min = 2, max = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "BirthDate")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "Email")
    @Email
    @NotBlank
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Password")
    @NotBlank
    @Size(min = 6, max = 80)
    @Pattern(regexp = "^\\S+$")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Basic
    @Column(name = "IsEnabled", columnDefinition = "BIT default true", nullable = false)
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @OneToMany
    @JoinColumn(name = "ClientId")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Client(String firstName, String lastName, Date birthDate, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
    }

    public Client(String firstName, String lastName, Date birthDate, String email, String password, boolean isEnabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.isEnabled = isEnabled;
    }

    public Client() {
    }

    public Client(long clientId, String firstName, String lastName, Date birthDate,
                  String email, String password, List<Role> roles, List<Order> orders) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (clientId != client.clientId) return false;
        if (firstName != null ? !firstName.equals(client.firstName) : client.firstName != null) return false;
        if (lastName != null ? !lastName.equals(client.lastName) : client.lastName != null) return false;
        if (birthDate != null ? !birthDate.equals(client.birthDate) : client.birthDate != null) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (password != null ? !password.equals(client.password) : client.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (clientId ^ (clientId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
