package br.com.materialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ViewImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_image);

        setupWindowAnimations();

        ImageView imageView = (ImageView) findViewById(R.id.image);

        String imgUrl = getIntent().getStringExtra("image");

        Glide.with(this).load(imgUrl)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);
    }

    private void setupWindowAnimations() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_left);

        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
    }
}
