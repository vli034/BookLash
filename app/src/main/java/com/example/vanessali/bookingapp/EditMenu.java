package com.example.vanessali.bookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class EditMenu extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.done_btn, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.cancel_btn:
                finish();
                //cancelEdit();
                return true;
            case R.id.done_btn:
                doneEdit();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }



    private void doneEdit(){
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
        finish();

    }
}
