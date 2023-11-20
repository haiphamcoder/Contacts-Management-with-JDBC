package com.haiphamcoder;

public class Contact {
    private long id;
    private String name;
    private String email;
    private String phone;

    public Contact() {
    }

    public Contact(long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email must not be null or empty");
        }
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Phone must not be null or empty");
        }
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{\n\t" +
                "id=" + id + ",\n\t" +
                "name='" + name + "',\n\t" +
                "email='" + email + "',\n\t" +
                "phone=" + phone + "\n" +
                '}';
    }
}
