package sg.edu.np.mad.MADPractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListActivityViewHolder extends RecyclerView.ViewHolder {
    TextView username;
    TextView desc;
    ImageView profile;
    public ListActivityViewHolder(View viewItem){
        super(viewItem);
        username = viewItem.findViewById(R.id.username);
        desc = viewItem.findViewById(R.id.desc);
        profile = viewItem.findViewById(R.id.profile);
    }

}
