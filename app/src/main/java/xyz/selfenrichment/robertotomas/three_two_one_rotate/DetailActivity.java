package xyz.selfenrichment.robertotomas.three_two_one_rotate;
// Created by RobertoTomás on 0004, 4, 4, 2016.

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import xyz.selfenrichment.robertotomas.three_two_one_rotate.lib.LayoutUtil;

/**
 * This activity is meant for single-pane mode only. It has a handler to detect screenRotation. Each
 * rotation event will check if the layout qualifies for two-pane mode and if so, defer back to the
 * activity.
 */
public class DetailActivity extends AppCompatActivity {
    private int mGridViewPosition;
    private String mViewItemTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            Intent i = getIntent();
            Bundle arguments = new Bundle();
            arguments.putString(getString(R.string.key_view_item_title),
                    i.getStringExtra(getString(R.string.key_view_item_title)));
            arguments.putInt(getString(R.string.key_grid_view_position),
                    i.getIntExtra(getString(R.string.key_grid_view_position),0));

            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentcontainer_detail, fragment)
                    .commit();
        } else {
            mGridViewPosition = savedInstanceState.getInt(
                    getString(R.string.key_grid_view_position));
            mViewItemTitle = savedInstanceState.getString(
                    getString(R.string.key_view_item_title));

            if(LayoutUtil.twoPanes(this)){ // if the screen rotated while on single pane, we need to
                // translate to dual-pane presentation.
                startActivity(new Intent(this, MainActivity.class)
                                .putExtra(getString(R.string.key_grid_view_position), mGridViewPosition)
                                .putExtra(getString(R.string.key_view_item_title), mViewItemTitle)
                );
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        TextView tv = (TextView) findViewById(R.id.textView);
        outState.putInt(getString(R.string.key_grid_view_position), _getPositionFromTag(tv));
        outState.putString(getString(R.string.key_view_item_title), _getTitle(tv));
    }

    @NonNull
    private String _getTitle(TextView tv) {
        return (String) tv.getText();
    }

    @NonNull
    private Integer _getPositionFromTag(TextView tv) {
        return (Integer) tv.getTag(R.id.tag_grid_view_item_position);
    }
}