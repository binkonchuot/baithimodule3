package dao;

import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    ConnectionJDBC connectionJDBC = new ConnectionJDBC();

    public List<Category> showListCategory() {
        String getall = "SELECT * FROM category";
        List<Category> categoryList = new ArrayList<>();

        try (Connection connection = connectionJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getall)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idcategory");
                String name = rs.getString("namecategory");

                categoryList.add(new Category(id, name));
            }
            return categoryList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}