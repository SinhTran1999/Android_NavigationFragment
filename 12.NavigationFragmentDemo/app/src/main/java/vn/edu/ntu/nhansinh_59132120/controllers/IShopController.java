package vn.edu.ntu.nhansinh_59132120.controllers;

import java.util.List;

import vn.edu.ntu.nhansinh_59132120.models.Product;

public interface IShopController {
  public List<Product> getAllProduct();
  public void addProduct(Product product);
}
