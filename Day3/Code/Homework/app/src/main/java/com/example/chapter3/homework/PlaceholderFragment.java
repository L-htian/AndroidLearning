package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {
    LottieAnimationView animationView;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        listView = view.findViewById(R.id.lvItems);

        String data[] = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item, data);
        listView.setAdapter(arrayAdapter);
        animationView = view.findViewById(R.id.animation_view);
        animationView.playAnimation();
        listView.setAlpha(0);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(animationView,"alpha",1.0f,0f);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(listView,"alpha",0f,1.0f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator1,animator2);
                animatorSet.start();
            }
        }, 5000);
    }

    public static class ListViewAdapter extends BaseAdapter {

        private Context mcontext;

        public ListViewAdapter(Context context) {
            mcontext = context;
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mcontext);
                view = inflater.inflate(R.layout.item,null);
            }else{
                view = convertView;
            }
            return view;
        }
    }
}
