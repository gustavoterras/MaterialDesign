package br.com.materialdesign.fragment;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.materialdesign.R;
import br.com.materialdesign.ScrollActivity;

/**
 * Created by Gustavo on 12/09/2016.
 */
public class HomeFragment extends Fragment {

    private Typeface typeface;
    private ImageButton fab;

    @Override
    public void onResume() {
        super.onResume();

        if(fab != null)
            fab.setVisibility(View.VISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        fab = (ImageButton) rootView.findViewById(R.id.fab);

        final AnimationSet animationSet = new AnimationSet(getActivity(), null);
        animationSet.addAnimation(new AlphaAnimation(1f, 0f));
        animationSet.addAnimation(new TranslateAnimation(0, 0, 0, -fab.getHeight()));
        animationSet.setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime));
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fab.setVisibility(View.GONE);
                startActivity(new Intent(getActivity(), ScrollActivity.class));
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.startAnimation(animationSet);
            }
        });

        TextView textView = (TextView) rootView.findViewById(R.id.text);
        textView.setTypeface(typeface);

        final ImageView img = (ImageView) rootView.findViewById(R.id.img);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.picture);

        RoundedBitmapDrawable drawableFactory = RoundedBitmapDrawableFactory.create(getActivity().getResources(), bitmap);
        drawableFactory.setCircular(true);

        img.setImageDrawable(drawableFactory);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "yerbaluisa.ttf");
    }
}
