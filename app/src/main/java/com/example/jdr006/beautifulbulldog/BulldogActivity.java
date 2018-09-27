package com.example.jdr006.beautifulbulldog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import io.realm.Realm;

public class BulldogActivity extends AppCompatActivity {
    private TextView nameView, ageView, prompt, nameText, ageText;
    private EditText rating;
    private Button saveButton;
    private ImageView iv;
    private Realm realm;
    public Bulldog bulldog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulldog);

        realm = Realm.getDefaultInstance();
        String id = (String) getIntent().getStringExtra("bulldog");
        bulldog = realm.where(Bulldog.class).equalTo("id", id).findFirst();

        nameView = (TextView) findViewById(R.id.name_view2);
        ageView = (TextView) findViewById(R.id.age_view2);
        nameView.setText(bulldog.getName());
        ageView.setText(bulldog.getAge());

        nameText = (TextView) findViewById(R.id.name_text);
        ageText = (TextView) findViewById(R.id.age_text);

        prompt = (TextView) findViewById(R.id.prompt);
        rating = (EditText) findViewById(R.id.rating);

        iv = (ImageView) findViewById(R.id.imageView);

        /*spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.rankings, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        saveButton = (Button) findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Vote vote = new Vote();
                        realm.copyToRealmOrUpdate(vote);
                        finish();
                    }
                });
            }
        });
    }
}
