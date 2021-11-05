package com.a209350309.i_learn.ui.notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.a209350309.i_learn.Entity.Note;
import com.a209350309.i_learn.Login;
import com.a209350309.i_learn.R;
import com.a209350309.i_learn.Service.NoteService;
import com.a209350309.i_learn.Test;
import com.a209350309.i_learn.Write;
import com.a209350309.i_learn.WriteShow;
import com.a209350309.i_learn.ui.me.MeFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class NotesFragment extends Fragment {

    private NotesViewModel notesViewModel;
    private NoteService noteService=null;
    private ListView listView = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notesViewModel =
                new ViewModelProvider(this).get(NotesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notes, container, false);

        noteService=new NoteService(NotesFragment.this.getActivity());
        listView = (ListView) root.findViewById(R.id.listView);
        show();

//        final TextView textView = root.findViewById(R.id.text_notes);
//        notesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
        inflater.inflate(R.menu.activity_option_menu,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this.getActivity(),item.getTitle(),Toast.LENGTH_LONG).show();
        switch (item.getItemId()){
            case R.id.menu_write:
                Intent intent = new Intent(NotesFragment.this.getActivity(), Write.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void show(){
        SharedPreferences preferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);//fragment中获取SharedPreferences需要多加一步getActivity()方法
        String username = preferences.getString("username", "");
        Cursor notes=noteService.SelectNotes(username);
        List<HashMap<String ,Object>> data = new ArrayList<HashMap<String ,Object>>();
        while (notes.moveToNext()){
            HashMap<String , Object> item = new HashMap<String , Object>();
            item.put("writeId",notes.getString(notes.getColumnIndex("id")));
            item.put("subject",notes.getString(notes.getColumnIndex("subject")));
            item.put("content",notes.getString(notes.getColumnIndex("content")));
            data.add(item);
            System.out.println(notes.getString(notes.getColumnIndex("subject")));
        }
        SimpleAdapter adapter = new SimpleAdapter(NotesFragment.this.getActivity(), data, R.layout.item,
                new String[]{"writeId","subject", "content"}, new int[]{R.id.writeId,R.id.subject,R.id.content});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int listView, long id) {
                TextView writeId = (TextView) view.findViewById(R.id.writeId);
//                Cursor cursor= listView.getItemAtPosition(listView);
//                String writeId = cursor.getString(cursor.getColumnIndex("writeId"));
//                Object o = listView.getItemAtPosition(position);
                System.out.println(writeId.getText());
                Intent intent=new Intent(NotesFragment.this.getActivity(), WriteShow.class);
                intent.putExtra("writeId",writeId.getText());
                startActivity(intent);
            }
        });
    }



}