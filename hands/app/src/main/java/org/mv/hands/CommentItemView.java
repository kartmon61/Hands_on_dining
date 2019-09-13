package org.mv.hands;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class CommentItemView extends LinearLayout {

    TextView tvCommentId;
    TextView tvCommentContent;
    TextView tvCommentTime;
    ImageView imgCommentId;
    RatingBar ratingBarComment;
    TextView tvCommentRecommendCount;

    //생성자1
    public CommentItemView(Context context) {
        super(context);

        init(context);
    }
    //생성자2
    public CommentItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    //init 함수 선언
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_comment_item,this,true);

        tvCommentId = (TextView) findViewById(R.id.TextView_CommentId);
        tvCommentTime = (TextView) findViewById(R.id.textView_commentTime);
        imgCommentId = (ImageView) findViewById(R.id.Img_CommentId);
        tvCommentContent = (TextView) findViewById(R.id.textView_commentContent);
        ratingBarComment = (RatingBar) findViewById(R.id.ratingBar_commentWrite);
        tvCommentRecommendCount = (TextView) findViewById(R.id.textView_commentRecommendCount);

    }


    //시간 계산하는 함수 선언부
    private static class TIME_MAXIMUM{
        public static final int SEC = 60;
        public static final int MIN = 60;
        public static final int HOUR = 24;
        public static final int DAY = 30;
        public static final int MONTH = 12;
    }
    public static String formatTimeString(long regTime) {
        long curTime = System.currentTimeMillis();
        long diffTime = (curTime - regTime) / 1000;
        String msg = null;
        if (diffTime < TIME_MAXIMUM.SEC) {
            msg = "방금 전";
        } else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
            msg = diffTime + "분 전";
        } else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
            msg = (diffTime) + "시간 전";
        } else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
            msg = (diffTime) + "일 전";
        } else if ((diffTime /= TIME_MAXIMUM.DAY) < TIME_MAXIMUM.MONTH) {
            msg = (diffTime) + "달 전";
        } else {
            msg = (diffTime) + "년 전";
        }
        return msg;
    }

    //id,img,time,content,rate setter 함수

    public void setCommentId(String id){
        tvCommentId.setText(id);
    }
    public void setImgCommentId(int resId){
        imgCommentId.setImageResource(resId);
    }
    public void setCommentTime(long regTime){
        tvCommentTime.setText(formatTimeString(regTime));
    }

    public void setCommentContent(String content){
        tvCommentContent.setText(content);
    }

    public void setCommentRecommendCount(int good){
        tvCommentRecommendCount.setText(""+good);
    }

    public void setRatingBarComment(float rate){
        ratingBarComment.setRating(rate);
    }

}
