package erb325.pandacorn.edu.raceprophet;

import java.text.DecimalFormat;

/**
 * Utility class for predicting race time at a target distance given actual performance at another
 * distance. There are two popular models for making such predictions -- the Cameron model and the
 * Riegel model. This class provides methods for both predictions.
 *
 * @author Drue Coles
 */
public class RaceTimePredictor {

    // Used to format the time in hh:mm:ss format.
    private static DecimalFormat f = new DecimalFormat("00");

    /**
     * @param d1 distance of actual performance (race or workout)
     * @param d2 target distance
     * @param h hours of actual performance
     * @param m minutes of actual performance
     * @param s seconds of actual performance
     * @return predicted race time at target distance according to Cameron model
     */
    public static String getCameronPrediction(double d1, double d2, int h, int m, int s) {
        int secs = toSeconds(h, m, s);
        double a = 13.49681 - 0.048865 * d1 + 2.438936 / Math.pow(d1, 0.7905);
        double b = 13.49681 - 0.048865 * d2 + 2.438936 / Math.pow(d2, 0.7905);
        int predictedTimeInSeconds = (int) (secs / d1 * (a / b) * d2);
        return format(predictedTimeInSeconds);
    }

    /**
     * @param d1 distance of actual performance (race or workout)
     * @param d2 target distance
     * @param h hours of actual performance
     * @param m minutes of actual performance
     * @param s seconds of actual performance
     * @return predicted race time at target distance according to Riegel model
     */
    public static String getRiegelPrediction(double d1, double d2, int h, int m, int s) {
        int secs = toSeconds(h, m, s);
        int predictedTimeInSeconds = (int) (secs * Math.pow(d2 / d1, 1.06));
        return format(predictedTimeInSeconds);
    }

    /**
     * @param timeInSeconds
     * @return the time in hh:mm:ss format
     */
    private static String format(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        timeInSeconds -= hours * 3600;
        int minutes = timeInSeconds / 60;
        timeInSeconds -= minutes * 60;
        int seconds = timeInSeconds;

        return f.format(hours) + ":" + f.format(minutes) + ":" + f.format(seconds);
    }

    /**
     * @param hours
     * @param minutes
     * @param seconds
     * @return number of seconds in the given interval
     */
    private static int toSeconds(int hours, int minutes, int seconds) {
        return seconds + 60 * minutes + 3600 * hours;
    }
}