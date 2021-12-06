package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.notes.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.notes.EXTRA_TITLE";                    //these all for intent
    public static final String EXTRA_DESCRIPTION = "com.example.notes.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.notes.EXTRA_PRIORITY";

    private EditText editTextTitle, editTextDesc;
    private TextView tvPriority;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextDesc = findViewById(R.id.editTextDesc);
        editTextTitle = findViewById(R.id.editTextTitle);
        tvPriority = findViewById(R.id.tvPriority);
        numberPicker = findViewById(R.id.numberPickerPriority);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);// cross button to go back to mainActivity

        //here the note the should be edited comes to the activity
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit note");// here the title of activity get changed to Edit note
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));  //setting new title,description,priority to the note
            editTextDesc.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("Add Note");
        }

    }

    // this function saves the note when the save button was clicked
    //writing title,description,priority of the note
    private void saveNote() {
        String title = editTextTitle.getText().toString(); //getText method used to enter the text to the EditText in XWL. which is typeCased to String using toString().
        String description = editTextDesc.getText().toString();
        int priority = numberPicker.getValue();


        //condition to check whether the note and description are empty or not
        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;  //stop and doesn't execute below task
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);                 // here sending data to mainActivity
        data.putExtra(EXTRA_DESCRIPTION, description);        //title,description,priority
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }

    // add note menu is added to the activity to save the note
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note, menu);

        return true;// to show icon false if we don't want to show
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveNote:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}