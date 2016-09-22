package br.com.materialdesign.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import br.com.materialdesign.R;

/**
 * Created by Gustavo on 19/09/2016.
 */

public class AnimationFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animation1, container, false);

        ImageButton button = (ImageButton) rootView.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Slide slide = new Slide();
                slide.setSlideEdge(Gravity.TOP);

                TransitionManager.beginDelayedTransition(container, slide);
                view.setVisibility(View.INVISIBLE);*/

                Transition transition = TransitionInflater.from(getActivity()).inflateTransition(R.transition.transition_info);
                TransitionManager.go(Scene.getSceneForLayout(container, R.layout.fragment_animation2, getActivity()), transition);
            }
        });

        return rootView;
    }
}
