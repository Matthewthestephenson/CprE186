package com.printf.opia.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class OpiaMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opia_main);
        ImageButton startGame = (ImageButton)findViewById(R.id.startButton);
        ImageButton startTutorialMenu = (ImageButton)findViewById(R.id.tutorialButton);
        ImageButton startAboutMenu = (ImageButton)findViewById(R.id.aboutButton);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpiaMain.this, AndroidLauncher.class);
                OpiaMain.this.startActivity(intent);
            }
        });

        startTutorialMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpiaMain.this, TutorialMenu.class);
                OpiaMain.this.startActivity(intent);
            }
        });

        startAboutMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpiaMain.this, AboutMenu.class);
                OpiaMain.this.startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_opia_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
