package com.example.adoni.tictac;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static android.R.attr.filter;

public class Diary extends AppCompatActivity {

    // To realize left_draw function
    private String[] LeftDrawerListItems = new String[]{"Diary", "Idea Collector", "Reminders", "Account"};
    private DrawerLayout DiaryDrawerLayout;
    private ListView DiaryDrawerLayoutListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);



        /***************To realize left_draw function************************/
        //Initialize the Drawer List
        DiaryDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        DiaryDrawerLayoutListView = (ListView) findViewById(R.id.DiaryLeftDrawer);
        mTitle = getTitle();
        //Should set the LeftDrawerListItems[0] as the account name dynamically
        //...
        //Set the adapter for the list view
        DiaryDrawerLayoutListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.diary_drawer_list_item, R.id.ListViewItem_TextView, LeftDrawerListItems));
        //Set the list's click listener
        DiaryDrawerLayoutListView.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this, DiaryDrawerLayout,
                R.string.drawer_open, R.string.drawer_close){
            //Called when a drawer has settled in a completely closed state.
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            //Called when a drawer has settled in a completely open state.
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Hi, Default !");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        // Set the drawer toggle as the DrawerListener
        DiaryDrawerLayout.setDrawerListener(mDrawerToggle);
        //Activate the icon: show drawer and back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        /***************To realize left_draw function************************/

    }

    /***************To realize left_draw function************************/
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = DiaryDrawerLayout.isDrawerOpen(DiaryDrawerLayoutListView);
        menu.findItem(R.id.DiarySearch).setVisible(!drawerOpen);
        menu.findItem(R.id.DiaryMessage).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /***************To realize left_draw function************************/

    /***************To realize Action Bar and Menu************************/
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Toast.makeText(Diary.this, "This is position" + Integer.toString(position),Toast.LENGTH_SHORT ).show();
            if (position == 0){
                DiaryDrawerLayout.closeDrawers();
            }
            else if (position == 1) {
                Intent intent = new Intent(Diary.this, Idea.class);
                startActivity(intent);
            }
            else if (position == 2) {
                Intent intent = new Intent(Diary.this, Reminders.class);
                startActivity(intent);
            }
            else if (position == 3) {
                Intent intent = new Intent(Diary.this, Account.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // TODO Auto-generated method stub
        switch(item.getItemId()){
            case R.id.DiarySearch:
                Toast.makeText(Diary.this, ""+"Search Diary", Toast.LENGTH_SHORT).show();
                break;
            case R.id.DiaryMessage:
                Toast.makeText(Diary.this, ""+"Messages", Toast.LENGTH_SHORT).show();
                break;
            case R.id.MenuAbout:
                Toast.makeText(Diary.this, ""+"About", Toast.LENGTH_SHORT).show();
                break;
            case R.id.MenuSettings:
                Toast.makeText(Diary.this, ""+"Settings", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Diary.this, Settings.class);
                startActivity(intent);
                break;
            case R.id.MenuQuit:
                Toast.makeText(Diary.this, ""+"quit", Toast.LENGTH_SHORT).show();
                //Method 1 to kill the process to quit
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
                //Method 2 to kill the process to quit
                //ActivityManager manager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
                //manager.killBackgroundProcesses(getPackageName());
                break;
            default:
                break;
        }
//         Toast.makeText(MainActivity.this, ""+item.getItemId(), Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }
    /***************To realize Action Bar and Menu************************/

}






















