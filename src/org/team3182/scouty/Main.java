package org.team3182.scouty;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import com.allthelucky.common.view.ViewPagerIndicatorView;
import com.parse.Parse;
import com.parse.ParseObject;

import java.util.HashMap;
import java.util.Map;

public class Main extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, getString(R.string.appID), getString(R.string.clientKey));

        ParseObject.registerSubclass(Team.class);

        ViewPagerIndicatorView viewPagerIndicatorView = (ViewPagerIndicatorView) findViewById(R.id.base_view);
        final Map<String, View> map = new HashMap<String, View>();
        map.put("Enter new info", LayoutInflater.from(this).inflate(R.layout.entry_view, null));
        map.put("Tab 2", LayoutInflater.from(this).inflate(R.layout.entry_view, null));
        viewPagerIndicatorView.setupLayout(map);

        map.get("Enter new info").findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked submit!", Toast.LENGTH_SHORT).show();
            }
        });



        
    }
}
