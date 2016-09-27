package br.com.materialdesign;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class DetailActivity extends AppCompatActivity {

    private Scene current;
    private Scene other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupWindowAnimations();

        ViewGroup container = (ViewGroup) findViewById(R.id.content);

        current = Scene.getSceneForLayout(container, R.layout.activity_detail1, this);
        other = Scene.getSceneForLayout(container, R.layout.activity_detail2, this);

        current.enter();
    }

    private void setupWindowAnimations() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);

        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);

        Transition sharedTransition = TransitionInflater.from(this).inflateTransition(R.transition.change_image_transform);

        getWindow().setSharedElementEnterTransition(sharedTransition);
    }

    public void changeScenes(View view) {
        Scene tmp = other;
        other = current;
        current = tmp;

        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.transition_info);
        TransitionManager.go(current, transition);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                supportFinishAfterTransition();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
