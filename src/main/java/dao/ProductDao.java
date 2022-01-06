package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    ConnectionJDBC connectionJDBC = new ConnectionJDBC();

    public void createProductDao(Product product) {
        String saveproduct = "INSERT INTO product (name,price,soluong,mausac,depcription,idcategory) VALUES (?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = connectionJDBC.getConnection().prepareStatement(saveproduct);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getSoluong());
            preparedStatement.setString(4, product.getMausac());
            preparedStatement.setString(5, product.getDepcription());
            preparedStatement.setInt(6, product.getIdcategory());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public List<Product> showListProduct() {
        String show = "select product.* , category.namecategory from product join category on product.idcategory = category.idcategory";
        List<Product> productList = new ArrayList<>();

        try (Connection connection = connectionJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(show)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                int soluong = rs.getInt("soluong");
                String mausac = rs.getString("mausac");
                String depcription = rs.getString("depcription");
                String namecategory = rs.getString("namecategory");
                productList.add(new Product(id, name, price, soluong,mausac,depcription,namecategory));
            }
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> searchByName(String findname) {
        String getall = "select product.* , category.namecategory from product\n" +
                " join category on product.idcategory = category.idcategory where product.name like '%" + findname +"%'";
        try {
            Statement statement = connectionJDBC.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(getall);
            List<Product> productList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                int soluong = rs.getInt("soluong");
                String mausac = rs.getString("mausac");
                String depcription = rs.getString("depcription");
                String namecategory = rs.getString("namecategory");
                productList.add(new Product(id, name, price, soluong,mausac,depcription,namecategory));
            }
            return productList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void deleteProduct(int id) {
        String deleteSQL = "DELETE  from product where id=?";
        try {
            PreparedStatement preparedStatement = connectionJDBC.getConnection().prepareStatement(deleteSQL);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateProductDao(int id, Product product){
        String editProduct = "update product set name = ?,price = ?,soluong =? ,mausac =? ,depcription =? ,idcategory =? where id = ?";

        try (Connection connection = connectionJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(editProduct)){
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getSoluong());
            preparedStatement.setString(4, product.getMausac());
            preparedStatement.setString(5, product.getDepcription());
            preparedStatement.setInt(6, product.getIdcategory());
            preparedStatement.setInt(7,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}