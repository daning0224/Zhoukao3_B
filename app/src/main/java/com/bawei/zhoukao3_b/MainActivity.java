package com.bawei.zhoukao3_b;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button btn_location;
    private Button btn_delete;
    private XRecyclerView recyclerView;
    private OkHttpClient client = new OkHttpClient();
    private MyRecyclerViewAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ArrayList<Data> datas = (ArrayList<Data>) msg.obj;
            //适配器
            adapter = new MyRecyclerViewAdapter(MainActivity.this, datas);
            recyclerView.setAdapter(adapter);
            //获取外部存储目录
            File file = getExternalStorageDirectory();
            long length = file.length();
            String size = Formatter.formatFileSize(MainActivity.this, Long.valueOf(length));
            btn_delete.setText("清除缓存" + size);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        recyclerView = (XRecyclerView) findViewById(R.id.recyclerView);
        btn_location = (Button) findViewById(R.id.btn_location);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        //请求数据
        requestData();
        //监听事件
        btn_location.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        //LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, false));

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
//                requestData();
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
//                requestData();
                recyclerView.loadMoreComplete();
            }
        });
    }

    //请求数据
    private void requestData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String url = "http://www.yulin520.com/a2a/impressApi/news/mergeList?" +
                            "sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=10&gender=2&ts=1871746850&page=1";
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    String result = response.body().string();
                    //解析数据
                    ArrayList<Data> datas = parseData(result);
                    //发送给主线程
                    Message msg = Message.obtain();
                    msg.obj = datas;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //解析数据
    private ArrayList<Data> parseData(String result) {
        Gson gson = new Gson();
        Bean bean = gson.fromJson(result, Bean.class);
        ArrayList<Data> data = bean.getData();
        Log.d(TAG, "+++++++" + data);

        return data;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_location:
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_delete:
                ImageLoader.getInstance().clearDiskCache();
                ImageLoader.getInstance().clearMemoryCache();
                btn_delete.setText("清除缓存0KB");
                break;
        }
    }
}
