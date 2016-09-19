package br.com.materialdesign.fragment;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.materialdesign.R;

/**
 * Created by Gustavo on 19/09/2016.
 */

public class AnimationFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animation, container, false);

        Button button = (Button) rootView.findViewById(R.id.button1);
        ObjectAnimator.ofObject(button, "Change", new ArgbEvaluator(), Color.RED, Color.GREEN).setDuration(1000 * 60).start();
        button.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button1:
                Slide slide = new Slide();
                slide.setSlideEdge(Gravity.RIGHT);

                ViewGroup viewGroup = (ViewGroup) getActivity().findViewById(android.R.id.content);

                TransitionManager.beginDelayedTransition(viewGroup, slide);
                view.setVisibility(View.INVISIBLE);

                break;
        }
    }
}
