package org.team3182.scouty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SelectedTeamActivity extends Activity{


    Team team = new Team();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_select_view);

        Intent i = getIntent();
        team = (Team) i.getSerializableExtra("Team");
        buildUI();
    }

    private void buildUI(){
        for (final Method method : team.getClass().getDeclaredMethods()) {

            final UIAnnotation handler = method.getAnnotation(UIAnnotation.class);
            if (handler != null) {
                try {
                    addTextField(handler.name(), method.invoke(team));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void addTextField(String name, Object data){
        // TODO Actually add text field to view
    }
}
