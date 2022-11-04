package models;

public enum EmployeeRoleEnum {

    ADMIN("ADMIN"),
    RECEPTIONIST("RECEPTIONIST"),
    MANAGER("MANAGER"),
    MECHANIC("MECHANIC");

    private final String text;

    EmployeeRoleEnum(final String text) {
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
