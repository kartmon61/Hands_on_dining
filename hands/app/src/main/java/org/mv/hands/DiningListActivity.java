package org.mv.hands;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class DiningListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    Intent intent;
    MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_list);

        Button dining1Button = (Button) findViewById(R.id.button_dining1);
        Button dining2Button = (Button) findViewById(R.id.button_dining2);
        Button dining3Button = (Button) findViewById(R.id.button_dining3);

        dining1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "선택한 가게로 이동합니다.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent,101);
            }
        });

        dining2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dining3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//
//
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);



    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_reserve_dinner) {
            Toast.makeText(this,"내가 예약한 가게로 이동합니다.",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(),MyOrderActivity.class);
            startActivityForResult(intent,102);
        } else if (id == R.id.nav_shopping_cart) {
            Toast.makeText(this,"장바구니로 이동합니다.",Toast.LENGTH_LONG).show();
            intent = new Intent(getApplicationContext(),ShoppingCartActivity.class);
            intent.putParcelableArrayListExtra("menuData",menuAdapter.getMenuItems());
            startActivityForResult(intent,101);


        } else if (id == R.id.nav_coupon) {
            Toast.makeText(this,"쿠폰함로 이동합니다.",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_shopping_list) {
            Toast.makeText(this,"주문내역로 이동합니다.",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_review) {
            Toast.makeText(this,"리뷰관리로 이동합니다.",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_announcement) {
            Toast.makeText(this,"공지사항로 이동합니다.",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_event){
            Toast.makeText(this,"이벤로 이동합니다.",Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_config){
            Toast.makeText(this,"환경설정으로 이동합니다.",Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
