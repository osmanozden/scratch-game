package com.cyberSpeed.scratchGame;

import java.util.List;

public class WinCombination {
    private String when;
    private double rewardMultiplier;
    private int count;
    private String group;
    private List<List<String>> coveredAreas;


    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<List<String>> getCoveredAreas() {
        return coveredAreas;
    }

    public void setCoveredAreas(List<List<String>> coveredAreas) {
        this.coveredAreas = coveredAreas;
    }
}
