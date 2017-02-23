package com.bawei.zhoukao3_b;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * 作    者：云凯文
 * 时    间：2017/2/22
 * 描    述：
 * 修改时间：
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHodler> {

    private static final String TAG = "MyRecyclerViewAdapter";
    private Context context;
    private ArrayList<Data> datas;

    public MyRecyclerViewAdapter(Context context, ArrayList<Data> datas) {
        this.context = context;
        this.datas = datas;
    }

    //加载布局，创建ViewHolder
    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_recyclerview, null);
        return new ViewHodler(itemView);
    }

    //数据和View绑定
    @Override
    public void onBindViewHolder(ViewHodler holder, int position) {
        //根据位置得到对应的数据
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(new ImageLoaderConfiguration.Builder(context.getApplicationContext()).build());
        imageLoader.displayImage(datas.get(position).getImg(), holder.iv_icon);

        holder.tv_name.setText(datas.get(position).getUserName());
        holder.tv_age.setText(datas.get(position).getUserAge()+"岁");
        holder.tv_work.setText(datas.get(position).getOccupation());
        holder.tv_resume.setText(datas.get(position).getIntroduction());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private TextView tv_name;
        private TextView tv_age;
        private TextView tv_work;
        private TextView tv_resume;

        public ViewHodler(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_age = (TextView) itemView.findViewById(R.id.tv_age);
            tv_work = (TextView) itemView.findViewById(R.id.tv_work);
            tv_resume = (TextView) itemView.findViewById(R.id.tv_resume);
            Log.d(TAG, "////////////////// " + datas);
        }
    }
}
