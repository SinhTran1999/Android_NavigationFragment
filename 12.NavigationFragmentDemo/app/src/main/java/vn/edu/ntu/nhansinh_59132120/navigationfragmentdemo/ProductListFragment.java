package vn.edu.ntu.nhansinh_59132120.navigationfragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import vn.edu.ntu.nhansinh_59132120.controllers.ICartController;
import vn.edu.ntu.nhansinh_59132120.controllers.IShopController;
import vn.edu.ntu.nhansinh_59132120.models.Product;

public class ProductListFragment extends Fragment {
  FloatingActionButton fab;
  RecyclerView rvProductList;
  ProductAdapter productAdapter;
  ICartController cartController;
  IShopController shopController;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public void onStart() {
    super.onStart();
    ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.mnu_cart, menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.miCart) {
      NavHostFragment.findNavController(this)
              .navigate(R.id.action_productListFragment_to_cartFragment);
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    getActivity().setTitle("SẢN PHẨM");

    shopController = ((MainActivity)getActivity()).shopController;
    cartController = ((MainActivity)getActivity()).cartController;

    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_product_list, container, false);
    fab = view.findViewById(R.id.floatingActionButton);

    rvProductList = view.findViewById(R.id.rvProductList);
    // set recycle product list
    rvProductList.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    productAdapter = new ProductAdapter(shopController.getAllProduct());
    rvProductList.setAdapter(productAdapter);

    // cartController = (CartController) getActivity().getApplication();

    return view;
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    // set fab click
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        NavHostFragment.findNavController(ProductListFragment.this)
                .navigate((R.id.action_productListFragment_to_addingProductFragment));
      }
    });
  }


  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    ((MainActivity) getActivity()).navController = NavHostFragment.findNavController(this);
  }

  private class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView txtName, txtPrice, txtDecs;
    ImageView imvAddToCart;
    Product product;

    public ProductHolder(@NonNull View itemView) {
      super(itemView);
      txtName = itemView.findViewById(R.id.txtProductName);
      txtPrice = itemView.findViewById(R.id.txtProductPrice);
      txtDecs = itemView.findViewById(R.id.txtProductDesc);
      imvAddToCart = itemView.findViewById(R.id.imvAddToCart);

      imvAddToCart.setOnClickListener(this);
    }

    public void bind(Product product) {
      this.product = product;
      txtName.setText(product.getName());
      txtPrice.setText("" + product.getPrice());
      txtDecs.setText(product.getDesc());
    }

    @Override
    public void onClick(View v) {
      if (cartController.addProduct(product)) {
        Toast.makeText(getActivity(), "Đã thêm " + product.getName() + " vào giỏ hàng", Toast.LENGTH_SHORT).show();
      } else {
        Toast.makeText(getActivity(), "Thêm sản phẩm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
      }
    }
  }

  private class ProductAdapter extends RecyclerView.Adapter<ProductHolder> {
    List<Product> products;

    public ProductAdapter(List<Product> products) {
      this.products = products;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = getLayoutInflater();
      View view =  inflater.inflate(R.layout.product_item, parent, false);
      return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
      holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
      return products.size();
    }
  }
}
