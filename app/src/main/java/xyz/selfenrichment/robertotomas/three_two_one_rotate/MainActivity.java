package xyz.selfenrichment.robertotomas.three_two_one_rotate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_detail) != null) { // you are in two-pane mode

            // In two-pane mode, show the detail fragment in this activity by adding or replacing
            // the fragmentcontainer in the xml-layout using a fragment transaction.
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentcontainer_details_fragment,
                                new DetailFragment(), DetailFragment.class.getSimpleName())
                        .commit();
            }
        }

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_main);
        mainFragment.setupGridView();
    }
}
