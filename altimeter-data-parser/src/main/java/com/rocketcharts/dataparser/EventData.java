package com.rocketcharts.dataparser;

public class EventData {
    // events
    private Integer launchDetectionAltitude;
    private Integer lowVelocity;
    private Integer apogee;
    private Integer noseOver;
    private Integer drogue;
    private Integer main;

    public EventData() {
        this.launchDetectionAltitude = null;
        this.lowVelocity = null;
        this.apogee = null;
        this.noseOver = null;
        this.drogue = null;
        this.main = null;
    }
 
    public EventData(int launchDetectionAltitude, int lowVelocity, int apogee, int noseOver, int drogue, int main) {
        this.launchDetectionAltitude = launchDetectionAltitude;
        this.lowVelocity = lowVelocity;
        this.apogee = apogee;
        this.noseOver = noseOver;
        this.drogue = drogue;
        this.main = main;
    }

    public void merge(EventData input) {
        if(input.launchDetectionAltitude != null && input.launchDetectionAltitude > 0)
            this.launchDetectionAltitude = input.launchDetectionAltitude;

        if(input.lowVelocity != null && input.lowVelocity > 0)
            this.lowVelocity = input.lowVelocity;

        if(input.apogee != null && input.apogee > 0)
            this.apogee = input.apogee;

        if(input.noseOver != null && input.noseOver > 0)
            this.noseOver = input.noseOver;

        if(input.drogue != null && input.drogue > 0)
            this.drogue = input.drogue;

        if(input.main != null && input.main > 0)
            this.main = input.main;
    }

    public Integer getLaunchDetectionAltitude() {
        return launchDetectionAltitude;
    }

    public void setLaunchDetectionAltitude(Integer launchDetectionAltitude) {
        this.launchDetectionAltitude = launchDetectionAltitude;
    }

    public Integer getLowVelocity() {
        return lowVelocity;
    }

    public void setLowVelocity(Integer lowVelocity) {
        this.lowVelocity = lowVelocity;
    }

    public Integer getApogee() {
        return apogee;
    }

    public void setApogee(Integer apogee) {
        this.apogee = apogee;
    }

    public Integer getNoseOver() {
        return noseOver;
    }

    public void setNoseOver(Integer noseOver) {
        this.noseOver = noseOver;
    }

    public Integer getDrogue() {
        return drogue;
    }

    public void setDrogue(Integer drogue) {
        this.drogue = drogue;
    }

    public Integer getMain() {
        return main;
    }

    public void setMain(Integer main) {
        this.main = main;
    }
}
