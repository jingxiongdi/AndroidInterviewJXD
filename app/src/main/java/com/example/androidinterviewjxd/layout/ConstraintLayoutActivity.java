package com.example.androidinterviewjxd.layout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidinterviewjxd.R;

/**
 * layout_constraintLeft_toRightOf：可以理解为当前控件的左边，在指定控件的右边
 *
 */
public class ConstraintLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);
    }
}
