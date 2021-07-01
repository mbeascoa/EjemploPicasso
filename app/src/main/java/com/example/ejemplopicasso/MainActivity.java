package com.example.ejemplopicasso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtView);
        OkHttpClient client = new OkHttpClient();
        String url = "http://www.textfiles.com/art/sunlogo.txt";

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                // Log.e("Error---->", e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String myresponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable()  {
                        @Override
                        public void run() {
                            txtResult.setText(myresponse);
                        }
                    });
                }
            }
        });

    }

    }

