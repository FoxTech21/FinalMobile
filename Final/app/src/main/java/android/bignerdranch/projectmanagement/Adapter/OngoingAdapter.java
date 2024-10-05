package android.bignerdranch.projectmanagement.Adapter;

import android.bignerdranch.projectmanagement.R;
import android.content.Context;
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
    private ArrayList<OngioingDomain> items;
    private Context context;


    public OngoingAdapter(ArrayList<OngioingDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OngoingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_ongoing, parent, false);
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull OngoingAdapter.ViewHolder holder, int position) {


        OngioingDomain item = items.get(position);
        holder.taskName.setText(item.getTaskName());
        holder.startDate.setText(item.getStartDate());
        holder.progressBarPercent.setText(String.valueOf(item.getProgressPercent()) + "%");

        int drawableResourceId = holder.itemView.getResources()
                        .getIdentifier(items.get(position).getPicture(),"drawable",context.getPackageCodePath());

        Glide.with(context)
                        .load(drawableResourceId)
                                .into(holder.pic);

        holder.progressBar.setProgress(item.getProgressPercent());

        if (position == 0) {
            holder.layout.setBackgroundResource(R.drawable.dark_backgourd);
            holder.taskName.setTextColor(context.getColor(R.color.white));
            holder.startDate.setTextColor(context.getColor(R.color.white));
            holder.progressTxT.setTextColor(context.getColor(R.color.white));
            holder.progressBarPercent.setTextColor(context.getColor(R.color.white));
            holder.pic.setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN);
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white)));
        } else {
            holder.layout.setBackgroundResource(R.drawable.light_background);
            holder.taskName.setTextColor(context.getColor(R.color.drak_blue));
            holder.startDate.setTextColor(context.getColor(R.color.drak_blue));
            holder.progressTxT.setTextColor(context.getColor(R.color.drak_blue));
            holder.progressBarPercent.setTextColor(context.getColor(R.color.drak_blue));
            holder.pic.setColorFilter(ContextCompat.getColor(context, R.color.drak_blue), PorterDuff.Mode.SRC_IN);
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.drak_blue)));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView taskName, startDate, progressBarPercent, progressTxT;
        ProgressBar progressBar;
        ImageView pic;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            layout = itemView.findViewById(R.id.layout);
            progressTxT = itemView.findViewById(R.id.progresstxt);
            taskName = itemView.findViewById(R.id.titletxt);
            startDate = itemView.findViewById(R.id.datetxt);
            progressBarPercent = itemView.findViewById(R.id.percenttxt);
            progressBar = itemView.findViewById(R.id.progressBar);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
