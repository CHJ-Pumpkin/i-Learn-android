package com.a209350309.i_learn;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.a209350309.i_learn.Entity.Message;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.myViewHodler>{
    private Context context;
    private ArrayList<Message> RecyclerviewAdapterArrayList;

    //创建构造函数
    public RecyclerviewAdapter(Context context, ArrayList<Message> RecyclerviewAdapterArrayList) {
        //将传递过来的数据，赋值给本地变量
        this.context = context;//上下文
        this.RecyclerviewAdapterArrayList = RecyclerviewAdapterArrayList;//实体类数据ArrayList
    }

    /**
     * 创建viewhodler，相当于listview中getview中的创建view和viewhodler
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public myViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建自定义布局
        View itemView = View.inflate(context, R.layout.item5, null);
        return new myViewHodler(itemView);
    }

    /**
     * 绑定数据，数据与view绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(myViewHodler holder, int position) {
        //根据点击位置绑定数据
        Message data = RecyclerviewAdapterArrayList.get(position);
//        holder.mItemGoodsImg;
        holder.message_content_item5.setText(data.getMessage_content());//获取实体类中的name字段并设置
        holder.message_time_item5.setText(data.getMessage_time());//获取实体类中的price字段并设置

    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return RecyclerviewAdapterArrayList.size();
    }

    //自定义viewhodler
    class myViewHodler extends RecyclerView.ViewHolder {
        private TextView message_content_item5;
        private TextView message_time_item5;

        public myViewHodler(View itemView) {
            super(itemView);
            message_content_item5 = (TextView) itemView.findViewById(R.id.message_content_item5);
            message_time_item5 = (TextView) itemView.findViewById(R.id.message_time_item5);
            //点击事件放在adapter中使用，也可以写个接口在activity中调用
            //方法一：在adapter中设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //可以选择直接在本位置直接写业务处理
//                    Toast.makeText(context,"点击了",Toast.LENGTH_SHORT).show();
                    //此处回传点击监听事件
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(v, RecyclerviewAdapterArrayList.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    /**
     * 设置item的监听事件的接口
     */
    public interface OnItemClickListener {
        /**
         * 接口中的点击每一项的实现方法，参数自己定义
         *
         * @param view 点击的item的视图
         * @param data 点击的item的数据
         */
        public void OnItemClick(View view, Message data);
    }

    //需要外部访问，所以需要设置set方法，方便调用
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
