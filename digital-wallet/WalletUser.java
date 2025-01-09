public class WalletUser {
    private String userId;
    private String name;
    private String email;
    private Address address;

    public WalletUser(Address address, String email, String name, String userId) {
        this.address = address;
        this.email = email;
        this.name = name;
        this.userId = userId;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }
}
