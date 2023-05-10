package com.example.vanny;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vanny.databinding.ReportActivityBinding;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

          ReportActivityBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding =  ReportActivityBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            int[] imageId = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,
                    R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i};
            String[] name = {"Christopher","Craig","Sergio","Mubariz","Mike","Michael","Toa","Ivana","Alex"};
            String[] lastMessage = {"Heye","Supp","Let's Catchup","Dinner tonight?","Gotta go",
                    "i'm in meeting","Gotcha","Let's Go","any Weekend Plans?"};
            String[] lastmsgTime = {"8:45 pm","9:00 am","7:34 pm","6:32 am","5:76 am",
                    "5:00 am","7:34 pm","2:32 am","7:76 am"};
            String[] phoneNo = {"7656610000","9999043232","7834354323","9876543211","5434432343",
                    "9439043232","7534354323","6545543211","7654432343"};
            String[] country = {"United States","Russia","India","Israel","Germany","Thailand","Canada","France","Switzerland"};

            ArrayList<Report> userArrayList = new ArrayList<>();

            for(int i = 0;i< imageId.length;i++){

                Report report = new Report(name[i],lastMessage[i],lastmsgTime[i],phoneNo[i],imageId[i]);
                userArrayList.add(report);

            }


            ReportListAdapter listAdapter = new ReportListAdapter(ReportActivity.this,userArrayList);

            binding.listview.setAdapter(listAdapter);
            binding.listview.setClickable(true);
            binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent i = new Intent(ReportActivity.this, ReportItemActivity.class);
                    i.putExtra("name",name[position]);
                    i.putExtra("phone",phoneNo[position]);
                    i.putExtra("country",country[position]);
                    i.putExtra("imageid",imageId[position]);
                    startActivity(i);

                }
            });

        }



