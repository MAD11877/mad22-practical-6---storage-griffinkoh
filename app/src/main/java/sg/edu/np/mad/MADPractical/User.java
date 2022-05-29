package sg.edu.np.mad.MADPractical;

import java.util.ArrayList;

public class User {
    String Name;
    String Description;
    int Id;
    boolean Followed;
    static ArrayList<User> userList = new ArrayList<>();

    public User(){

    }

    public User(String n, String des, int ID, boolean f){
        Name = n;
        Description = des;
        Id = ID;
        Followed = f;
    }
}
