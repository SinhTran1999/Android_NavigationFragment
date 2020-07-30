package vn.edu.ntu.nhansinh_59132120.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "gio_hang",
        foreignKeys = @ForeignKey(entity = Product.class, parentColumns = "ma_sanpham", childColumns = "ma_sanpham"))
public class Cart {
  @NonNull
  @ColumnInfo(name = "ma_sanpham")
  @PrimaryKey
  int id;

  public Cart(int id) {
    this.id = id;
  }
}