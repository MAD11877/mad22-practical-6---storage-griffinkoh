package sg.edu.np.mad.MADPractical;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    public static String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        FirebaseDatabase currentUser = FirebaseDatabase.getInstance("https://storage-f1ea5-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference reference = currentUser.getReference("Users");

        reference.child("mad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username = dataSnapshot.child("username").getValue().toString();
                password = dataSnapshot.child("password").getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG,"User not found. Please try again!", error.toException());
            }
        });

        EditText name = findViewById(R.id.usernameInput);
        EditText pwd = findViewById(R.id.passwordInput);
        Button login = findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue = name.getText().toString();
                String passwordValue = pwd.getText().toString();
                if (nameValue.equals(username) && passwordValue.equals(password)){
                    Log.d(TAG,"test");
                    Intent i1 = new Intent(LoginPage.this, ListActivity.class);
                    startActivity(i1);
                }
                else{
                    Log.d(TAG,username);
                    Log.d(TAG,pwd.getText().toString());
                }
            }
        });
    }
}
