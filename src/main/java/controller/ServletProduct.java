package controller;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;
import service.ProductService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet(urlPatterns = "/product")
public class ServletProduct extends HttpServlet {
    ProductDao productDao = new ProductDao();
    ProductService productService = new ProductService();
    CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                List<Category> categories = categoryDao.showListCategory();
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/createProduct.jsp").forward(request, response);
                break;
            case "edit":
                categories = categoryDao.showListCategory();
                request.setAttribute("categories", categories);
                request.getRequestDispatcher("/editProduct.jsp").forward(request, response);
                break;
            case "delete":
                try {
                    deleteProduct(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                showList(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    createProduct(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    editProduct(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "search":
                searchByName(request,response);
                break;
        }
    }
    public void searchByName (HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("search");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/showProduct.jsp");
        List<Product> productList = productDao.searchByName(name);;
        request.setAttribute("products", productList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        Double price = Double.valueOf(request.getParameter("price"));
        int quantily = Integer.parseInt(request.getParameter("quantily"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int idCategory = Integer.parseInt(request.getParameter("idCategory"));
        productService.add(new Product( name, price, quantily,color,description, idCategory));
        try {
            response.sendRedirect("/product");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void editProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Double price = Double.valueOf(request.getParameter("price"));
        int quantily = Integer.parseInt(request.getParameter("quantily"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");

        Product product = (new Product(id,name, price, quantily, color,description));
        productService.edit(id, product);
        response.sendRedirect("/product");
    }
    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.delete(id);
        response.sendRedirect("/product");
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/showProduct.jsp");
        List<Product> productList = productDao.showListProduct();
        request.setAttribute("products", productList);
        dispatcher.forward(request, response);
    }
}
