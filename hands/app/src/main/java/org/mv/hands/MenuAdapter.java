package org.mv.hands;

import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {

    ArrayList<MenuItem> menuItems = new ArrayList<>();
    int loc;
    //
    //BaseAdapter 오버라이드
    //

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    //getCount
    @Override
    public int getCount() {

        return menuItems.size();
    }

    //getItem
    @Override
    public Object getItem(int i) {
        return menuItems.get(i);
    }

    //addItem
    public void addItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    //getItemId
    @Override
    public long getItemId(int i) {
        return i;
    }

    //getView
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        loc = i;
        MenuItemView menuItemView = null;
        if(convertView == null){
            menuItemView = new MenuItemView(viewGroup.getContext());
        }
        else{
            menuItemView = (MenuItemView) convertView;
        }
        Button deleteButton = menuItemView.findViewById(R.id.button_delete_one);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("버튼 눌림",""+loc);
            }
        });

        MenuItem menuItem = menuItems.get(i);
        menuItemView.setTvMenuName(menuItem.getMenuName());
        menuItemView.setTvMenuPrice(menuItem.getPrice());
        menuItemView.setImgMenu(menuItem.getResId());

        return menuItemView;
    }

    @Override
    public String toString() {
        return "MenuAdapter{" +
                "menuItems=" + menuItems +
                '}';
    }
}
