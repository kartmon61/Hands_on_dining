package org.mv.hands;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentItem implements Parcelable {

    private String commentId;
    private String commentContent;
    private String commentTime;
    private float rate;
    private int resId;
    private int good;

    //생성자
    public CommentItem(String commentId, String commentContent, float rate, int resId, int good) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.rate = rate;
        this.resId = resId;
        this.good = good;
    }

    //생성자2
    public CommentItem(Parcel src){
        commentId = src.readString();
        commentContent = src.readString();
        commentTime = src.readString();
        rate = src.readFloat();
        resId = src.readInt();
        good = src.readInt();
    }


    //creator 필드가 필요함
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        //simpleData 객체 생성
        public CommentItem createFromParcel(Parcel src){
            return new CommentItem(src);
        }

        //객체 배열 생성
        public CommentItem[] newArray(int size){
            return new CommentItem[size];
        }
    };


    //오버라이드
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(commentId);
        parcel.writeString(commentContent);
        parcel.writeString(commentTime);
        parcel.writeFloat(rate);
        parcel.writeInt(resId);
        parcel.writeInt(good);
    }


    //getter setter 함수 선언
    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String comment_time) {
        this.commentTime = comment_time;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }



    //toString 함수 선언
    @Override
    public String toString() {
        return "CommentItem{" +
                "comment_id='" + commentId + '\'' +
                ", comment_content='" + commentContent + '\'' +
                ", comment_time='" + commentTime + '\'' +
                ", rate=" + rate +
                ", resId=" + resId +
                ", good=" + good +
                '}';
    }
}
