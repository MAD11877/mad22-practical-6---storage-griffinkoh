package sg.edu.np.mad.MADPractical;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivityAdapter
        extends RecyclerView.Adapter<ListActivityViewHolder>{

    ArrayList<User> data;
    public ListActivityAdapter(ArrayList<User> data){
        this.data = data;
    }

    @NonNull
    @Override
    public ListActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        item = (viewType == 0) ?
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout_2, null, false)) :
                (LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout_1, null, false));

        return new ListActivityViewHolder(item);
    }

    @Override
    public int getItemViewType(int position){
        String getName = data.get(position).Name;
        return (getName.charAt(getName.length() - 1) == '7') ? 0 : 1;
    }

    @Override
    public void onBindViewHolder(@NonNull ListActivityViewHolder holder, int position) {
        String username = data.get(position).Name;
        holder.username.setText(username);
        String desc = data.get(position).Description;
        holder.desc.setText(desc);
        Boolean followed = data.get(position).Followed;

        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder info = new AlertDialog.Builder(view.getContext());
                info.setTitle("Profile").setMessage(username).setCancelable(false)
                .setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent newPage = new Intent(view.getContext(), MainActivity.class);
                        newPage.putExtra("name", username).putExtra("desc", desc).putExtra("id", holder.getAdapterPosition())
                                .putExtra("followed", followed);
                        (view.getContext()).startActivity(newPage);
                    }
                });
                info.setNegativeButton("CLOSE", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                info.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
