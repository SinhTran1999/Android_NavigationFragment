package vn.edu.ntu.nhansinh_59132120.navigationfragmentdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmFragment extends Fragment {
  TextView txtInfo;
  Button btnConfirm;

  public ConfirmFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getActivity().setTitle("ĐẶT HÀNG");

    View view = inflater.inflate(R.layout.fragment_confirm, container, false);
    txtInfo = view.findViewById(R.id.txtInfo);
    btnConfirm = view.findViewById(R.id.btnConfirm);

    Bundle bundle = getArguments();
    if (bundle != null) {
      txtInfo.setText(bundle.getString("info"));
    }
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    btnConfirm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        NavHostFragment.findNavController(ConfirmFragment.this)
                .navigateUp();
      }
    });
  }

//
//  @Override
//  public void onAttach(@NonNull Context context) {
//    super.onAttach(context);
//    navController = NavHostFragment.findNavController(this);
//    ((MainActivity) getActivity()).navController = navController;
//  }
}
