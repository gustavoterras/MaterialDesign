package br.com.materialdesign.fragment;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import br.com.materialdesign.R;
import br.com.materialdesign.ViewImageActivity;
import br.com.materialdesign.util.SquareImageView;

/**
 * Created by Gustavo on 19/09/2016.
 */

public class AnimationFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animation, container, false);

        final String images[] = getActivity().getResources().getStringArray(R.array.images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new MarginDecoration(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new RecyclerView.Adapter<AnimationFragment.ViewHolder>() {
            @Override
            public AnimationFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
                return new AnimationFragment.ViewHolder(getActivity().getLayoutInflater().inflate(R.layout.grid_item, parent, false));
            }

            @Override
            public void onBindViewHolder(AnimationFragment.ViewHolder holder, int position) {
                holder.urlImage = images[position];

                Glide.with(getActivity()).load(holder.urlImage)
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .into(holder.image);

            }

            @Override
            public int getItemCount() {
                return images.length;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public void onAttachedToRecyclerView(RecyclerView recyclerView) {
                super.onAttachedToRecyclerView(recyclerView);
            }
        });

        return rootView;
    }

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        SquareImageView image;
        String urlImage;
        ViewHolder(View itemView) {
            super(itemView);
            this.image = (SquareImageView) itemView.findViewById(R.id.image);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            startActivity(new Intent(getActivity(), ViewImageActivity.class).putExtra("image", urlImage),
                    ActivityOptions.makeSceneTransitionAnimation(getActivity(), cardView, image.getTransitionName()).toBundle());
        }
    }

    private class MarginDecoration extends RecyclerView.ItemDecoration {
        private int margin;

        MarginDecoration(Context context) {
            margin = context.getResources().getDimensionPixelSize(R.dimen.item_margin);
        }

        @Override
        public void getItemOffsets(
                Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(margin, margin, margin, margin);
        }
    }
}
