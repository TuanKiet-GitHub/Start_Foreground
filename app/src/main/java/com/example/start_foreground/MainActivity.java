package com.example.start_foreground;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;

import com.example.start_foreground.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 eventStartService();
            }
        });

        binding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventStopService();
            }
        });
    }

    private void eventStopService() {
         Intent intent = new Intent(this , MyService.class);
         stopService(intent);

    }

    private void eventStartService() {
        // Khởi chạy unBound bằng start Service
        Intent intent = new Intent(this , MyService.class);
        intent.putExtra("key_data_intent", binding.edData.getText().toString().trim());
        startService(intent);
    }

}