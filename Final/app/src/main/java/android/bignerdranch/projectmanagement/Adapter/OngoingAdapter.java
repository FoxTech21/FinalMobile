package android.bignerdranch.projectmanagement.Adapter;

import static android.media.CamcorderProfile.get;

import android.bignerdranch.projectmanagement.Activity.TaskDetail;
import android.bignerdranch.projectmanagement.R;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.bignerdranch.projectmanagement.Domain.OngioingDomain;
import android.bignerdranch.projectmanagement.SQLiteOpenHelper.DatabaseHandler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.ViewHolder> {
    private static ArrayList<OngioingDomain> items;
    private Context context;


    public OngoingAdapter(ArrayList<OngioingDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OngoingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_ongoing,
                parent, false);
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull OngoingAdapter.ViewHolder holder, int position) {


        OngioingDomain item = items.get(position);
        holder.taskID.setText(String.valueOf(item.getTaskID()));
        holder.taskName.setText(item.getTaskName());
        holder.startDate.setText(item.getStartDate());
        holder.progressBarPercent.setText(String.valueOf(item.getProgressPercent()) + "%");
        holder.progressBar.setProgress(item.getProgressPercent());
        holder.endDate.setText(String.valueOf(item.getEndDate()));
        holder.devName.setText(String.valueOf(item.getDevName()));
        holder.estimateDay.setText(String.valueOf(item.getEstimateDay()));


        if (position == 0) {
            holder.layout.setBackgroundResource(R.drawable.dark_backgourd);
            holder.taskID.setTextColor(context.getColor(R.color.white));
            holder.taskName.setTextColor(context.getColor(R.color.white));
            holder.startDate.setTextColor(context.getColor(R.color.white));
            holder.progressTxT.setTextColor(context.getColor(R.color.white));
            holder.progressBarPercent.setTextColor(context.getColor(R.color.white));
            holder.estimateDay.setTextColor(context.getColor(R.color.white));
            holder.devName.setTextColor(context.getColor(R.color.white));
            holder.textestimate.setTextColor(context.getColor(R.color.white));
            holder.TaskID_text.setTextColor(context.getColor(R.color.white));
            holder.endDate.setTextColor(context.getColor(R.color.white));
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white)));
        } else {
            holder.layout.setBackgroundResource(R.drawable.light_background);
            holder.taskID.setTextColor(context.getColor(R.color.drak_blue));
            holder.taskName.setTextColor(context.getColor(R.color.drak_blue));
            holder.estimateDay.setTextColor(context.getColor(R.color.drak_blue));
            holder.devName.setTextColor(context.getColor(R.color.drak_blue));
            holder.endDate.setTextColor(context.getColor(R.color.drak_blue));
            holder.startDate.setTextColor(context.getColor(R.color.drak_blue));
            holder.progressTxT.setTextColor(context.getColor(R.color.drak_blue));
            holder.TaskID_text.setTextColor(context.getColor(R.color.drak_blue));
            holder.textestimate.setTextColor(context.getColor(R.color.drak_blue));
            holder.progressBarPercent.setTextColor(context.getColor(R.color.drak_blue));
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.drak_blue)));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public int getImageResourceByName(String imageName) {
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView taskName, startDate, progressBarPercent, progressTxT, taskID, estimateDay, devName, TaskID_text, endDate, textestimate;
        ProgressBar progressBar;
        ImageView pic;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            taskID = itemView.findViewById(R.id.taskID);
            TaskID_text = itemView.findViewById(R.id.TaskID_text);
            textestimate = itemView.findViewById(R.id.textestimate);
            progressTxT = itemView.findViewById(R.id.progresstxt);
            taskName = itemView.findViewById(R.id.titletxt);
            startDate = itemView.findViewById(R.id.datetxt);
            progressBarPercent = itemView.findViewById(R.id.percenttxt);
            progressBar = itemView.findViewById(R.id.progressBar);
            estimateDay = itemView.findViewById(R.id.estimatetxt);
            devName = itemView.findViewById(R.id.devNametxt);
            endDate = itemView.findViewById(R.id.endDatetxt);


            // Bắt sự kiện click vào item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition(); // Lấy vị trí của item
                    if (position != RecyclerView.NO_POSITION) {
                        OngioingDomain task = items.get(position);

                        // Tạo Intent để chuyển sang TaskDetailActivity
                        Intent intent = new Intent(view.getContext(), TaskDetail.class);

                        // Truyền dữ liệu sang activity mới
                        intent.putExtra("taskID", task.getTaskID());
                        intent.putExtra("taskName", task.getTaskName());
                        intent.putExtra("startDate", task.getStartDate());
                        intent.putExtra("endDate", task.getEndDate());
                        intent.putExtra("devName", task.getDevName());
                        intent.putExtra("progressPercent", task.getProgressPercent());
                        intent.putExtra("estimateDay", task.getEstimateDay());

                        // Chuyển sang activity TaskDetailActivity
                        view.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
