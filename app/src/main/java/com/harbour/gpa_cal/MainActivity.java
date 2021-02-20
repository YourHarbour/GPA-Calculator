package com.harbour.gpa_cal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    LinearLayout insert_point;
    FloatingActionButton add_button;
    FloatingActionButton calculate_button;
    Button clear_all_button;
    FloatingActionButton question_button;
    Spinner uni_select;
    int select_uni_num;
    String first_sub_code;
    String first_score;
    String first_credit;
    double first_scr;
    double first_cre;
    int total_num_of_subjects;
    double first_scr_fin;
    double gpa;
    double total_score;
    double total_credit;
    ArrayList<String> sub_code_list;
    ArrayList<String> score_list;
    ArrayList<String> credit_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        insert_point = findViewById(R.id.container_layout);
        add_button = findViewById(R.id.add_button);
        calculate_button = findViewById(R.id.calculate_button);
        clear_all_button = findViewById(R.id.button_clear_all);
        question_button = findViewById(R.id.question_mark);
        uni_select = findViewById(R.id.uni_select_spinner);
        select_uni_num = 0;
        sub_code_list = new ArrayList<>();
        score_list = new ArrayList<>();
        credit_list = new ArrayList<>();
        
        add_button.setOnClickListener(this);
        calculate_button.setOnClickListener(this);
        clear_all_button.setOnClickListener(this);
        question_button.setOnClickListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.uni_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uni_select.setAdapter(adapter);
        uni_select.setOnItemSelectedListener(this);

        
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.add_button:
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(8);
                addView();
                break;
            case R.id.button_clear_all:
                Vibrator v1 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v1.vibrate(8);
                insert_point.removeViews(1,insert_point.getChildCount()-1);
                EditText first = findViewById(R.id.Subject_input);
                EditText sec = findViewById(R.id.Score_input);
                EditText third = findViewById(R.id.Credit_input);
                first.getText().clear();
                sec.getText().clear();
                third.getText().clear();
                total_score = 0;
                total_credit = 0;
                sub_code_list.clear();
                score_list.clear();
                credit_list.clear();
                break;
            case R.id.question_mark:
                Intent intent = new Intent(this,QuestionActivity.class);
                startActivity(intent);
                break;
            case R.id.calculate_button:
                EditText editText1 = findViewById(R.id.Subject_input);
                EditText editText2 = findViewById(R.id.Score_input);
                EditText editText3 = findViewById(R.id.Credit_input);
                Vibrator v3 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v3.vibrate(8);
                total_num_of_subjects = insert_point.getChildCount();
                first_sub_code = editText1.getText().toString();
                first_score = editText2.getText().toString();
                first_credit = editText3.getText().toString();
                if(TextUtils.isEmpty(first_sub_code)||TextUtils.isEmpty(first_score)||TextUtils.isEmpty(first_credit)){
                    Toast.makeText(view.getContext(),"Please fill all blanks, if curious about subject code just type 1, 2, 3, 4 instead .",Toast.LENGTH_LONG).show();
                    total_score = 0;
                    total_credit = 0;
                    sub_code_list.clear();
                    score_list.clear();
                    credit_list.clear();
                }else{
                    sub_code_list.add(first_sub_code);
                    score_list.add(first_score);
                    credit_list.add(first_credit);
                    first_scr = Integer.parseInt(first_score);
                    first_cre = Integer.parseInt(first_credit);
                    if(select_uni_num == 1 ){
                        if(first_scr >= 75){
                            first_scr_fin = 4;
                        }else if(first_scr>=65){
                            first_scr_fin = 3;
                        }else if(first_scr>=50){
                            first_scr_fin = 2;
                        }else{
                            first_scr_fin = 0;
                        }
                    }else if(select_uni_num == 3 ){
                        if(first_scr >= 90){
                            first_scr_fin = 4;
                        }else if(first_scr>=80){
                            first_scr_fin = 3;
                        }else if(first_scr>=70){
                            first_scr_fin = 2;
                        }else if(first_scr>=60){
                            first_scr_fin = 1;
                        }
                    }else if(select_uni_num == 2){
                        if(first_scr >= 80){
                            first_scr_fin = 4;
                        }else if(first_scr>=75){
                            first_scr_fin = 3.7;
                        }else if(first_scr>=70){
                            first_scr_fin = 3.3;
                        }else if(first_scr>=60){
                            first_scr_fin = 3;
                        }else if(first_scr>=55){
                            first_scr_fin = 2.3;
                        }else if(first_scr>=50){
                            first_scr_fin = 2;
                        }
                    }
                    total_credit+=first_cre;
                    total_score+=first_cre*first_scr_fin;
                    if(insert_point.getChildCount() > 1){
                        for(int i = 1;i<total_num_of_subjects;i++){
                            View current_view = insert_point.getChildAt(i);
                            EditText editText4 = current_view.findViewById(R.id.Subject_input1);
                            EditText editText5 = current_view.findViewById(R.id.Score_input1);
                            EditText editText6 = current_view.findViewById(R.id.Credit_input1);
                            String sub_code = editText4.getText().toString();
                            String score = editText5.getText().toString();
                            String credit = editText6.getText().toString();
                            if(TextUtils.isEmpty(sub_code)||TextUtils.isEmpty(score)||TextUtils.isEmpty(credit)){
                                Toast.makeText(view.getContext(),"Please fill all blanks, if curious about subject code just type 1, 2, 3, 4 instead .",Toast.LENGTH_LONG).show();
//                                sub_code = "0000";
//                                score = "0";
//                                credit = "0";
//                                sub_code_list.add(sub_code);

                                break;
                            }else{
                                int score_cur = Integer.parseInt(score);
                                int credit_fin = Integer.parseInt(credit);
                                double score_fin = 0;
                                if(select_uni_num == 1 ){
                                    if(score_cur >= 75){
                                        score_fin = 4;
                                    }else if(score_cur>=65){
                                        score_fin = 3;
                                    }else if(score_cur>=50){
                                        score_fin = 2;
                                    }
                                }else if(select_uni_num == 3){
                                    if(score_cur >= 90){
                                        score_fin = 4;
                                    }else if(score_cur>=80){
                                        score_fin = 3;
                                    }else if(score_cur>=70){
                                        score_fin = 2;
                                    }else if(score_cur>=60){
                                        score_fin = 1;
                                    }
                                }else if(select_uni_num == 2){
                                    if(score_cur >= 80){
                                        score_fin = 4;
                                    }else if(score_cur>=75){
                                        score_fin = 3.7;
                                    }else if(score_cur>=70){
                                        score_fin = 3.3;
                                    }else if(score_cur>=60){
                                        score_fin = 3;
                                    }else if(score_cur>=55){
                                        score_fin = 2.3;
                                    }else if(score_cur>=50){
                                        score_fin = 2;
                                    }
                                }
                                total_credit+=credit_fin;
                                total_score+=credit_fin*score_fin;
                                sub_code_list.add(sub_code);
                                score_list.add(score);
                                credit_list.add(credit);
                            }
                        }
                        double d = total_credit;
                        double d1 = total_score;
                        gpa = d1/d;
                        String final_gpa = String.valueOf(gpa);
                        Intent intent1 = new Intent(this, ResultActivity.class);
                        intent1.putStringArrayListExtra("sub_code_list",sub_code_list);
                        intent1.putStringArrayListExtra("score_list",score_list);
                        intent1.putStringArrayListExtra("credit_list",credit_list);
                        intent1.putExtra("final_gpa",final_gpa);
                        startActivity(intent1);
                    }else{
                        double d = total_credit;
                        double d1 = total_score;
                        gpa = d1/d;
                        String final_gpa = String.valueOf(gpa);
                        Intent intent1 = new Intent(this, ResultActivity.class);
                        intent1.putStringArrayListExtra("sub_code_list",sub_code_list);
                        intent1.putStringArrayListExtra("score_list",score_list);
                        intent1.putStringArrayListExtra("credit_list",credit_list);
                        intent1.putExtra("final_gpa",final_gpa);
                        startActivity(intent1);
                    }
                    total_score = 0;
                    total_credit = 0;
                    sub_code_list.clear();
                    score_list.clear();
                    credit_list.clear();
                    break;

                }
                break;

        }
    }

    private void addView() {
        final View new_view = getLayoutInflater().inflate(R.layout.new_layout,null,false);
        final Button close_button = new_view.findViewById(R.id.button_close);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(8);
                removeView(new_view);
            }
        });
        insert_point.addView(new_view);
    }

    private void removeView(View view) {
        insert_point.removeView(view);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(8);
        String uni_name = adapterView.getItemAtPosition(i).toString();
        switch (uni_name) {
            case "Australia New South Wales":
                select_uni_num = 1;
                break;
            case "Australia University of Melbourne":
                select_uni_num = 2;
                break;
            case "China 百分制(标准4.0)":
                select_uni_num = 3;
                break;
        }
        String first_sec = MainActivity.this.getResources().getString(R.string.have_choose);
        String sec_sec = MainActivity.this.getResources().getString(R.string.cau_method);

        Toast.makeText(adapterView.getContext(),first_sec+" "+uni_name+" "+sec_sec,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


}