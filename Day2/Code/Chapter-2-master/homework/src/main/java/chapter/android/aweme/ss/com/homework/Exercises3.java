package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Items;
import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.model.PullParser;

/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 * <p>
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */
public class Exercises3 extends AppCompatActivity {
    private List<Items> mItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        try {
            InputStream assetInput = getAssets().open("data.xml");
            List<Message> messages = PullParser.pull2xml(assetInput);
            for (Message message : messages) {
                switch (message.getIcon()) {
                    case "TYPE_USER":
                        if (message.isOfficial()) {
                            Items it = new Items(R.drawable.icon_girl, 1, message.getTitle(),
                                    message.getDescription(), message.getTime());
                            mItems.add(it);
                        } else {
                            Items it = new Items(R.drawable.icon_girl, 0, message.getTitle(),
                                    message.getDescription(), message.getTime());
                            mItems.add(it);
                        }
                        break;
                    case "TYPE_STRANGER":
                        if (message.isOfficial()) {
                            Items it = new Items(R.drawable.session_stranger, 1, message.getTitle(),
                                    message.getDescription(), message.getTime());
                            mItems.add(it);
                        } else {
                            Items it = new Items(R.drawable.session_stranger, 0, message.getTitle(),
                                    message.getDescription(), message.getTime());
                            mItems.add(it);
                        }
                        break;
                    case "TYPE_SYSTEM":
                        if (message.isOfficial()) {
                            Items it = new Items(R.drawable.session_system, 1, message.getTitle(),
                                    message.getDescription(), message.getTime());
                            mItems.add(it);
                        } else {
                            Items it = new Items(R.drawable.session_system, 0, message.getTitle(),
                                    message.getDescription(), message.getTime());
                            mItems.add(it);
                        }
                        break;
                    case "TYPE_GAME":
                        if (message.isOfficial()) {
                            Items it = new Items(R.drawable.icon_micro_game_comment, 1, message.getTitle(),
                                    message.getDescription(), message.getTime());
                            mItems.add(it);
                        } else {
                            Items it = new Items(R.drawable.icon_micro_game_comment, 0, message.getTitle(),
                                    message.getDescription(), message.getTime());
                            mItems.add(it);
                        }
                        break;
                    case "TYPE_ROBOT":
                        if (message.isOfficial()) {
                            Items it = new Items(R.drawable.session_robot, 1, message.getTitle(),
                                    message.getDescription(), message.getTime());
                            mItems.add(it);
                        } else {
                            Items it = new Items(R.drawable.session_robot, 0, message.getTitle(),
                                    message.getDescription(), message.getTime());
                            mItems.add(it);
                        }
                        break;
                }
            }
            MessageAdapter messageAdapter = new MessageAdapter(this, mItems);
            recyclerView.setAdapter(messageAdapter);
//            messageAdapter.setMyClickListener(new MessageAdapter.MyClickListener() {
//                @Override
//                public void onItemClick(View view, int position) {
//
//                }
//            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
//        recyclerView.setAdapter(new ArrayAdapter<Message>(this,,messages));
    }

}
