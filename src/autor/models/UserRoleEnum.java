package models;

public enum UserRoleEnum {

    ADMIN("ADMIN"),
    RECEPTIONIST("RECEPTIONIST"),
    MANAGER("MANAGER"),
    MECHANIC("MECHANIC"),
    CUSTOMER("CUSTOMER");

    private final String text;

    UserRoleEnum(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
