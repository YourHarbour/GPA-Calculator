package com.harbour.gpa_cal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    ArrayList<String> sub_code_list;
    ArrayList<String> score_list;
    ArrayList<String> credit_list;
    LinearLayout insert_place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        sub_code_list = bundle.getStringArrayList("sub_code_list");
        score_list = bundle.getStringArrayList("score_list");
        credit_list = bundle.getStringArrayList("credit_list");
        String final_result = bundle.getString("final_gpa");
        insert_place = findViewById(R.id.result_container);


        TextView textView = findViewById(R.id.show_result);
        String str = "GPA : " + final_result;
        textView.setText(str);

        addView();
    }

    private void addView() {
        for(int i = 0;i<sub_code_list.size();i++){
            final View new_view = getLayoutInflater().inflate(R.layout.new_result_layout,null,false);
            final TextView textView1 = new_view.findViewById(R.id.fill1);
            final TextView textView2 = new_view.findViewById(R.id.fill2);
            final TextView textView3 = new_view.findViewById(R.id.fill3);
            textView1.setText(sub_code_list.get(i));
            textView2.setText(score_list.get(i));
            textView3.setText(credit_list.get(i));
            insert_place.addView(new_view);
        }

    }
}