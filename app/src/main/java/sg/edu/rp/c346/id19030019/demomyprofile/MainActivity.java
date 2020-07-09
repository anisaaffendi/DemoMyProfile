package sg.edu.rp.c346.id19030019.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etGPA;
    RadioGroup radioGroup;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.etName);
        etGPA=findViewById(R.id.etGPA);
        radioGroup=findViewById(R.id.radioGroupGender);
        buttonSave=findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strName = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                int id = radioGroup.getCheckedRadioButtonId();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor preEdit = prefs.edit();
                preEdit.putString("name", strName);
                preEdit.putFloat("GPA", gpa);
                preEdit.putInt("GenderID", id);

                preEdit.commit();

                Toast.makeText(MainActivity.this,"Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onPause() {
        super.onPause();

        //get the user input from edit text and store it in a variable
        String strName = etName.getText().toString();
        float gpa=Float.parseFloat(etGPA.getText().toString());
        int id=radioGroup.getCheckedRadioButtonId();

        //obtain an instance of the shared preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //obtain an instance of the shared preference editor for update
        SharedPreferences.Editor preEdit = prefs.edit();

        //Add the key-value pair
        preEdit.putString("name",strName);
        preEdit.putFloat("GPA", gpa);
        preEdit.putInt("GenderID", id);

        //call commit to svae changes to shared preferences
        preEdit.commit();

    }

    protected void onResume(){
        super.onResume();

        //obtain an instance of the shared preference
        SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(this);

        //Retrieve from saved data from the shared preference object
        String strName=prefs.getString("name","No name!");
        float gpa=prefs.getFloat("GPA",0);
        int id=prefs.getInt("GenderID",R.id.radioButtonMale);

        //Update the UI element with value
        etName.setText(strName);
        etGPA.setText(gpa + "");
        radioGroup.check(id);

    }



}
