package com.kevin.androidopenglstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_triangle_one)
    Button btnTriangleOne;
    @BindView(R.id.btn_triangle_two)
    Button btnTriangleTwo;
    @BindView(R.id.btn_triangle_three)
    Button btnTriangleThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnTriangleOne.setOnClickListener(this);
        btnTriangleTwo.setOnClickListener(this);
        btnTriangleThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_triangle_one:
                startActivity(TriangleOneActivity.class);
                break;
            case R.id.btn_triangle_two:
                startActivity(TriangleTwoActivity.class);
                break;
            case R.id.btn_triangle_three:
                startActivity(TriangleThreeActivity.class);
                break;
        }
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
