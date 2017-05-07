package ru.andrei.tsystemsverificationwork.database.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entity of roles table
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {
    private long roleId;
    private String name;

    public Role() { //No argument constructor required for hibernate framework
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleId")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    private List<Client> clientList;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }


    @Basic
    @Column(name = "Name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Role role = (Role) o;

        return roleId == role.roleId && name.equals(role.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }
}
