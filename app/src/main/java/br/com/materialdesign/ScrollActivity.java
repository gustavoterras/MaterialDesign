package br.com.materialdesign;

import android.animation.Animator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

public class ScrollActivity extends AppCompatActivity {

    static String baconTitle = "Bacon";
    static String baconText = "Bacon ipsum dolor amet pork belly meatball kevin spare ribs. Frankfurter swine corned beef meatloaf, strip steak.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initCollapsingToolbar();

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
                return new ViewHolder(getLayoutInflater().inflate(R.layout.list_item, parent, false));
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position) {
                viewHolder.text1.setText(baconTitle);
                viewHolder.text2.setText(baconText);
            }

            @Override
            public int getItemCount() {
                return 30;
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text1;
        TextView text2;
        int pink;
        int white;

        ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(android.R.id.text1);
            text2 = (TextView) itemView.findViewById(android.R.id.text2);

            if (pink == 0)
                pink = itemView.getContext().getResources().getColor(R.color.colorAccent);
            if (white == 0)
                white = itemView.getContext().getResources().getColor(R.color.background_material_light);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            boolean isVeggie = view.getBackground() != null && ((ColorDrawable) view.getBackground()).getColor() == pink;

            int finalRadius = (int) Math.hypot(view.getWidth() / 2, view.getHeight() / 2);
            Animator anim = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight() / 2, 0, finalRadius);

            text1.setTextColor(isVeggie ? Color.BLACK : white);
            text2.setTextColor(isVeggie ? Color.BLACK : white);
            view.setBackgroundColor(isVeggie ? white : pink);
            anim.start();
        }
    }
}
