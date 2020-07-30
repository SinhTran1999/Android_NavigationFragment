package vn.edu.ntu.nhansinh_59132120.controllers;

import java.util.List;

import vn.edu.ntu.nhansinh_59132120.models.Product;

public interface ICartController {
  public boolean addProduct(Product product);
  public List<Product> getProducts();
  public void clearCart();
}
