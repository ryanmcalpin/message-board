package com.example.guest.messageboard.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


import com.example.guest.messageboard.Constants;
import com.example.guest.messageboard.R;
import com.example.guest.messageboard.models.Message;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewPostActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.categoriesSpinner) Spinner spinner;
    @Bind(R.id.submitButton) Button subButt;
    @Bind(R.id.nameView) EditText nameView;
    @Bind(R.id.messageView) EditText messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        ButterKnife.bind(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        subButt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = nameView.getText().toString();
        String message = messageView.getText().toString();
        String category = String.valueOf(spinner.getSelectedItem());

        Message messageObj = new Message(name, message, category);

        DatabaseReference messageRef = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_MESSAGES);
        messageRef.push().setValue(messageObj);
    }

}
