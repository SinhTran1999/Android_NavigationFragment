package vn.edu.ntu.nhansinh_59132120.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sanpham")
public class Product {
  @PrimaryKey(autoGenerate = true)
  @NonNull
  @ColumnInfo(name = "ma_sanpham")
  int id;
  @NonNull
  @ColumnInfo(name = "ten_sanpham")
  String name;
  @NonNull
  @ColumnInfo(name = "gia_sanpham")
  int price;
  @ColumnInfo(name = "mota_sanpham")
  private String desc;

  public Product(String name, int price, String desc) {
    this.name = name;
    this.price = price;
    this.desc = desc;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public String getDesc() {
    return desc;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
