public class Address {
    private String pincode;
    private String city;
    private String state;
    private String country;

    public Address(String pincode, String city, String state, String country) {
        this.pincode = pincode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
}
