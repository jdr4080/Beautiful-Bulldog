package com.example.jdr006.beautifulbulldog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.service.notification.NotificationListenerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;

public class BulldogActivity extends AppCompatActivity {
    private TextView nameView, ageView, prompt, nameText, ageText;
    private EditText rating;
    private Button voteButton;
    private ImageView bulldogImage;
    private Realm realm, realm2;
    public Bulldog bulldog;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog);

        realm = Realm.getDefaultInstance();
        String id = (String) getIntent().getStringExtra("bulldog");
        bulldog = realm.where(Bulldog.class).equalTo("id", id).findFirst();

        realm2 = Realm.getDefaultInstance();
        String username = (String) getIntent().getStringExtra("username");
        user = realm2.where(User.class).equalTo("username", username).findFirst();

        nameView = (TextView) findViewById(R.id.name_view2);
        ageView = (TextView) findViewById(R.id.age_view2);
        nameView.setText(bulldog.getName());
        ageView.setText(bulldog.getAge());

        nameText = (TextView) findViewById(R.id.name_text);
        ageText = (TextView) findViewById(R.id.age_text);

        prompt = (TextView) findViewById(R.id.prompt);
        rating = (EditText) findViewById(R.id.rating);

        bulldogImage = (ImageView) findViewById(R.id.imageView);
        if(bulldog.getImage() != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(bulldog.getImage(), 0, bulldog.getImage().length);
            bulldogImage.setImageBitmap(bmp);
        }

        voteButton = (Button) findViewById(R.id.vote_button);

        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Vote vote = new Vote();
                        vote.setBulldog(bulldog);
                        vote.setOwner(user);
                        vote.setRating(Integer.parseInt(rating.getText().toString()));
                        realm.copyToRealmOrUpdate(vote);
                        finish();
                    }
                });
            }
        });
    }
}
