package org.mv.hands;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    //어뎁터 객체 생성
    Intent intent;
    MenuAdapter menuAdapter;

    //메뉴를 보관하는 객체 생성
    ArrayList<MenuItem> menuItems;
    ListView listViewMenu;

    int allPrice = 0;

    TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        totalPrice = (TextView) findViewById(R.id.textView_totalPrice1);
        intent = getIntent();
        processIntent(intent);
        listViewMenu = (ListView) findViewById(R.id.listView_myShoppingCart);
        listViewMenu.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();


        Button backButton = (Button) findViewById(R.id.button_back);
        Button deleteAllButton = (Button) findViewById(R.id.button_delete);
        Button orderButton = (Button) findViewById(R.id.button_order);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
                builder.setTitle("주문 확인");
                builder.setMessage("정말 주문하시겠습니까?");


                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"메뉴 결제창으로 넘어갑니다.",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MyOrderActivity.class);
                        startActivityForResult(intent,101);
                    }
                });

                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        });

        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
                builder.setTitle("전체 삭제");
                builder.setMessage("정말 장바구니 내역을 모두 삭제 하겠습니까?");


                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"메뉴가 모두 삭제됩니다.",Toast.LENGTH_LONG).show();
                        menuAdapter = new MenuAdapter();
                        listViewMenu.setAdapter(menuAdapter);
                        menuAdapter.notifyDataSetChanged();
                        allPrice = 0;
                        totalPrice.setText(allPrice+"");
                    }
                });

                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.putParcelableArrayListExtra("returnList",menuAdapter.getMenuItems());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    //intent에 있는 데이터를 가져오는 메소드
    public void processIntent(Intent intent){
        if(intent != null){
            menuItems =  intent.getParcelableArrayListExtra("menuData");
            Log.d("size",menuItems.size()+"");
            menuAdapter = new MenuAdapter();
            for(int i=0;i<menuItems.size();i++){
                menuAdapter.addItem(menuItems.get(i));
                int price = menuAdapter.menuItems.get(i).getPrice();
                allPrice += price;
            }
            Log.d("in cart",menuAdapter.toString()+"");
            menuAdapter.notifyDataSetChanged();
            totalPrice.setText(allPrice+"");

        }
    }

    //==========================뒤로가기 버튼을 눌렀을 때 동작==========================
    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putParcelableArrayListExtra("returnList",menuAdapter.getMenuItems());
        setResult(RESULT_OK,intent);
        finish();
        super.onBackPressed();
    }
}
