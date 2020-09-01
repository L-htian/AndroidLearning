package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_relativelayout, null);
        setContentView(R.layout.activity_relativelayout);
        TextView tv = findViewById(R.id.tv_center);
        tv.setText(getAllChildViewCount(inflate) + "个view");

    }

    public int getAllChildViewCount(View view) {
        //todo 补全你的代码
        int co = 0;
        if (view == null)
            return 0;
        if (view instanceof ViewGroup) {
            co++;
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View view1 = ((ViewGroup) view).getChildAt(i);
                if (view1 instanceof ViewGroup) {
                    co += getAllChildViewCount(view1);
                } else {
                    co++;
                }
            }
        }
        return co;
    }
}
