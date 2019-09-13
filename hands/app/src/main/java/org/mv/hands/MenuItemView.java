package org.mv.hands;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuItemView extends LinearLayout {

    TextView tvMenuName;
    TextView tvMenuPrice;
    ImageView imgMenu;


    //생성자1
    public MenuItemView(Context context) {
        super(context);

        init(context);
    }
    //생성자2
    public MenuItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    //init 함수 선언
    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_menu_item,this,true);

        tvMenuName = (TextView) findViewById(R.id.textView_menuName);
        tvMenuPrice = (TextView) findViewById(R.id.textView_menuPrice);
        imgMenu = (ImageView) findViewById(R.id.imageView_menu);

    }

    //setter


    public void setTvMenuName(String menuName) {
        tvMenuName.setText(menuName);
    }


    public void setTvMenuPrice(int menuPrice) {
        tvMenuPrice.setText(menuPrice+"");
    }

    public void setImgMenu(int resId) {
        imgMenu.setImageResource(resId);
    }
}
