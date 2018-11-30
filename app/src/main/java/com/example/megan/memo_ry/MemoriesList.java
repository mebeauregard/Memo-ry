package com.example.megan.memo_ry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MemoriesList extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories_list);

        button = (Button) findViewById(R.id.buttonBack);
        button. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMain();
            }
        });
    }
    public void openActivityMain () {
        Intent intent = new Intent (this, MainActivity.class);
        startActivity (intent);
    }
}
