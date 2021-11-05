package com.a209350309.i_learn.ui.me;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.a209350309.i_learn.Login;
import com.a209350309.i_learn.R;
import com.a209350309.i_learn.Service.UserService;

import static android.content.Context.MODE_PRIVATE;

public class MeFragment extends Fragment {

    private MeViewModel meViewModel;
    TextView text_username;
    TextView text_telephone;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        meViewModel =
                new ViewModelProvider(this).get(MeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_me, container, false);

        text_username=root.findViewById(R.id.text_username);
        SharedPreferences preferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);//fragment中获取SharedPreferences需要多加一步getActivity()方法
        String username = preferences.getString("username", "");
        text_username.setText(username);

        UserService uService = new UserService(MeFragment.this.getActivity());
        Cursor userInfo=uService.SelectLogin(username);
        text_telephone=root.findViewById(R.id.text_telephone);
        if (userInfo==null){
            Toast.makeText(MeFragment.this.getActivity(), "您的user表中无数据！！！", Toast.LENGTH_SHORT).show();
        }else {
            while (userInfo.moveToNext()){
                String telephone=userInfo.getString(userInfo.getColumnIndex("telephone"));
                System.out.println(telephone);
                text_telephone.setText(telephone);
            }

        }

//        String telephone=userInfo.getString(userInfo.getColumnIndex("telephone"));
//        System.out.println(telephone);
//        text_telephone.setText(telephone);


        return root;
    }
}