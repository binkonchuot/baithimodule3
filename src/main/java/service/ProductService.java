package service;

import dao.ProductDao;
import model.Product;

import java.util.List;

public class ProductService {
    ProductDao productDao = new ProductDao();
    List<Product> productList = productDao.showListProduct();

    public void add(Product product) {
        productDao.createProductDao(product);
        productList = productDao.showListProduct();
    }

    public void delete (int id) {
        productDao.deleteProduct(id);
        productDao.showListProduct();
    }

    public void edit(int id ,Product product)  {
        productDao.updateProductDao(id,product);
        productDao.showListProduct();
    }
}