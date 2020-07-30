package vn.edu.ntu.nhansinh_59132120.navigationfragmentdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.ntu.nhansinh_59132120.controllers.ICartController;
import vn.edu.ntu.nhansinh_59132120.models.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
  Button btnBuyCart, btnDeleteCart;
  TextView txtCartInfo;
  ICartController cartController;
  NavController navController;

  public CartFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    getActivity().setTitle("GIỎ HÀNG");

    // Inflate the layout for this fragment
    View view =  inflater.inflate(R.layout.fragment_cart, container, false);
    btnBuyCart = view.findViewById(R.id.btnBuyCart);
    btnDeleteCart = view.findViewById(R.id.btnDeleteCart);
    txtCartInfo = view.findViewById(R.id.txtCartInfo);

    cartController = ((MainActivity)getActivity()).cartController;

    List<Product> products = cartController.getProducts();
    if (products.size() > 0) {
      StringBuilder stringBuilder = new StringBuilder();
      for (Product product : products) {
        stringBuilder.append(product.getName());
        stringBuilder.append(": ");
        stringBuilder.append(product.getPrice());
        stringBuilder.append("đ\n");
      }
      txtCartInfo.setText(stringBuilder.toString());
    } else {
      txtCartInfo.setText("Không có mặt hàng trong giỏ");
    }

    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    btnBuyCart.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        List<Product> products = cartController.getProducts();
        if (products.size() > 0) {
          StringBuilder stringBuilder = new StringBuilder();
          int sumPrice = 0;
          for (Product product : products) {
            stringBuilder.append(product.getName());
            stringBuilder.append(": ");
            stringBuilder.append(product.getPrice());
            stringBuilder.append("đ\n");
            sumPrice += product.getPrice();
          }
          stringBuilder.append("Thành tiền: ");
          stringBuilder.append(sumPrice);
          stringBuilder.append("đ");

          Bundle bundle = new Bundle();
          bundle.putString("info", stringBuilder.toString());

          cartController.clearCart();
          txtCartInfo.setText("Không có mặt hàng trong giỏ");

          NavHostFragment.findNavController(CartFragment.this)
                  .navigate(R.id.action_cartFragment_to_confirmFragment, bundle);
        } else {
          Toast.makeText(getActivity(), "Không có mặt hàng trong giỏ, không thể đặt hàng", Toast.LENGTH_SHORT).show();
        }
      }
    });

    btnDeleteCart.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        cartController.clearCart();
        txtCartInfo.setText("Không có mặt hàng trong giỏ");
      }
    });
  }


//  @Override
//  public void onAttach(@NonNull Context context) {
//    super.onAttach(context);
//    navController = NavHostFragment.findNavController(this);
//    ((MainActivity) getActivity()).navController = navController;
//  }
}
