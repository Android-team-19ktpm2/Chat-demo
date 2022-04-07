package com.example.chat;

import android.provider.ContactsContract;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TimeCheckThread implements Runnable{
    Thread t;
    DatabaseReference reference;
    User enemy_user;
    FirebaseUser current_user;

    TextView status;
    public TimeCheckThread(FirebaseUser current_user, User enemy_user, TextView status)
    {
        t = new Thread(this);
        this.current_user=current_user;
        this.enemy_user=enemy_user;
        this.status=status;
        t.start();
    }

    @Override
    public void run() {
        while (true){
            reference = FirebaseDatabase.getInstance().getReference("Users/" + current_user.getUid() + "/online");
            String onlineAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            reference.setValue("online", "True////" + onlineAt);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
