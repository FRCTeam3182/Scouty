package org.team3182.scouty;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.allthelucky.common.view.ViewPagerIndicatorView;
import com.parse.Parse;

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

        ViewPagerIndicatorView viewPagerIndicatorView = (ViewPagerIndicatorView) findViewById(R.id.base_view);
        final Map<String, View> map = new HashMap<String, View>();
        map.put("Tab 1", LayoutInflater.from(this).inflate(R.layout.activity_sample_pager_0, null));
        map.put("Tab 2", LayoutInflater.from(this).inflate(R.layout.activity_sample_pager_1, null));
        viewPagerIndicatorView.setupLayout(map);

        
    }
}
