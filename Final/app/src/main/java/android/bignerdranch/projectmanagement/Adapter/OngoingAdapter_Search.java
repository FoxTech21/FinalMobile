package android.bignerdranch.projectmanagement.Adapter;

import android.bignerdranch.projectmanagement.Domain.OngoingDomain_Search;
import android.bignerdranch.projectmanagement.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OngoingAdapter_Search extends RecyclerView.Adapter<OngoingAdapter_Search.viewholder> {
    private List<OngoingDomain_Search> items;
    private Context context;
    private OnItemLongClickListener longClickListener;


    public OngoingAdapter_Search(List<OngoingDomain_Search> items, OnItemLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public OngoingAdapter_Search.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_ongoing_search,
                parent,false);
        return new viewholder(inflator);
    }

    public void updateTaskList(List<OngoingDomain_Search> newTaskList) {
        this.items.clear(); // Xóa danh sách hiện tại
        this.items.addAll(newTaskList); // Thêm danh sách mới
        notifyDataSetChanged(); // Cập nhật RecyclerView
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        OngoingDomain_Search currentItem = items.get(position);
        holder.texttaskId.setText(String.valueOf(items.get(position).getTaskId()));
        holder.taskName.setText(items.get(position).getTaskName());
        holder.startDate.setText(items.get(position).getStartDate());
        holder.progressBarPercent.setText(items.get(position).getProgressPercent()+"%");
        holder.endDate.setText(items.get(position).getEndDate());
        holder.devName.setText(items.get(position).getDevName());
        holder.progressBar.setProgress(items.get(position).getProgressPercent());

        if(position == 0)
        {
            holder.layout.setBackgroundResource(R.drawable.dark_backgourd);
            holder.taskName.setTextColor(context.getColor(R.color.white));
            holder.startDate.setTextColor(context.getColor(R.color.white));
            holder.progressTxT.setTextColor(context.getColor(R.color.white));
            holder.progressBarPercent.setTextColor(context.getColor(R.color.white));
            holder.devName.setTextColor(context.getColor(R.color.white));
            holder.endDate.setTextColor(context.getColor(R.color.white));
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.white)));
        }
        else {
            holder.layout.setBackgroundResource(R.drawable.light_background);
            holder.taskName.setTextColor(context.getColor(R.color.drak_blue));
            holder.startDate.setTextColor(context.getColor(R.color.drak_blue));
            holder.progressTxT.setTextColor(context.getColor(R.color.drak_blue));
            holder.progressBarPercent.setTextColor(context.getColor(R.color.drak_blue));
            holder.devName.setTextColor(context.getColor(R.color.drak_blue));
            holder.endDate.setTextColor(context.getColor(R.color.drak_blue));
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.drak_blue)));
        }

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onItemLongClick(currentItem, position);
            }
            return true;
        });

    }

    // Phương thức này để lấy danh sách các item trong adapter
    public List<OngoingDomain_Search> getItems() {
        return items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView taskName,startDate,progressBarPercent,progressTxT,endDate,devName, texttaskId;
        ProgressBar progressBar;
        ConstraintLayout layout;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            texttaskId = itemView.findViewById((R.id.texttaskId));
            progressTxT = itemView.findViewById(R.id.progresstxt);
            taskName = itemView.findViewById(R.id.titletxt);
            startDate = itemView.findViewById(R.id.datetxt);
            progressBarPercent = itemView.findViewById(R.id.percenttxt);
            progressBar = itemView.findViewById(R.id.progressBar);
            endDate = itemView.findViewById(R.id.textEndDate);
            devName = itemView.findViewById(R.id.textDevName);
        }
    }
    public interface OnItemLongClickListener {
        void onItemLongClick(OngoingDomain_Search item, int position);
    }
}
