package org.mv.hands;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FragmentMainMenu extends Fragment{

    MainActivity activity;
    ViewGroup rootView;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    Intent intent;
    MenuItem menuItem;

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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_menu,container,false);

        button1 = (Button) rootView.findViewById(R.id.button_menu1);
        button2 = (Button) rootView.findViewById(R.id.button_menu2);
        button3 = (Button) rootView.findViewById(R.id.button_menu3);
        button4 = (Button) rootView.findViewById(R.id.button_menu4);
        button5 = (Button) rootView.findViewById(R.id.button_menu5);
        button6 = (Button) rootView.findViewById(R.id.button_menu6);



        //첫번째 메뉴 선택 했을 때
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuItem = new MenuItem("찜햇닭 강정",18000,R.drawable.menu1);
                activity.menuAdapter.addItem(menuItem);
                showData();
            }
        });
        //두번째 메뉴 선택 했을 때
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuItem = new MenuItem("찜햇닭",18000,R.drawable.menu2);
                activity.menuAdapter.addItem(menuItem);
                showData();

            }
        });
        //세번째 메뉴 선택 했을 때

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuItem = new MenuItem("찜햇닭 윙",19000,R.drawable.menu3);
                activity.menuAdapter.addItem(menuItem);
                showData();
            }
        });

        //네번째 메뉴 선택 했을 때
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuItem = new MenuItem("치파오 치킨",20000,R.drawable.menu4);
                activity.menuAdapter.addItem(menuItem);
                showData();
            }
        });
        //다섯번째 메뉴 선택 했을 때
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuItem = new MenuItem("콜라",1000,R.drawable.menu5);
                activity.menuAdapter.addItem(menuItem);
                showData();

            }
        });
        //여섯번째 메뉴 선택 했을 때

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuItem = new MenuItem("사이다",1200,R.drawable.menu6);
                activity.menuAdapter.addItem(menuItem);
                showData();
            }
        });
        return rootView;
    }

    public void showData() {
        Toast.makeText(rootView.getContext(), "장바구니로 이동했습니다.", Toast.LENGTH_LONG).show();
        Log.d("전달한 데이터", menuItem.toString() );
    }

}



