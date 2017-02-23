package com.bawei.zhoukao3_b;

import java.util.ArrayList;

/**
 * 作    者：云凯文
 * 时    间：2017/2/22
 * 描    述：
 * 修改时间：
 */
public class Bean {

    private ArrayList<Data> data;

    public Bean() {
    }

    public Bean(ArrayList<Data> data) {
        this.data = data;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "data=" + data +
                '}';
    }
}
