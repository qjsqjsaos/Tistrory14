package org.techtown.tistrory14;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String id = "aaaa1111"; //아이디

    Uri uri; //uri 이미지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button update = findViewById(R.id.button2); //값저장
        Button comeon = findViewById(R.id.button);  //불러오기

        // 값을 저장한다.
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upDate();
            }
        });

        // 값을 불러온다.
        comeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comeOn();
            }
        });
    }

    private void comeOn() { //서버에서 이미지 가져오기
        Response.Listener<String> responseListener = new Response.Listener<String>() { //여기서 여기서 Quest1에서 썼던 데이터를 다가져온다.

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        String image = jsonObject.getString("profileimage");
                        //json데이터로 받은 image를 이미지뷰에 넣거나,
                        //글라이드 라이브러리를 통해 사용하면 된다.
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        ComeOn comeOn = new ComeOn(id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(comeOn);
    }

    private void upDate() { //이미지 업데이트 하기
        Response.Listener<String> responseListener = new Response.Listener<String>() { //여기서 여기서 Quest1에서 썼던 데이터를 다가져온다.

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        Toast.makeText(MainActivity.this, "데이터베이스 이미지 바꾸기 성공", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        UpDate update = new UpDate(id, uri, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(update);
    }
}