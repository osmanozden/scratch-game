package com.cyberSpeed.scratchGame;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Symbol {
    private String type;
    private int impact;
    private boolean extra;

    @JsonProperty("reward_multiplier")
    private int rewardMultiplier;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImpact() {
        return Integer.parseInt(String.valueOf(impact));
    }

    public void setImpact(int impact) {
        this.impact = impact;
    }

    public boolean isExtra() {
        return extra;
    }

    public void setExtra(boolean extra) {
        this.extra = extra;
    }

    public int getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(int rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }
// Getters and setters
}
