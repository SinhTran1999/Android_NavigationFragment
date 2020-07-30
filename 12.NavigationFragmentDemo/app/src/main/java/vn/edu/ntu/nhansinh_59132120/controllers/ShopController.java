package vn.edu.ntu.nhansinh_59132120.controllers;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.nhansinh_59132120.models.Product;

public class ShopController implements IShopController {
  private static ShopController intance;
  List<Product> products;

  private ShopController() {
    products = new ArrayList<>();
    products.add(new Product("Cá mú", 200000, "Cá mú mú mu mù mu"));
    products.add(new Product("Cá bóp", 150000, "Cá bóp bọp bọp bóp"));
    products.add(new Product("Cá bè", 100000, "Cá bè bé bè be"));
    products.add(new Product("Cá chim", 100000, "Cá chim chím chìm chim"));
    products.add(new Product("Cá dìa", 150000, "Cá dìa día dìa dia"));
  };

  public static ShopController getIntance() {
    if (intance == null) {
      intance = new ShopController();
    }
    return intance;
  }

  @Override
  public List<Product> getAllProduct() {
    return products;
  }

  @Override
  public void addProduct(Product product) {
    products.add(product);
  }
}
