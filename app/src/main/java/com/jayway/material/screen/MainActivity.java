package com.jayway.material.screen;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jayway.material.R;
import com.jayway.material.screen.drawer.DrawerMenuAdapter;
import com.jayway.material.screen.drawer.NavigationFragment;


public class MainActivity extends ActionBarActivity implements NavigationFragment.DrawerMenuListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            NavigationFragment navigationFragment =
                    (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);

            navigationFragment.setUp(R.id.navigation_fragment, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        }

        Toolbar cardToolbar = (Toolbar) findViewById(R.id.card_toolbar);
        if (cardToolbar != null) {
            cardToolbar.inflateMenu(R.menu.menu_card);
            cardToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    //.....
                    return false;
                }
            });
            cardToolbar.setTitle("Some Snazzy Headline...");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Toast.makeText(this, "Menu item info pressed!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuItemClicked(int pos, DrawerMenuAdapter.DrawerMenuItem item) {
        System.out.println("MainActivity.onMenuItemClicked");
        System.out.println("pos = [" + pos + "], item = [" + item.getLabel() + "]");
    }
}
