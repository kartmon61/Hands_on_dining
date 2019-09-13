package org.mv.hands;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentMainReview extends Fragment {

    MainActivity activity;
    ViewGroup rootView;
    TextView textView_count;
    //어뎁터 객체 생성
    Intent intent;
    CommentAdapter commentAdapter;

    //댓글을 보관하는 객체 생성
    ArrayList<CommentItem> commentItems;
    ListView listView_comments;

    Button btnWriteComment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_review,container,false);
        TextView textView_count = (TextView) rootView.findViewById(R.id.textView_count);

        btnWriteComment = (Button) rootView.findViewById(R.id.btnWriteComment);

        //리뷰를 남겨주세요 버튼을 눌렀을 때,
        btnWriteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(rootView.getContext(), "작성하기 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                showCommentWriteActivity();
            }
        });
        //리스트 뷰 생성
        listView_comments = (ListView) rootView.findViewById(R.id.listView_allComments);
        //코멘트 어뎁터 객체 선언
        commentAdapter = new CommentAdapter();
        //데이터 저장할 arrayList 선언
        commentItems = new ArrayList<>();
        //예시 댓글 생성
        addItem();
        textView_count.setText(commentItems.size()+"");
        //리스트뷰에 어뎁터 적용
        listView_comments.setAdapter(commentAdapter);

        return rootView;
    }

    //==========================예시 댓글 생성 함수=======================
    public void addItem(){
        //댓글 추가1
        commentAdapter.addItem(
                new CommentItem
                        ("깐블리","오랜만에 먹었는데 존맛탱!! 반찬들도 맘에 들어용",3.1f,R.drawable.user1,0));
        commentItems.add(new CommentItem
                ("깐블리","오랜만에 먹었는데 존맛탱!! 반찬들도 맘에 들어용",3.1f,R.drawable.user1,0));
        //댓글 추가2
        commentAdapter.addItem(
                new CommentItem
                        ("낭낭이","국물이 진짜 진하고 맛있네용!!!! 특히 반찬 더 주실수 있냐고 요청사항에 적었더니 푸짐하게 주셨어요",4.2f,R.drawable.user1,2));
        commentItems.add( new CommentItem
                ("낭낭이","국물이 진짜 진하고 맛있네용!!!! 특히 반찬 더 주실수 있냐고 요청사항에 적었더니 푸짐하게 주셨어요",4.2f,R.drawable.user1,2));

    }

    //================작성하기 버튼을 눌렀을 때 동작하는 메소드==========
    public void showCommentWriteActivity(){
        CommentItem commentItem = new CommentItem("kartmon","",0f,R.drawable.user1,0);
        intent = new Intent(rootView.getContext(),CommentWriteActivity.class);

        intent.putExtra("itemData",commentItem);
        startActivityForResult(intent,101);
    }



    //==========================onActivityResult==========================
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        //작성하기에서 돌아왔을 때 동작
        if(requestCode == 101){
            if(intent != null){
                CommentItem commentItem = intent.getParcelableExtra("returnData");
                commentItems.add(commentItem);
                commentAdapter.addItem(commentItem);
                commentAdapter.notifyDataSetChanged();
                textView_count = (TextView) rootView.findViewById(R.id.textView_count);
                textView_count.setText(commentItems.size()+"");
            }
        }
    }
}
