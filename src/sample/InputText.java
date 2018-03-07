package sample;

import javafx.scene.control.TextField;

public class InputText {
    private double angle;
    private double speed;

    public InputText(double angle, double speed) {
        this.angle = angle;
        this.speed = speed;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
