package sg.edu.np.mad.MADPractical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        SQLAdapter db = new SQLAdapter(this);
        User.userList = db.getUsers();
        for (int i = 0; i < 20; i++) {
            Random num = new Random();
            int name = num.nextInt();
            int des = num.nextInt();
            Boolean follow = (Math.random() <= 0.5) ? true : false;

            User newUser = new User("Name" + Integer.toString(name), "Description " + Integer.toString(des), i, follow);
            User.userList.add(newUser);
            db.addUser(User.userList.get(i));
        }

        RecyclerView rv = findViewById(R.id.recyclerView);
        ListActivityAdapter adapter = new ListActivityAdapter(User.userList);
        LinearLayoutManager layout = new LinearLayoutManager(this);

        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);
    }
}
//    public void displayAlert(View view){
//        AlertDialog.Builder profile = new AlertDialog.Builder(this);
//
//        profile.setTitle("Profile").setMessage("MADness").setCancelable(false).setPositiveButton
//        ("VIEW", new DialogInterface.OnClickListener(){
//            @Override
//            public void onClick(DialogInterface dialog, int id){
//                Random num = new Random();
//                int rand = num.nextInt(Integer.MAX_VALUE);
//                Intent newPage = new Intent(ListActivity.this, MainActivity.class);
//                newPage.putExtra("random",rand);
//                startActivity(newPage);
//            }
//        });
//        profile.setNegativeButton("CLOSE", new DialogInterface.OnClickListener(){
//            public void onClick(DialogInterface dialog, int id){
//
//            }
//        });
//        profile.show();
//
//    }
//}