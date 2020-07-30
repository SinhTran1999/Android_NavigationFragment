package vn.edu.ntu.nhansinh_59132120.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {
  // shop
  @Insert
  public void insertProduct(Product...products);
  @Update
  public void updateProduct(Product...products);
  @Delete
  public void deleteProduct(Product products);
  @Query("select * from sanpham")
  public List<Product> getAllProduct();
  @Query("select * from sanpham where ma_sanpham = :id")
  public Product getProductById(int id);

  // cart
  @Insert
  public void insertProductToCart(Cart...carts);
  @Query("select sp.* from gio_hang gh join sanpham sp on gh.ma_sanpham = sp.ma_sanpham")
  public List<Product> getAllProductInCart();
  @Query("delete from gio_hang")
  public void clearCart();
  @Query("select count(*) from gio_hang where ma_sanpham = :id")
  public int isProductExistsInCart(int id);
}
