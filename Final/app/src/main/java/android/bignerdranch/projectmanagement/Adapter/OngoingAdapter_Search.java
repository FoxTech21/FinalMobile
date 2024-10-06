package android.bignerdranch.projectmanagement.Adapter;

import android.bignerdranch.projectmanagement.Domain.OngoingDomain_Search;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class OngoingAdapter_Search extends RecyclerView.Adapter<OngoingAdapter_Search.viewholder> {
    private ArrayList<OngoingDomain_Search> items;
    private Context context;

    public OngoingAdapter_Search(ArrayList<OngoingDomain_Search> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OngoingAdapter_Search.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_ongoing_search,
                parent,false);
        return new viewholder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.date.setText(items.get(position).getDate());
        holder.progressBarPercent.setText(items.get(position).getProgressPercent()+"%");
        holder.endDate.setText(items.get(position).getEndDate());
        holder.Assignee.setText(items.get(position).getAssignee());
        int drawableResourceId = holder.itemView.getResources()
                .getIdentifier(items.get(position).getPicture(),"drawable",context.getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);

        holder.progressBar.setProgress(items.get(position).getProgressPercent());

        if(position == 0)
        {
            holder.layout.setBackgroundResource(R.drawable.dark_backgourd);
            holder.title.setTextColor(context.getColor(R.color.white));
            holder.date.setTextColor(context.getColor(R.color.white));
            holder.progressTxT.setTextColor(context.getColor(R.color.white));
            holder.progressBarPercent.setTextColor(context.getColor(R.color.white));
            holder.Assignee.setTextColor(context.getColor(R.color.white));
            holder.endDate.setTextColor(context.getColor(R.color.white));
            holder.pic.setColorFilter(ContextCompat.getColor(context,R.color.white), PorterDuff.Mode.SRC_IN);
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.white)));
        }
        else {
            holder.layout.setBackgroundResource(R.drawable.light_background);
            holder.title.setTextColor(context.getColor(R.color.drak_blue));
            holder.date.setTextColor(context.getColor(R.color.drak_blue));
            holder.progressTxT.setTextColor(context.getColor(R.color.drak_blue));
            holder.progressBarPercent.setTextColor(context.getColor(R.color.drak_blue));
            holder.Assignee.setTextColor(context.getColor(R.color.drak_blue));
            holder.endDate.setTextColor(context.getColor(R.color.drak_blue));
            holder.pic.setColorFilter(ContextCompat.getColor(context,R.color.drak_blue), PorterDuff.Mode.SRC_IN);
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.drak_blue)));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView title,date,progressBarPercent,progressTxT,endDate,Assignee;
        ProgressBar progressBar;
        ImageView pic;
        ConstraintLayout layout;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            progressTxT = itemView.findViewById(R.id.progresstxt);
            title = itemView.findViewById(R.id.titletxt);
            date = itemView.findViewById(R.id.datetxt);
            progressBarPercent = itemView.findViewById(R.id.percenttxt);
            progressBar = itemView.findViewById(R.id.progressBar);
            pic = itemView.findViewById(R.id.pic);
            endDate = itemView.findViewById(R.id.textEndDate);
            Assignee = itemView.findViewById(R.id.textAssignee);
        }
    }
}
