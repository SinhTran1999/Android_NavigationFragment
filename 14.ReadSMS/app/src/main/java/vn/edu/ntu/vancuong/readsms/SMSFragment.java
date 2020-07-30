package vn.edu.ntu.vancuong.readsms;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

public class SMSFragment extends Fragment {

    ListView lvSMS;
    ArrayList<String> dsSMS = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    boolean duocPhepDocSMS = false;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_sms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        duocPhepDocSMS = ((MainActivity)getActivity()).duocNhapDocSMS;
        lvSMS = view.findViewById(R.id.lvSMS);
        arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,dsSMS);
        lvSMS.setAdapter(arrayAdapter);
        if (duocPhepDocSMS)
            hienThiSMS();
    }

    private void hienThiSMS() {
        Bundle data = getArguments();
        String strTuNgay = data.getString(LuaChonFragment.KEY_TU_NGAY);
        String strDenNgay = data.getString(LuaChonFragment.KEY_DEN_NGAY);
        Date tuNgay = Date.valueOf(strTuNgay);
        Date denNgay = Date.valueOf(strDenNgay);
        dsSMS.clear();
        // Uri.parse(*Context://sms/index*);
        Uri uriSMS = Telephony.Sms.CONTENT_URI;
        ContentResolver resolver = getActivity().getContentResolver();
        Cursor cursor = resolver.query(uriSMS,new String[]{"address","date","body"},null,null,null);
        if (cursor!=null) {
            int addressCol = cursor.getColumnIndex("address");
            int dateCol = cursor.getColumnIndex("date");
            int bodyCol = cursor.getColumnIndex("body");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            while (cursor.moveToNext()) {
               long s = cursor.getLong(dateCol);
               Date date = new Date(s);
               if (date.compareTo(tuNgay)==1 && date.compareTo(denNgay)==-1){
                   String address = cursor.getString(addressCol);
                   String body = cursor.getString(bodyCol);
                   String sms = address + "\n" + dateFormat.format(date) + "\n" + body;
                   dsSMS.add(sms);

               }
            }
        }
        arrayAdapter.notifyDataSetChanged();
    }
}