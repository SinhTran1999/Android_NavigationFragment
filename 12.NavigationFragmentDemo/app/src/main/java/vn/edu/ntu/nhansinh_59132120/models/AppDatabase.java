package vn.edu.ntu.nhansinh_59132120.models;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class, Cart.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
  public abstract DAO getDAO();
}
