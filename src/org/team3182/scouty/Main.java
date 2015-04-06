package org.team3182.scouty;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.allthelucky.common.view.ViewPagerIndicatorView;
import com.baoyz.swipemenulistview.*;
import com.parse.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Activity {

    private AppAdapter mAdapter;
    final static List<Team> teams = new ArrayList<Team>();

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
        map.put("View teams", LayoutInflater.from(this).inflate(R.layout.team_list_view, null));
        map.put("Submit new team", LayoutInflater.from(this).inflate(R.layout.entry_view, null));

        

        viewPagerIndicatorView.setupLayout(map);

        createListView(map);

        map.get("Submit new team").findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Clicked submit!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void createListView(Map<String, View> map){

        ParseQuery<Team> query = ParseQuery.getQuery(Team.class);
        query.findInBackground(new FindCallback<Team>() {
            @Override
            public void done(List<Team> results, ParseException e) {
                for (Team t : results) {
                    teams.add(t);
                }
                Toast.makeText(getApplicationContext(), "Done getting stuff! " + teams.get(0).getTeamName(), Toast.LENGTH_SHORT).show();
            }
        });

        final SwipeMenuListView listView = (SwipeMenuListView) map.get("View teams").findViewById(R.id.listView);
        mAdapter = new AppAdapter();
        listView.setAdapter(mAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Team t = (Team) listView.getItemAtPosition(position);
                listItemPressed(t);
            }
        });
    }

    public void listItemPressed(Team t){
        Intent i = new Intent(this, SelectedTeamActivity.class);
        i.putExtra("Team", t);
        startActivity(i);
    }

    class AppAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return teams.size();
        }

        @Override
        public Team getItem(int position) {
            return teams.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item, parent, false);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            Team team = getItem(position);
            holder.tv_name.setText(team.getTeamName());
            return convertView;
        }

        class ViewHolder {
            ImageView iv_icon;
            TextView tv_name;

            public ViewHolder(View view) {
                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                view.setTag(this);
            }
        }
    }

        

}
