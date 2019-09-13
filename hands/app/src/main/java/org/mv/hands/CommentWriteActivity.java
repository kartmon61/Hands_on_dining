package org.mv.hands;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class CommentWriteActivity extends AppCompatActivity {

    RatingBar rating;
    EditText editTextWrite;
    Button saveButton;
    Button cancelButton;

    CommentItem commentItem;
    Intent intent;

    //==========================onCreate 부분==========================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_write);

        rating = (RatingBar) findViewById(R.id.ratingBar_commentWrite);
        editTextWrite = (EditText) findViewById(R.id.editText_write);
        saveButton = (Button) findViewById(R.id.button_save);
        cancelButton = (Button) findViewById(R.id.button_cancel);

        //인텐트에 있는 데이터 전달
        intent = getIntent();
        processIntent(intent);

        //저장버튼을 눌렀을 때
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        //취소 버튼을 눌렀을 때
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    //==========================onCreate 종료==========================

    //==========================인텐트에 있는 데이터를 가져오는 메소드==========================
    public void processIntent(Intent intent){
        if(intent != null){
            commentItem = (CommentItem) intent.getParcelableExtra("itemData");
        }
    }

    //==========================저장버튼을 눌렀을 때 동작하는 메소드==========================
    public void saveData(){

        commentItem.setRate(rating.getRating());
        commentItem.setCommentContent(editTextWrite.getText().toString());

        intent = new Intent();
        intent.putExtra("returnData",commentItem);
        setResult(RESULT_OK,intent);
        finish();
    }
}
