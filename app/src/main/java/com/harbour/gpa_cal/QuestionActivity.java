package com.harbour.gpa_cal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button email_button;
    ScrollView scrollView;
    Spinner spinner;
    Spinner method_spinner;
    LinearLayout answer_container;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        email_button = findViewById(R.id.email_button);
        scrollView = findViewById(R.id.question_answer);
        spinner = findViewById(R.id.question_picker_spinner);
        answer_container = findViewById(R.id.answer_container_layout);
        method_spinner = findViewById(R.id.method_des_select);
        imageView = findViewById(R.id.method_image);

        email_button.setOnClickListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.questions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        method_spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.email_button:
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(6);
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","xiaofanfansteve@gmail.com",null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(8);
        String answer = adapterView.getItemAtPosition(i).toString();
        switch(answer){
            case "Special Thanks":
                answer_container.removeAllViews();
                final View special_thanks_view = getLayoutInflater().inflate(R.layout.specialthanks_layout,null,false);
                TextView link1 =(TextView) special_thanks_view.findViewById(R.id.link1);
                link1.setMovementMethod(LinkMovementMethod.getInstance());
                TextView link2 =(TextView) special_thanks_view.findViewById(R.id.link2);
                link2.setMovementMethod(LinkMovementMethod.getInstance());
                TextView link3 =(TextView) special_thanks_view.findViewById(R.id.link3);
                link3.setMovementMethod(LinkMovementMethod.getInstance());
                TextView link4 =(TextView) special_thanks_view.findViewById(R.id.link4);
                link4.setMovementMethod(LinkMovementMethod.getInstance());
                answer_container.addView(special_thanks_view);
                break;
            case "How to save result ?":
                answer_container.removeAllViews();
                final View result_dev_view = getLayoutInflater().inflate(R.layout.save_result_answer_layout,null,false);
                answer_container.addView(result_dev_view);
                break;
            case "Is the result accurate enough ?":
                answer_container.removeAllViews();
                final View accurate_answer_view = getLayoutInflater().inflate(R.layout.accurate_answer_layout,null,false);
                answer_container.addView(accurate_answer_view);
                break;
            case "Where are other calculate methods ?":
                answer_container.removeAllViews();
                final View dev_view = getLayoutInflater().inflate(R.layout.save_result_answer_layout,null,false);
                TextView answer_text = dev_view.findViewById(R.id.answer_text);
                String string = QuestionActivity.this.getResources().getString(R.string.more_place);
                answer_text.setText(string);
                answer_container.addView(dev_view);
                break;
            case "Australia New South Wales":
                imageView.setImageResource(R.drawable.nsw);
                break;
            case "Australia University of Melbourne":
                imageView.setImageResource(R.drawable.umel);
                break;
            case "China 百分制(标准4.0)":
                imageView.setImageResource(R.drawable.china_baifenzhi);
                break;
            case "How to use ?":
                answer_container.removeAllViews();
                ImageView insertimage = new ImageView(this);
                insertimage.setImageResource(R.drawable.user_guide);
                answer_container.addView(insertimage);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}