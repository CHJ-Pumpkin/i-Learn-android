package com.a209350309.i_learn.ui.message;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a209350309.i_learn.Db.DatabaseHelper;
import com.a209350309.i_learn.Entity.Message;
import com.a209350309.i_learn.R;
import com.a209350309.i_learn.RecyclerviewAdapter;
import com.a209350309.i_learn.Service.MessageService;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MessageFragment extends Fragment {

    private View view;
    private MessageService messageService=null;
    public RecyclerView RecyclerView5;//定义RecyclerView
    //定义以goodsentity实体类为对象的数据集合
    private ArrayList<Message> RecyclerviewAdapterArrayList = new ArrayList<Message>();
    private RecyclerviewAdapter RecyclerviewAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);

        messageService= new MessageService(MessageFragment.this.getActivity());
        initRecyclerView();
        //模拟数据
        initData();
        return view;
    }

    /**
     * TODO 模拟数据
     */
    private void initData() {
//        for (int i=0;i<10;i++){
//            Home home=new Home();
//            home.setName("模拟数据"+i);
//            home.setTeacher("100"+i);
//            homeArrayList.add(home);
//        }
//        String sqlSelect="SELECT * FROM messages";
        //扫描数据库，将信息放入
//        DatabaseHelper mdb = new DatabaseHelper(getActivity());//打开数据库
//        SQLiteDatabase sd = mdb.getReadableDatabase();//获取数据库
//        Cursor cursor=sd.rawQuery(sqlSelect,new String[]{});
        SharedPreferences preferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);//fragment中获取SharedPreferences需要多加一步getActivity()方法
        String username = preferences.getString("username", "");
        Cursor cursor=messageService.SelectMessage(username);
        while(cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndex("id"));
            String content=cursor.getString(cursor.getColumnIndex("message_content"));
            String time=cursor.getString(cursor.getColumnIndex("message_time"));
            Log.i("A",content);
            Message message =new Message();
            message.setId(id);
            message.setMessage_content(content);
            message.setMessage_time(time);
            RecyclerviewAdapterArrayList.add(message);
        }
        cursor.close();

    }

    /**
     * TODO 对recycleview进行配置
     */

    private void initRecyclerView() {
        //获取RecyclerView
        RecyclerView5=(RecyclerView)view.findViewById(R.id.rvView5);
        //创建adapter
        RecyclerviewAdapter = new RecyclerviewAdapter(getActivity(), RecyclerviewAdapterArrayList);
        //给RecyclerView设置adapter
        RecyclerView5.setAdapter(RecyclerviewAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        RecyclerView5.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        RecyclerView5.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
        RecyclerviewAdapter.setOnItemClickListener(new RecyclerviewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Message data) {
                //此处进行监听事件的业务处理
//                Toast.makeText(getActivity(),"我是item"+data.getId(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}