package org.team3182.scouty;

import android.app.Activity;
import android.os.Bundle;

public class SelectedTeamActivity extends Activity{


    Team team = new Team();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_select_view);
    }
}
