package vn.edu.ntu.nhansinh_59132120.controllers;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.nhansinh_59132120.models.Product;

public class CartController extends Application implements ICartController {
  List<Product> products = new ArrayList<>();
  @Override
  public boolean addProduct(Product product) {
    if (!products.contains(product)) {
      products.add(product);
      return true;
    }
    return false;
  }

  @Override
  public List<Product> getProducts() {
    return products;
  }

  @Override
  public void clearCart() {
    products.clear();
  }
}
