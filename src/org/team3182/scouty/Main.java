package org.team3182.scouty;

import android.app.Activity;
import android.os.Bundle;
import com.parse.Parse;

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

        Parse.initialize(this, "ePfmPvijcOTPfbKwcz6Ap7uAPWICrmi6rR6pyJ6T", "proSRvcvzD7Hs1KXCdp2qz5Xhiae3563a5NUUNxe");

        
    }
}
