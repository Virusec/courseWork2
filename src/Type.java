public enum Type {
    PERSONAL(1),
    WORK(2);

    private int enumConstant;

    public int getEnumConstant() {
        return enumConstant;
    }

    Type(int i) {
        this.enumConstant = i;
    }

    public static Type getEnumFromConstant(int i) {
        return values()[i];
    }
}
