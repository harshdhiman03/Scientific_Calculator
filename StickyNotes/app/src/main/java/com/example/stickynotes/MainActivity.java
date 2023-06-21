package com.example.stickynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText noteEDt;
    private Button increaseSizebtn,decreaseSizebtn,boldbtn,underlinebtn,italicbtn;
    private TextView Sizetv;
    float currentSize;
    StickyNote note= new StickyNote();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteEDt = findViewById(R.id.idEdt);
        increaseSizebtn = findViewById(R.id.idbtnincreasesize);
        decreaseSizebtn = findViewById(R.id.idbtnreducesize);
        italicbtn = findViewById(R.id.idbtnIlastic);
        boldbtn = findViewById(R.id.idbtnbold);
        underlinebtn = findViewById(R.id.idbtnUnderline);
        Sizetv = findViewById(R.id.idtvsize);
        currentSize= noteEDt.getTextSize();
        Sizetv.setText(String.valueOf(currentSize));
        Sizetv.setText("",currentSize);
        increaseSizebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sizetv.setText(String.valueOf(currentSize));
                currentSize++;
                noteEDt.setTextSize(currentSize);

            }
        });
        decreaseSizebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sizetv.setText(String.valueOf(currentSize));
                currentSize--;
                noteEDt.setTextSize(currentSize);

            }
        });
        boldbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                italicbtn.setTextColor(getResources().getColor(R.color.white));
                italicbtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if (noteEDt.getTypeface().isBold()){
                    noteEDt.setTypeface(Typeface.DEFAULT);
                    boldbtn.setTextColor(getResources().getColor(R.color.white));
                    boldbtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }
                else {
                    noteEDt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    boldbtn.setTextColor(getResources().getColor(R.color.purple_200));
                    boldbtn.setBackgroundColor(getResources().getColor(R.color.white));
                }

            }
        });
        underlinebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(noteEDt.getPaintFlags()==8){
                    underlinebtn.setTextColor(getResources().getColor(R.color.white));
                    underlinebtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                    noteEDt.setPaintFlags(noteEDt.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
                }else {
                    underlinebtn.setTextColor(getResources().getColor(R.color.purple_200));
                    underlinebtn.setBackgroundColor(getResources().getColor(R.color.white));
                    noteEDt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                }

            }
        });
        italicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boldbtn.setTextColor(getResources().getColor(R.color.white));
                boldbtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                if (noteEDt.getTypeface().isItalic()){
                    noteEDt.setTypeface(Typeface.DEFAULT);
                    italicbtn.setTextColor(getResources().getColor(R.color.white));
                    italicbtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }
                else {
                    noteEDt.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                    italicbtn.setTextColor(getResources().getColor(R.color.purple_200));
                    italicbtn.setBackgroundColor(getResources().getColor(R.color.white));
                }

            }
        });
    }
        public void saveButton(View View){
        note.setStick(noteEDt.getText().toString(),this);
        updatewidget();
        Toast.makeText(this, "Note has been createed", Toast.LENGTH_SHORT).show();

        }
        private void updatewidget(){
            AppWidgetManager appWidgetManager =AppWidgetManager.getInstance(this);
            RemoteViews remoteViews = new RemoteViews(this.getPackageName(),R.layout.widget_layout);
            ComponentName thisWidget = new ComponentName(this,AppWidget.class);
            remoteViews.setTextViewText(R.id.idtvwidget,noteEDt.getText().toString());
            appWidgetManager.updateAppWidget(thisWidget,remoteViews);

        }

}