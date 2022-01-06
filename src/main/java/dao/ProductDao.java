package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    protected Connection getConnection() {
        String jdbcURL = "jdbc:mysql://localhost:3306/QLSPModule3";
        String jdbcUsername = "root";
        String jdbcPassword = "12345678";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void createProductDao(Product product) {
        String saveCustomer = "INSERT INTO product (name,price,quantily,color,description,idCategory) VALUES (?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = getConnection().prepareStatement(saveCustomer);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantily());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getIdCategory());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Product> showListProduct() {
        String getall = "select product.* , category.namenameCategory from product join category on product.idCategory = category.idCategory";
        List<Product> productList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getall)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                int quantily = rs.getInt("quantily");
                String color = rs.getString("color");
                String description = rs.getString("description");
                String namecategory = rs.getString("nameCategory");
                productList.add(new Product(id, name, price, quantily,color,description,namecategory));
            }
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> searchByName(String findname) {
        String getall = "select product.* , category.nameCategory from product\n" +
                " join category on product.idCategory = category.idCategory where product.name like '%" + findname +"%'";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery(getall);
            List<Product> productList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                int quantily = rs.getInt("quantily");
                String color = rs.getString("color");
                String description = rs.getString("description");
                String namecategory = rs.getString("nameCategory");
                productList.add(new Product(id, name, price, quantily,color,description,namecategory));
            }
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void deleteProductDao(int id) {
        String deleteSQL = "DELETE  from product where id=?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(deleteSQL);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateProductDao(int id, Product product) throws SQLException {
        String editProduct = "update product set name = ?,price = ?, quantily =?,color =?, description = ? where id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(editProduct)){
            statement.setString(1,product.getName());
            statement.setDouble(2,product.getPrice());
            statement.setInt(3,product.getQuantily());
            statement.setString(4,product.getColor());
            statement.setString(5,product.getDescription());
            statement.setInt(6,id);
            statement.execute();
        }
    }
}
