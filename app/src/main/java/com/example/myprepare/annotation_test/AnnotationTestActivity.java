package com.example.myprepare.annotation_test;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lib_annotation_test.Binding;
import com.example.lib_annotations.BindView;
import com.example.myprepare.R;

public class AnnotationTestActivity extends AppCompatActivity {

  @BindView(R.id.annotation_test_text)
  public TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_annotation_test);

//    textView = findViewById(R.id.annotation_test_text);
    Binding.bind(this);
    textView.setText("FUCK_YOU");
  }
}