package com.timer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfilePropertyActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextRemark;

    private Button buttonOk;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_property);

        Bundle bundle = this.getIntent().getExtras();
        Profile profile = bundle.getParcelable("profile");
        final int position = bundle.getInt("position");


        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextRemark = (EditText)findViewById(R.id.editTextRemark);

        editTextName.setText(profile.getName());
        editTextRemark.setText(profile.getRemark());


        buttonOk = (Button)findViewById(R.id.buttonOk);
        buttonCancel = (Button)findViewById(R.id.buttonCancel);
        buttonOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String remark = editTextRemark.getText().toString();
                /*SharedPreferences preferences = getSharedPreferences("Text", position);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", name);
                editor.putString("remark", remark);

                if(editor.commit()) {
                    setResult(Activity.RESULT_OK);
                }
                finish();*/

                Profile profile = new Profile();
                profile.setName(name);
                profile.setRemark(remark);

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable("profile", profile);
                bundle.putInt("position", position);
                intent.putExtras(bundle);
                //setResult(CONTEXT_RESTRICTED, intent);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }
}
