package org.team3182.scouty;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.io.Serializable;

@ParseClassName("Team")
public class Team extends ParseObject implements Serializable {

    private String teamNumber = "teamNumber";
    private String teamName = "teamName";

    private String numberOfTotes = "numberOfTotes";
    private String amountOfWheels = "amountOfWheels";

    @UIAnnotation(name = "Team Number")
    public int getTeamNumber() {
        return getInt(teamNumber);
    }

    public void setTeamNumber(int teamNumber) {
        put(this.teamNumber, teamNumber);
    }

    @UIAnnotation(name = "Team Name")
    public String getTeamName() {
        return getString(teamName);
    }

    public void setTeamName(String teamName) {
        put(this.teamName, teamNumber);
    }

    @UIAnnotation(name = "Amount of Totes")
    public int getNumberOfTotes() {
        return getInt(numberOfTotes);
    }

    public void setNumberOfTotes(int numberOfTotes) {
        put(this.numberOfTotes, numberOfTotes);
    }

    @UIAnnotation(name = "Amount of wheels")
    public int getAmountOfWheels() {
        return getInt(amountOfWheels);
    }

    public void setAmountOfWheels(int amountOfWheels) {
        put(this.amountOfWheels, amountOfWheels);
    }
}
