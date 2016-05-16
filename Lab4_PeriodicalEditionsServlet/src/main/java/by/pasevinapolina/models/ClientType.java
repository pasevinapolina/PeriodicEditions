package by.pasevinapolina.models;

/**
 * Enum for client types
 */
public enum ClientType {
    GUEST(0),
    USER(1),
    ADMIN(2);

    ClientType(int role) {
        this.role = role;
    }

    private int role;

    public int getRole() {
        return role;
    }
}
