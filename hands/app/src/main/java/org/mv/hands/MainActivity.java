package org.mv.hands;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentMainInfo fragmentMainInfo;
    FragmentMainMenu fragmentMainMenu;
    FragmentMainReview fragmentMainReview;
    MenuAdapter menuAdapter;
    ArrayList<MenuItem> menuItems;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentMainInfo = new FragmentMainInfo();
        fragmentMainMenu = new FragmentMainMenu();
        fragmentMainReview = new FragmentMainReview();
        menuAdapter = new MenuAdapter();

        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentMainMenu).commit();

        TabLayout tabs = findViewById(R.id.tabLayout);
        tabs.addTab(tabs.newTab().setText("메뉴"));
        tabs.addTab(tabs.newTab().setText("정보"));
        tabs.addTab(tabs.newTab().setText("리뷰"));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if(position == 0){
                    selected = fragmentMainMenu;
                }
                else if(position == 1){
                    selected = fragmentMainInfo;
                }
                else if(position == 2){
                    selected = fragmentMainReview;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container,selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "장바구니로 이동합니다.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                intent = new Intent(getApplicationContext(),ShoppingCartActivity.class);
                Log.d("result",menuAdapter.toString()+"");
                intent.putParcelableArrayListExtra("menuData",menuAdapter.getMenuItems());
                startActivityForResult(intent,101);

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d("request",requestCode+"");

        //장바구니에서 돌아왔을 때 동작
        if(requestCode == 101 && intent!=null){
            Log.d("call result","result");
            menuItems = (ArrayList) intent.getParcelableArrayListExtra("returnList");
            Log.d("get return menu size",menuItems.size()+"");
            menuAdapter = new MenuAdapter();
            if(!menuItems.isEmpty()){
                for(int i=0;i<menuItems.size();i++){
                    menuAdapter.addItem(menuAdapter.menuItems.get(i));
                }
            }
            menuAdapter.notifyDataSetChanged();
        }

    }


}
