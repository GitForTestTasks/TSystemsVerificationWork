package ru.andrei.tsystemsverificationwork.database.models.keys;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Composite primary key of Client entity
 */
public class ClientrolePK implements Serializable {
    private long clientId;
    private long roleId;

    public ClientrolePK() { //No argument constructor required for hibernate framework
    }

    @Column(name = "ClientId")
    @Id
    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    @Column(name = "RoleId")
    @Id
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ClientrolePK that = (ClientrolePK) o;

        return clientId == that.clientId && roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        int result = (int) (clientId ^ (clientId >>> 32));
        result = 31 * result + (int) (roleId ^ (roleId >>> 32));
        return result;
    }
}
