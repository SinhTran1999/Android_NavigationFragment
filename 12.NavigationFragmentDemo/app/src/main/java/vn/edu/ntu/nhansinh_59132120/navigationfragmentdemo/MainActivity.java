package vn.edu.ntu.nhansinh_59132120.navigationfragmentdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import vn.edu.ntu.nhansinh_59132120.controllers.CartControllerDB;
import vn.edu.ntu.nhansinh_59132120.controllers.ICartController;
import vn.edu.ntu.nhansinh_59132120.controllers.IShopController;
import vn.edu.ntu.nhansinh_59132120.controllers.ShopControllerDB;

public class MainActivity extends AppCompatActivity {
  NavController navController;
  IShopController shopController;
  ICartController cartController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

//    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    getSupportActionBar().setDisplayShowHomeEnabled(true);

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        navController.navigateUp();
      }
    });

    shopController = new ShopControllerDB(this);
    cartController = new CartControllerDB(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_exit) {
      finish();
    } else if (id == android.R.id.home) {
      Toast.makeText(this, "safsf", Toast.LENGTH_SHORT).show();
    }

    return super.onOptionsItemSelected(item);
  }
}
