package com.example.vanessali.bookingapp;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditProfileActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 101;
    private static final int PERMISSION_CODE = 102;

    Uri image_uri;

    private TextView toolBarTitle;
    private EditText nameEdt;
    private EditText emailEdt;
    private EditText phoneEdt;
    private TextView changePic;
    private ImageView profilePic;
    String pictureFilePath;

    //Header
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
        getOutputMediaFile();
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
        finish();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        nameEdt = findViewById(R.id.name_edt);
        emailEdt = findViewById(R.id.email_edt);
        phoneEdt = findViewById(R.id.phone_edt);
        changePic = findViewById(R.id.change_picture);
        profilePic = findViewById(R.id.profile_picture);


        // Customizing ToolBar
        Toolbar toolbar = findViewById(R.id.include);
        setSupportActionBar(toolbar);
        toolBarTitle = findViewById(R.id.toolbar_title);
        toolBarTitle.setText("EDIT PROFILE");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Method
        getData();

        //Onclick listeners
        changePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //23

                    if (checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        //if permission is not enabled request it
                        String[] permission={Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        //show Popup to request permission
                        requestPermissions(permission, PERMISSION_CODE);
                    }
                    else{
                        openCamera();

                    }
                }
                else{
                    openCamera();

                }
            }
        });

    }


    /** We need to be able to save profile changes to app and database and  photo to gallery when
     * user clicks done **/
  /** DEPRECATED CAMERA METHOD  **/
    private void openCamera(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        //camera intent
        Intent takeImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takeImage.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(takeImage,REQUEST_IMAGE_CAPTURE);

    }

    //handling permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //this method is called when the use either presses allow or deny
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length> 0 &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }else{
                    Toast.makeText(getApplicationContext(),"permission denied ", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        //called when image is captured from camera
        if (resultCode == RESULT_OK){
            profilePic.setImageURI(image_uri);

        }


    }

    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }

    ////////////////////TODO FIRE BASE READ AND UPDATE DATA METHODS ///////////////////////////////
    private void getData(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String current = user.getUid();//getting unique user id

        db.collection("users")
                .whereEqualTo("uId",current)//looks for the corresponding value with the field
                // in the database
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {

                               /* FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String current = user.getUid();//getting unique user id*/

                                nameEdt.setText((CharSequence) document.get("firstName"));
                                emailEdt.setText((CharSequence) document.get("email"));
                                phoneEdt.setText((CharSequence) document.get("phone"));


                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"No such document",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}
