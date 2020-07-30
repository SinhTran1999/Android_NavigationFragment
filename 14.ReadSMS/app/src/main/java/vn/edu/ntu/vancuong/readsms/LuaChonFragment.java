package vn.edu.ntu.vancuong.readsms;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;

public class LuaChonFragment extends Fragment implements View.OnClickListener {

    public static final String KEY_TU_NGAY = "tu_ngay";
    public static final String KEY_DEN_NGAY = "den_ngay";
    EditText editTuNgay, editDenNgay;
    ImageView imvTuNgay, imvDenNgay;
    ImageButton imbHienThi;
    NavController navController;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        return inflater.inflate(R.layout.fragment_luachon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTuNgay = view.findViewById(R.id.editTuNgay);
        editDenNgay = view.findViewById(R.id.editDenNgay);
        imvTuNgay = view.findViewById(R.id.imvTuNgay);
        imvDenNgay = view.findViewById(R.id.imvDenNgay);
        imbHienThi = view.findViewById(R.id.imbHienThi);

        imvTuNgay.setOnClickListener(this);
        imvDenNgay.setOnClickListener(this);
        imbHienThi.setOnClickListener(this);

        navController = NavHostFragment.findNavController(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imvTuNgay: thietLapNgay(0); break;
            case R.id.imvDenNgay: thietLapNgay(1); break;
            case R.id.imbHienThi: hienThiSMS(); break;
        }
    }

    private void hienThiSMS() {
        if (editTuNgay.getText().toString().length() > 0 && editDenNgay.getText().toString().length() > 0){
            Bundle date = new Bundle();
            date.putCharSequence(KEY_TU_NGAY,editTuNgay.getText().toString());
            date.putCharSequence(KEY_DEN_NGAY,editDenNgay.getText().toString());
            navController.navigate(R.id.action_FirstFragment_to_SecondFragment, date);
        }

    }

    private void thietLapNgay(final int luachon) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                StringBuilder builder = new StringBuilder();
                builder.append(year)
                        .append("-")
                        .append(month)
                        .append("-")
                        .append(dayOfMonth);
                if (luachon == 0)
                    editTuNgay.setText(builder.toString());
                else
                    editDenNgay.setText(builder.toString());
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),listener,
                calendar.get(calendar.YEAR),
                calendar.get(calendar.MONTH),
                calendar.get(calendar.DAY_OF_MONTH)
                );
        datePickerDialog.show();
    }



}