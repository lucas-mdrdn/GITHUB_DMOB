package android.example.dmob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    Context context;
    ArrayList alarm_id, alarm_hour, alarm_minute;

    CustomAdapter(Context context, ArrayList alarm_id, ArrayList alarm_hour, ArrayList alarm_minute){
        this.context = context;
        this.alarm_id = alarm_id;
        this.alarm_hour = alarm_hour;
        this.alarm_minute = alarm_minute;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.alarm_id_txt.setText(String.valueOf(alarm_id.get(position)));
        holder.alarm_hour_txt.setText(String.valueOf(alarm_hour.get(position)));
        holder.alarm_minute_txt.setText(String.valueOf(alarm_minute.get(position)));
    }

    @Override
    public int getItemCount() {
        return alarm_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView alarm_id_txt, alarm_hour_txt, alarm_minute_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            alarm_id_txt = itemView.findViewById(R.id.alarm_id_txt);
            alarm_hour_txt = itemView.findViewById(R.id.alarm_hour_txt);
            alarm_minute_txt = itemView.findViewById(R.id.alarm_minute_txt);
        }
    }
}
