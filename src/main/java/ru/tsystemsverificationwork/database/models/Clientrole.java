package ru.tsystemsverificationwork.database.models;

import ru.tsystemsverificationwork.database.models.keys.ClientrolePK;

import javax.persistence.*;

@Entity
@Table(name = "clientsroles")
@IdClass(ClientrolePK.class)
public class Clientrole {
    private long clientId;
    private long roleId;

    @Id
    @Column(name = "ClientId")
    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    @Id
    @Column(name = "RoleId")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clientrole that = (Clientrole) o;

        if (clientId != that.clientId) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (clientId ^ (clientId >>> 32));
        result = 31 * result + (int) (roleId ^ (roleId >>> 32));
        return result;
    }
}
