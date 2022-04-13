package database;

import production.model.*;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {

    private static Connection connectToDatabase() throws SQLException, IOException {
        Properties configuration = new Properties();
        configuration.load(new FileReader("dat/database.properties"));

        String databaseURL = configuration.getProperty("databaseURL");
        String databaseUsername = configuration.getProperty("databaseUsername");
        String databasePassword = configuration.getProperty("databasePassword");

        return DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
    }

    private static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public static List<Category> getCategories() throws SQLException, IOException {
        Connection connection = connectToDatabase();
        List<Category> categories = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORY");

        while (resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String description = resultSet.getString("DESCRIPTION");

            Category newCategory = new Category(id, name, description);
            categories.add(newCategory);
        }

        connection.close();
        return categories;
    }

    public static List<Item> getItems() throws SQLException, IOException {

        List<Category> categories = new ArrayList<>();
        Discount discount = new Discount(0);

        try {
            categories = Database.getCategories();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        Connection connection = connectToDatabase();
        List<Item> items = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ITEM");

        while (resultSet.next()) {
            Long id = resultSet.getLong("ID");
            Long catId = resultSet.getLong("CATEGORY_ID");
            String name = resultSet.getString("NAME");
            BigDecimal width = resultSet.getBigDecimal("WIDTH");
            BigDecimal height = resultSet.getBigDecimal("HEIGHT");
            BigDecimal length = resultSet.getBigDecimal("LENGTH");
            BigDecimal productionCost = resultSet.getBigDecimal("PRODUCTION_COST");
            BigDecimal sellingPrice = resultSet.getBigDecimal("SELLING_PRICE");

            Category itemCategory = categories.get(0);

            for (Category category : categories) {
                if (category.getId().equals(catId)) {
                    itemCategory = category;
                }
            }

            Item newItem = new Item(id, name, itemCategory, width, height, length, productionCost, sellingPrice, discount);
            items.add(newItem);
        }

        connection.close();
        return items;
    }

    public static List<Address> getAddresses() throws SQLException, IOException {

        Connection connection = connectToDatabase();
        List<Address> addresses = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM ADDRESS");

        while (resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String street = resultSet.getString("STREET");
            String houseNumber = resultSet.getString("HOUSE_NUMBER");
            String city = resultSet.getString("CITY");
            int postalCode = resultSet.getInt("POSTAL_CODE");

            Address newAddress = new Address(id,street, houseNumber,city, postalCode);
            addresses.add(newAddress);
        }

        connection.close();
        return addresses;
    }

    public static List<Factory> getFactories() throws SQLException, IOException {
        List<Address> addresses = new ArrayList<>();

        try {
            addresses = Database.getAddresses();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        Connection connection = connectToDatabase();
        List<Factory> factories = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FACTORY");

        while (resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            Long addrId = resultSet.getLong("ADDRESS_ID");

            Address factoryAddress = addresses.get(0);

            for (Address address : addresses) {
                if (address.getId().equals(addrId)) {
                    factoryAddress = address;
                }
            }

            Factory newFactory = new Factory(id, name, factoryAddress);
            factories.add(newFactory);
        }

        connection.close();
        return factories;
    }

    public static List<Long> getFactoryItems(Long facId) throws SQLException, IOException {
        List<Long> listOfItems = new ArrayList<>();
        Connection connection = connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FACTORY_ITEM FI, ITEM I WHERE FI.FACTORY_ID = "+ facId +" AND FI.ITEM_ID = I.ID;");

        while (resultSet.next()) {
            Long id = resultSet.getLong("ITEM_ID");
            listOfItems.add(id);
        }

        connection.close();
        return listOfItems;
    }

    public static List<Store> getStores() throws SQLException, IOException {
        Connection connection = connectToDatabase();
        List<Store> stores = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM STORE");

        while (resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            String webAddress = resultSet.getString("WEB_ADDRESS");

            Store newStore = new Store(id, name, webAddress);
            stores.add(newStore);
        }

        connection.close();
        return stores;
    }

    public static List<Long> getStoreItems(Long storeId) throws SQLException, IOException {
        List<Long> listOfItems = new ArrayList<>();
        Connection connection = connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM STORE_ITEM SI, ITEM I WHERE SI.STORE_ID = "+ storeId +" AND SI.ITEM_ID = I.ID;");

        while (resultSet.next()) {
            Long id = resultSet.getLong("ITEM_ID");
            listOfItems.add(id);
        }

        connection.close();
        return listOfItems;
    }

    public static Item getItemById(Long itemId) throws SQLException, IOException {

        List<Category> categories = new ArrayList<>();
        Discount discount = new Discount(0);

        try {
            categories = Database.getCategories();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        Connection connection = connectToDatabase();
        PreparedStatement getItem = connection.prepareStatement("SELECT * FROM ITEM WHERE ID = ?");
        getItem.setLong(1, itemId);
        ResultSet resultSet = getItem.executeQuery();

        Long id = itemId;
        Long catId = (long) 1;
        String name = "";
        BigDecimal width = BigDecimal.ZERO;
        BigDecimal height = BigDecimal.ZERO;
        BigDecimal length = BigDecimal.ZERO;
        BigDecimal productionCost = BigDecimal.ZERO;
        BigDecimal sellingPrice = BigDecimal.ZERO;

        while (resultSet.next()) {
            id = resultSet.getLong("ID");
            catId = resultSet.getLong("CATEGORY_ID");
            name = resultSet.getString("NAME");
            width = resultSet.getBigDecimal("WIDTH");
            height = resultSet.getBigDecimal("HEIGHT");
            length = resultSet.getBigDecimal("LENGTH");
            productionCost = resultSet.getBigDecimal("PRODUCTION_COST");
            sellingPrice = resultSet.getBigDecimal("SELLING_PRICE");
        }
        Category itemCategory = categories.get(0);

        for (Category category : categories) {
            if (category.getId().equals(catId)) {
                itemCategory = category;
            }
        }
        Item newItem = new Item(id, name, itemCategory, width, height, length, productionCost, sellingPrice, discount);

        connection.close();
        return newItem;
    }

    public static Category getCategoryById(Long categoryId) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement getCategory = connection.prepareStatement("SELECT * FROM CATEGORY WHERE ID = ?");
        getCategory.setLong(1, categoryId);
        ResultSet resultSet = getCategory.executeQuery();

        String name = "";
        String description = "";

        while (resultSet.next()) {
            name = resultSet.getString("NAME");
            description = resultSet.getString("DESCRIPTION");
        }
        Category newCategory = new Category(categoryId, name, description);
        connection.close();
        return newCategory;
    }

    public static Address getAddressById(Long addressId) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement getAddress = connection.prepareStatement("SELECT * FROM ADDRESS WHERE ID = ?");
        getAddress.setLong(1, addressId);
        ResultSet resultSet = getAddress.executeQuery();

        String street = "";
        String houseNumber = "";
        String city = "";
        int postalCode = 0;

        while (resultSet.next()) {
            street = resultSet.getString("STREET");
            houseNumber = resultSet.getString("HOUSE_NUMBER");
            city = resultSet.getString("CITY");
            postalCode = resultSet.getInt("POSTAL_CODE");
        }
        Address newAddress = new Address(addressId, street, houseNumber, city, postalCode);
        connection.close();
        return newAddress;
    }

    public static Factory getFactoryById(Long factoryId) throws SQLException, IOException {
        List<Address> addresses = new ArrayList<>();

        try {
            addresses = Database.getAddresses();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        Connection connection = connectToDatabase();
        PreparedStatement getFactory = connection.prepareStatement("SELECT * FROM FACTORY WHERE ID = ?");
        getFactory.setLong(1, factoryId);
        ResultSet resultSet = getFactory.executeQuery();

        String name = "";
        Long addrId = (long) 1;

        while (resultSet.next()) {
            name = resultSet.getString("NAME");
            addrId = resultSet.getLong("ADDRESS_ID");
        }
        Address factoryAddress = addresses.get(0);

        for (Address address : addresses) {
            if (address.getId().equals(addrId)) {
                factoryAddress = address;
            }
        }
        Factory newFactory = new Factory(factoryId, name, factoryAddress);

        connection.close();
        return newFactory;
    }

    public static Store getStoreById(Long storeId) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement getStore = connection.prepareStatement("SELECT * FROM STORE WHERE ID = ?");
        getStore.setLong(1, storeId);
        ResultSet resultSet = getStore.executeQuery();

        String name = "";
        String webAddress = "";

        while (resultSet.next()) {
            name = resultSet.getString("NAME");
            webAddress = resultSet.getString("WEB_ADDRESS");
        }
        Store newStore = new  Store(storeId, name, webAddress);
        connection.close();
        return  newStore;
    }

    public static void saveCategory(Category category) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO CATEGORY (NAME, DESCRIPTION) VALUES(?, ?)");
        statement.setString(1, category.getName());
        statement.setString(2, category.getDescription());
        statement.executeUpdate();
        connection.close();
    }

    public static void saveItem(Item item) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO ITEM (CATEGORY_ID, NAME, WIDTH, HEIGHT, LENGTH, PRODUCTION_COST, SELLING_PRICE) VALUES(?, ?, ?, ?, ?, ?, ?)");
        statement.setLong(1, item.getCategory().getId());
        statement.setString(2, item.getName());
        statement.setBigDecimal(3, item.getWidth());
        statement.setBigDecimal(4, item.getHeight());
        statement.setBigDecimal(5, item.getLength());
        statement.setBigDecimal(6, item.getProductionCost());
        statement.setBigDecimal(7, item.getSellingPrice());
        statement.executeUpdate();
        connection.close();
    }

    public static void saveAddress(Address address) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO ADDRESS (STREET, HOUSE_NUMBER, CITY, POSTAL_CODE) VALUES(?, ?, ?, ?)");
        statement.setString(1, address.getStreet());
        statement.setString(2, address.getHouseNumber());
        statement.setString(3, address.getCity());
        statement.setInt(4, address.getPostalCode());
        statement.executeUpdate();
        connection.close();
    }

    public static void saveFactory(Factory factory) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO FACTORY (NAME, ADDRESS_ID) VALUES(?, ?)");
        statement.setString(1, factory.getName());
        statement.setLong(2, factory.getAddress().getId());
        statement.executeUpdate();
        connection.close();
    }

    public static void addFactoryItem(Long facId, Long itemId) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO FACTORY_ITEM (FACTORY_ID, ITEM_ID) VALUES(?, ?)");
        statement.setLong(1, facId);
        statement.setLong(2, itemId);
        statement.executeUpdate();
        connection.close();
    }

    public static void saveStore(Store store) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO STORE (NAME, WEB_ADDRESS) VALUES(?, ?)");
        statement.setString(1, store.getName());
        statement.setString(2, store.getWebAddress());
        statement.executeUpdate();
        connection.close();
    }

    public static void addStoreItem(Long storeId, Long itemId) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO STORE_ITEM (STORE_ID, ITEM_ID) VALUES(?, ?)");
        statement.setLong(1, storeId);
        statement.setLong(2, itemId);
        statement.executeUpdate();
        connection.close();
    }
}




























