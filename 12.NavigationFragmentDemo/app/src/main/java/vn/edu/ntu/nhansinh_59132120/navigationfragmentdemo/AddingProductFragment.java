package vn.edu.ntu.nhansinh_59132120.navigationfragmentdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import vn.edu.ntu.nhansinh_59132120.controllers.IShopController;
import vn.edu.ntu.nhansinh_59132120.models.Product;

public class AddingProductFragment extends Fragment {
  EditText edtName, edtPrice, edtDesc;
  Button btnAdd;
  IShopController shopController;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    getActivity().setTitle("THÊM MẶT HÀNG");

    shopController = ((MainActivity)getActivity()).shopController;

    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_add_product, container, false);
    edtName = view.findViewById(R.id.edtName);
    edtPrice = view.findViewById(R.id.edtPrice);
    edtDesc = view.findViewById(R.id.edtDesc);
    btnAdd = view.findViewById(R.id.btnAdd);

    return view;
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    btnAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String name = edtName.getText().toString();
        String price = edtPrice.getText().toString();
        String desc = edtDesc.getText().toString();

        if (name.length() > 0 && price.length() > 0 && desc.length() > 0) {
//          ShopController.getIntance().addProduct(new Product(name, new Integer(price), desc));
          shopController.addProduct(new Product(name, new Integer(price), desc));
          Toast.makeText(getActivity(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
          edtName.setText("");
          edtPrice.setText("");
          edtDesc.setText("");
        } else {
          Toast.makeText(getActivity(), "Chưa nhập đủ thông tin sản phẩm", Toast.LENGTH_SHORT).show();
        }
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
