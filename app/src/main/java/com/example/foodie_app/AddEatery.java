package com.example.foodie_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddEatery extends AppCompatActivity {

    ImageView iv;
    Button upload;
    TextView name;
    TextView desc;
    TextView address;
    CheckBox veg;
    CheckBox nonveg;
    Uri path;
    StorageReference storageRef;
    DatabaseReference dbref;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_eatery);

        iv = findViewById(R.id.iv_upload);
        upload = findViewById(R.id.btn_upload);
        name = findViewById(R.id.tv_name);
        address = findViewById(R.id.tv_address);
        desc = findViewById(R.id.mlt_desc);
        veg = findViewById(R.id.cb_veg);
        nonveg = findViewById(R.id.cb_nonveg);
        ratingBar = findViewById(R.id.ratingBar);
        upload.setEnabled(false);
        storageRef = FirebaseStorage.getInstance().getReference("images");
        dbref = FirebaseDatabase.getInstance().getReference("Restaurant");



        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galery = new Intent();
                galery.setType("image/*");
                galery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(galery, 101);

            }
        });

        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        nonveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                upload.setEnabled(false);
                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Restaurant");
                String pk = dbref.push().getKey();
                StorageReference sref = storageRef.child(pk+"."+getExtension(path));
                sref.putFile(path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        sref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String downloadUrl = uri.toString();
                                Restaurant restaurant = new Restaurant(name.getText().toString(), downloadUrl, desc.getText().toString(),
                                        "user", address.getText().toString(), veg.isChecked(), nonveg.isChecked(), ratingBar.getRating(), 1);
                                dbref.child(pk).setValue(restaurant).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                        Context context = getApplicationContext();
                                        String text = "Upload successful";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Context context = getApplicationContext();
                                        String text = "Upload unsuccessful";
                                        int duration = Toast.LENGTH_SHORT;
                                        Toast toast = Toast.makeText(context, e.toString(), duration);
                                        toast.show();
                                    }
                                });

                                Intent intent = new Intent(AddEatery.this, RecyclerActivity.class);
                                intent.putExtra("Restaurant", "Restaurant");
                                startActivity(intent);
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Context context = getApplicationContext();
                        String text = "Upload unsuccessful";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK && data.getData() != null)
        {
            Picasso.get().load(data.getData()).fit().into(iv);
            path = data.getData();
            upload.setEnabled(true);
        }
    }

    private String getExtension(Uri pth)
    {
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(pth));
    }
}