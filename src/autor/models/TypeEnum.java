package models;

public enum TypeEnum {
    M("M"),
    R("R"),
    MR("MR");

    private final String text;

    TypeEnum(final String text) {
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
