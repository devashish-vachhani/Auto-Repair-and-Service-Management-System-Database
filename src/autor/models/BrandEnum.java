package models;

public enum BrandEnum {
    HONDA("HONDA"),
    NISSAN("NISSAN"),
    TOYOTA("TOYOTA"),
    LEXUS("LEXUS"),
    INFINITI("INFINITI");

    private final String text;

    BrandEnum(final String text) {
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
