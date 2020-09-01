package chapter.android.aweme.ss.com.homework;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Items;
import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.NumberViewHolder> {

    private List<Items> mItems;
    private Context mContext;
//    private MyClickListener mMyClickListener;

//    public void setMyClickListener(MyClickListener myClickListener) {
//        mMyClickListener = myClickListener;
//    }

    public MessageAdapter(Context context, List<Items> itemList) {
        mItems = itemList;
        mContext = context;
    }

    @NonNull
    @Override
    public MessageAdapter.NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.im_list_item, viewGroup, false);
        NumberViewHolder holder = new NumberViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageAdapter.NumberViewHolder numberViewHolder, @SuppressLint("RecyclerView") final int i) {
        Items ite = mItems.get(i);
        numberViewHolder.icon.setImageResource(ite.getAvatarId());
        if (ite.getRobotNoticeId() == 1) {
            numberViewHolder.isOfficial.setVisibility(View.VISIBLE);
        } else {
            numberViewHolder.isOfficial.setVisibility(View.INVISIBLE);
        }
        numberViewHolder.title.setText(ite.getTitle());
        numberViewHolder.description.setText(ite.getDescription());
        numberViewHolder.time.setText(ite.getTime());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder {
        CircleImageView icon;
        ImageView isOfficial;
        TextView title;
        TextView description;
        TextView time;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iv_avatar);
            isOfficial = itemView.findViewById(R.id.robot_notice);
            title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_description);
            time = itemView.findViewById(R.id.tv_time);
        }
    }

    public interface MyClickListener {
        void onItemClick(View view, int position);
    }
}
