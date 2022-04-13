package production.enums;

public enum City {
    LONDON ("London", "SW1A 2AA"),
    ZAGREB ("Zagreb", "10000"),
    NEW_YORK ("New York", "10001"),
    MUNICH ("Munich", "80331"),
    ZURICH ("Zurich", "8000"),
    MILANO ("Milano", "20019"),
    VELIKA_GORICA ("Velika Gorica", "10408"),
    TOMISLAVGRAD ("Tomislavgrad", "80240"),
    HUM ("Hum", "52425");

    private final String name;
    private final String postalCode;

    City(String name, String postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
