package com.a209350309.i_learn.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.a209350309.i_learn.CourseHome;
import com.a209350309.i_learn.Courses;
import com.a209350309.i_learn.R;
import com.a209350309.i_learn.Service.CourseService;
import com.a209350309.i_learn.WriteShow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private CourseService courseService=null;
    private ListView listView2 = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        courseService=new CourseService(HomeFragment.this.getActivity());
        listView2 = (ListView) root.findViewById(R.id.listView2);
        show();
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        setHasOptionsMenu(true);//这句没有菜单就出不来
        return root;
    }

    @Override
    public void  onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_option_course,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this.getActivity(),item.getTitle(),Toast.LENGTH_LONG).show();
        switch (item.getItemId()){
            case R.id.menu_course:
                Intent intent = new Intent(HomeFragment.this.getActivity(), Courses.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void show(){
        SharedPreferences preferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);//fragment中获取SharedPreferences需要多加一步getActivity()方法
        String username = preferences.getString("username", "");
        Cursor course=courseService.SelectCourse(username);
        List<HashMap<String ,Object>> data2 = new ArrayList<HashMap<String ,Object>>();
        while (course.moveToNext()){
            HashMap<String , Object> item2 = new HashMap<String , Object>();
            item2.put("courseId_item2",course.getString(course.getColumnIndex("id")));
            item2.put("course_name_item2",course.getString(course.getColumnIndex("course_name")));
            item2.put("course_teacher_item2",course.getString(course.getColumnIndex("course_teacher")));
            data2.add(item2);
            System.out.println(course.getString(course.getColumnIndex("course_name")));
        }

        SimpleAdapter adapter = new SimpleAdapter(HomeFragment.this.getActivity(), data2, R.layout.item2,
                new String[]{"courseId_item2","course_name_item2", "course_teacher_item2"}, new int[]{R.id.courseId_item2,R.id.course_name_item2,R.id.course_teacher_item2});
        listView2.setAdapter(adapter);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int listView, long id) {
                TextView course_name_item2 = (TextView) view.findViewById(R.id.course_name_item2);
//                Cursor cursor= listView.getItemAtPosition(listView);
//                String writeId = cursor.getString(cursor.getColumnIndex("writeId"));
//                Object o = listView.getItemAtPosition(position);
                System.out.println(course_name_item2.getText());
                Intent intent=new Intent(HomeFragment.this.getActivity(), CourseHome.class);
                intent.putExtra("course_name_item2",course_name_item2.getText());
                startActivity(intent);
            }
        });
    }
}