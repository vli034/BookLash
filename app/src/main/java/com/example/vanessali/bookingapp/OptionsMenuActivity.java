package com.example.vanessali.bookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class OptionsMenuActivity extends AppCompatActivity{

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // we pass an item and then we can check which items were clicked
        switch (item.getItemId()) {
            case android.R.id.home:
//                // todo: goto back activity from here
//                Intent intent = new Intent(this, ServiceActivity.class);
////                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
                finish(); // brings back to last activity in the stack
                return true;
            case R.id.item3:
                launchServiceAct();
                return  true;
            case R.id.item4:
                signOut();
                return  true;
            case R.id.home_btn:
                homePage();

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clearing the most recent previous activity
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); // clearing the stack
        // removing the activities that is on the stack
        startActivityForResult(intent,1);

    }

    private void launchServiceAct(){
        Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
        startActivity(intent);
        finish();

    }

    private void homePage(){
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
        finish();

    }



}
