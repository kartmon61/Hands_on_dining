package org.mv.hands;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {

    ArrayList<CommentItem> commentItems = new ArrayList<>();

    //
    //BaseAdapter 오버라이드
    //

    //getCount
    @Override
    public int getCount() {

        return commentItems.size();
    }

    //getItem
    @Override
    public Object getItem(int i) {
        return commentItems.get(i);
    }

    //addItem
    public void addItem(CommentItem commentItem){
        commentItems.add(commentItem);
    }

    //getItemId
    @Override
    public long getItemId(int i) {
        return i;
    }

    //getView
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        CommentItemView commentView = null;
        if(convertView == null){
            commentView = new CommentItemView(viewGroup.getContext());
        }
        else{
            commentView = (CommentItemView) convertView;
        }

        //코맨트 뷰에 id,img,time,rate,content,ratingBar,recommendCount 추가
        CommentItem commentItem = commentItems.get(i);
        commentView.setCommentId(commentItem.getCommentId());
        commentView.setImgCommentId(commentItem.getResId());
        commentView.setCommentTime(System.currentTimeMillis());
        commentView.setRatingBarComment(commentItem.getRate());
        commentView.setCommentContent(commentItem.getCommentContent());
        commentView.setCommentRecommendCount(commentItem.getGood());

        return commentView;
    }
}
