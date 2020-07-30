package vn.edu.ntu.nhansinh_59132120.controllers;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

import vn.edu.ntu.nhansinh_59132120.models.AppDatabase;
import vn.edu.ntu.nhansinh_59132120.models.Cart;
import vn.edu.ntu.nhansinh_59132120.models.DAO;
import vn.edu.ntu.nhansinh_59132120.models.Product;

public class CartControllerDB implements ICartController {
  AppDatabase database;
  DAO dao;

  public CartControllerDB(Context context) {
    database = Room.databaseBuilder(context, AppDatabase.class, "appdb")
            .allowMainThreadQueries()
            .build();
    dao = database.getDAO();
  }

  @Override
  public boolean addProduct(Product product) {
    int id = product.getId();
    if (dao.isProductExistsInCart(id) == 0) {
      dao.insertProductToCart(new Cart(id));
      return true;
    }
    return false;
  }

  @Override
  public List<Product> getProducts() {
    return dao.getAllProductInCart();
  }

  @Override
  public void clearCart() {
    dao.clearCart();
  }
}
