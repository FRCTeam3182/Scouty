package org.team3182.scouty;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Details");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setTitle("Delete");
                // set item width
                deleteItem.setWidth(dp2px(90));
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        SwipeMenuListView listView = (SwipeMenuListView) map.get("View teams").findViewById(R.id.listView);
        listView.setMenuCreator(creator);

        mAdapter = new AppAdapter();
        listView.setAdapter(mAdapter);


        // step 2. listener item click event
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Team team = teams.get(position);
                switch (index) {
                    case 0:
                        // open
                        Toast.makeText(getApplicationContext(), team.getTeamName(), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        // delete
                        Toast.makeText(getApplicationContext(), team.getTeamName() + " Deleted", Toast.LENGTH_SHORT).show();
                        //teams.remove(position);
                        //mAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

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

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
        

}
