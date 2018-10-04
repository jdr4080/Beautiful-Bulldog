package com.example.jdr006.beautifulbulldog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;

import io.realm.Realm;

public class NewBulldogActivity extends AppCompatActivity {

    private EditText newName, newAge;
    private Button saveButton;
    private ImageButton imageButton;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bulldog);

        newName = (EditText) findViewById(R.id.new_name);
        newAge = (EditText) findViewById(R.id.new_age);
        saveButton = (Button) findViewById(R.id.save_button);
        imageButton = (ImageButton) findViewById(R.id.image_button);

        realm = Realm.getDefaultInstance();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, 1);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newName.getText().toString().matches("")
                        && !newAge.getText().toString().matches("")
                        && imageButton.getDrawable() != null) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Bulldog bulldog = new Bulldog();
                            bulldog.setName(newName.getText().toString());
                            bulldog.setAge(newAge.getText().toString());
                            bulldog.setId(realm.where(Bulldog.class).findAllSorted("id").last().getId() + 1);
                            BitmapDrawable image = (BitmapDrawable) imageButton.getDrawable();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            image.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] imageInByte = baos.toByteArray();
                            bulldog.setImage(imageInByte);
                            realm.copyToRealm(bulldog);
                            finish();
                        }
                    });
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageButton.setImageBitmap(imageBitmap);
        }
    }

}
