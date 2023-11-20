package com.haiphamcoder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private static final String URL = "jdbc:mysql://localhost:3306/contact_information_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection successful");

            // Get all contacts
            List<Contact> lstContacts = getAllContacts(connection);
            System.out.println("All contacts:");
            for (Contact contact : lstContacts) {
                System.out.println(contact);
            }
            System.out.println();

            // Get contact by ID
            Contact contact = getContactById(connection, 2);
            System.out.println("Contact with ID = 2: \n" + contact);

            // Insert new contact
            Contact newContact = new Contact(
                    0,
                    "Nguyen Van F",
                    "nguyenvanf@gmail.com",
                    "0123456789"
            );
            addContact(connection, newContact);
            lstContacts = getAllContacts(connection);
            System.out.println("All contacts after adding new contact:");
            for (Contact c : lstContacts) {
                System.out.println(c);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void addContact(Connection connection, Contact contact) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contact (name, email, phone) " +
                "VALUES (?, ?, ?)");
        preparedStatement.setString(1, contact.getName());
        preparedStatement.setString(2, contact.getEmail());
        preparedStatement.setString(3, contact.getPhone());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private static void updateContact(Connection connection, Contact contact) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE contact SET name = ?, email = ?, " +
                "phone = ? " +
                "WHERE id = ?");
        preparedStatement.setString(1, contact.getName());
        preparedStatement.setString(2, contact.getEmail());
        preparedStatement.setString(3, contact.getPhone());
        preparedStatement.setLong(4, contact.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private static void deleteContact(Connection connection, long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM contact WHERE id = ?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private static List<Contact> getAllContacts(Connection connection) throws SQLException {
        List<Contact> lstContacts = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            lstContacts.add(new Contact(id, name, email, phone));
        }
        resultSet.close();
        preparedStatement.close();
        return lstContacts;
    }

    private static Contact getContactById(Connection connection, long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact WHERE id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Contact contact = null;
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            contact = new Contact(id, name, email, phone);
        }
        resultSet.close();
        preparedStatement.close();
        return contact;
    }
}