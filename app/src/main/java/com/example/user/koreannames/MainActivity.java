package com.example.user.koreannames;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private int currentApiVersion;
    private static String cho = "ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ";
    private static String jung = "ㅏㅐㅑㅒㅓㅔㅕㅖㅗㅘㅙㅚㅛㅜㅝㅞㅟㅠㅡㅢㅣ";
    private static String jong = " ㄱㄲㄳㄴㄵㄶㄷㄹㄺㄻㄼㄽㄾㄿㅀㅁㅂㅄㅅㅆㅇㅈㅊㅋㅌㅍㅎ";

    //FloatingActionButton fab = findViewById(R.id.fab);

    private static final char[] first_consonants =
            {
                    //0x3131,'ㄴ','ㄷ','ㄹ','ㅁ','ㅂ','ㅅ','ㅇ','ㅈ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'
                    'ㄱ', 'ㅋ', 'ㅅ', 'ㅈ', 'ㅊ'
            };
    private static final char[] vowels =
            {
                    'ㅏ','ㅑ','ㅓ','ㅕ','ㅗ','ㅛ','ㅜ','ㅠ','ㅡ','ㅣ','ㅐ','ㅔ','ㅖ','ㅝ','ㅟ','ㅢ'
            };
    private static final char[] second_consonants =
            {
                    ' ','ㄴ','ㄹ','ㅇ'
            };

    private static final char[] first_consonants2 =
            {
                    'ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅂ','ㅅ','ㅇ','ㅈ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT)
        {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                    {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility)
                        {
                            if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                            {
                                decorView.setSystemUiVisibility(flags);
                            }
                            //idito
                        }
                    });
        }



        ListView lv_names = (ListView)findViewById(R.id.NamesView);

        List<String> list = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lv_names.setAdapter(adapter);

        List<String> First_list = new ArrayList<>();
        for (int i = 0; i < first_consonants.length; i++)
        {
            int a = cho.indexOf(first_consonants[i]);
            for (int j = 0; j < vowels.length; j++)
            {
                int b = jung.indexOf(vowels[j]);
                for (int k = 0; k < second_consonants.length; k++)
                {
                    int c = jong.indexOf(second_consonants[k]);
                    char temp = (char)(0xAC00 + 28 * 21 * a + 28 * b + c);
                    System.out.println("a : " + a + ", b : " + b + ", c : " + c);
                    First_list.add(String.valueOf(temp));
                }
            }
        }

        List<String> Second_list = new ArrayList<>();
        for (int i = 0; i < first_consonants2.length; i++)
        {
            int a = cho.indexOf(first_consonants2[i]);
            for (int j = 0; j < vowels.length; j++)
            {
                int b = jung.indexOf(vowels[j]);
                for (int k = 0; k < second_consonants.length; k++)
                {
                    int c = jong.indexOf(second_consonants[k]);
                    char temp = (char)(0xAC00 + 28 * 21 * a + 28 * b + c);
                    System.out.println("a : " + a + ", b : " + b + ", c : " + c);
                    Second_list.add(String.valueOf(temp));
                }
            }
        }

        for (int i = 0; i < First_list.size(); i++)
        {
            for (int j = 0; j < Second_list.size(); j++)
            {
                String temp = "김".concat(First_list.get(i).concat(Second_list.get(j)));
                list.add(temp);
            }
        }
    }

    //추가된 소스, ToolBar에 menu.xml을 인플레이트함
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_item, menu);
        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "환경설정 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);

        }
    }
}
