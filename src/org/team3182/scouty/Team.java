package org.team3182.scouty;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Team")
public class Team extends ParseObject{

    private String teamNumber = "teamNumber";
    private String teamName = "teamName";

    private String numberOfTotes = "numberOfTotes";
    private String amountOfWheels = "amountOfWheels";

    public int getTeamNumber() {
        return getInt(teamNumber);
    }

    public void setTeamNumber(int teamNumber) {
        put(this.teamNumber, teamNumber);
    }

    public String getTeamName() {
        return getString(teamName);
    }

    public void setTeamName(String teamName) {
        put(this.teamName, teamNumber);
    }

    public int getNumberOfTotes() {
        return getInt(numberOfTotes);
    }

    public void setNumberOfTotes(int numberOfTotes) {
        put(this.numberOfTotes, numberOfTotes);
    }

    public int getAmountOfWheels() {
        return getInt(amountOfWheels);
    }

    public void setAmountOfWheels(int amountOfWheels) {
        put(this.amountOfWheels, amountOfWheels);
    }
}
