package br.com.materialdesign.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.materialdesign.R;
import br.com.materialdesign.ScrollActivity;

/**
 * Created by Gustavo on 12/09/2016.
 */
public class HomeFragment extends Fragment {

    private Typeface typeface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        rootView.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ScrollActivity.class));
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
