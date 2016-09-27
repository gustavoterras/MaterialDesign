package br.com.materialdesign;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.MenuItem;

import br.com.materialdesign.fragment.AnimationFragment;
import br.com.materialdesign.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupWindowAnimations();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final Fragment fragment = new HomeFragment();

        nextFragment(fragment, fragment.getClass().getSimpleName());
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = menuFragment(id);

        if(fragment == null) return false;

        nextFragment(fragment, fragment.getClass().getSimpleName());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupWindowAnimations() {
        getWindow().setExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.explode));
        getWindow().setReenterTransition(TransitionInflater.from(this).inflateTransition(R.transition.grid_reenter));
    }

    public void nextFragment(Fragment fragment, String tag){
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment currentFragment = fragmentManager.findFragmentByTag(tag);

        if(currentFragment != null && currentFragment.getTag().equals(tag)) return;

        Slide slideTransition = new Slide(Gravity.RIGHT);
        slideTransition.setDuration(getResources().getInteger(android.R.integer.config_longAnimTime));

        fragment.setExitTransition(slideTransition);
        fragment.setSharedElementEnterTransition(new ChangeTransform());

        fragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment,tag)
                .commit();
    }

    public Fragment menuFragment(int menuID){

        Fragment fragment = null;
        String title = null;

        switch (menuID){
            case R.id.nav_home:
                fragment = new HomeFragment();
                title = "Home";
                break;
            case R.id.nav_animation:
                fragment = new AnimationFragment();
                title = "Animation";
                break;
        }

        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);

        return fragment;
    }
}
