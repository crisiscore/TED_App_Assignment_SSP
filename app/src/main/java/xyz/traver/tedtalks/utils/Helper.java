package xyz.traver.tedtalks.utils;

public class Helper {

    public static String getDurationInMin(int durationSec) {
        String min = String.valueOf(durationSec / 60);
        String sec = String.valueOf(durationSec % 60);
        if ((durationSec / 60) < 60)
            return min.concat(":").concat(sec);
        else {
            String hour = String.valueOf((durationSec / 60) / 60);
            min = String.valueOf((durationSec / 60) % 60);
            return hour.concat(":").concat(min).concat(":").concat(sec);
        }
    }
}
