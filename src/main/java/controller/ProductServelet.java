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

import java.util.List;
@WebServlet(urlPatterns = "/product")
public class ProductServelet extends HttpServlet {

    ProductService productService = new ProductService();
    ProductDao productDao = new ProductDao();
    CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";

        }
        switch (action) {
            case "create":
                List<Category> categoryList = categoryDao.showListCategory();
                request.setAttribute("categorys", categoryList);
                request.getRequestDispatcher("/view/createProduct.jsp").forward(request, response);
                break;
            case "edit":
                categoryList = categoryDao.showListCategory();
                request.setAttribute("categorys", categoryList);
                request.getRequestDispatcher("/view/editProduct.jsp").forward(request, response);
                break;
            case "delete":
                deleteproduct(request,response);
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
                createProduct(request,response);
                break;
            case "edit":
                editProduct(request,response);

                break;
            case "search":
                searchByName(request,response);
                break;
        }
    }

    public void searchByName (HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("search");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/showProduct.jsp");
        List<Product> productList = productDao.searchByName(name);;
        request.setAttribute("products", productList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    private void createProduct(HttpServletRequest request, HttpServletResponse response)  {
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        int soluong = Integer.parseInt(request.getParameter("soluong"));
        String mausac = request.getParameter("mausac");
        String depcription = request.getParameter("depcription");
        int idCategory = Integer.parseInt(request.getParameter("idCategory"));
        productService.add(new Product(name,price,soluong,mausac,depcription,idCategory));
        try {
            response.sendRedirect("/product");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        int soluong = Integer.parseInt(request.getParameter("soluong"));
        String mausac = request.getParameter("mausac");
        String depcription = request.getParameter("depcription");
        int idCategory = Integer.parseInt(request.getParameter("idCategory"));

        Product product = (new Product(name, price, soluong, mausac,depcription,idCategory));
        productService.edit(id, product);

        try {
            response.sendRedirect("/product");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteproduct(HttpServletRequest request, HttpServletResponse response)  {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.delete(id);

        try {
            response.sendRedirect("/product");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/showProduct.jsp");
        List<Product> productList = productDao.showListProduct();
        request.setAttribute("products", productList);
        dispatcher.forward(request, response);
    }
}