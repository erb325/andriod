package erb325.pandacorn.edu.countdown;

import java.util.GregorianCalendar;

/**
 * A date in the Gregorian calendar. WOULD NOT NEED THIS CLASS IF WE COULD USE THE NEW DATE/TIME API
 * IN JAVA 8.
 *
 * @author Drue Coles
 */
public class Date {

    private int month;
    private int day;
    private int year;

    /**
     * Initializes this date to the current date.
     */
    public Date() {
        GregorianCalendar c = new GregorianCalendar();
        this.month = c.get(GregorianCalendar.MONTH) + 1;
        this.day = c.get(GregorianCalendar.DAY_OF_MONTH);
        this.year = c.get(GregorianCalendar.YEAR);
    }

    /**
     * Initializes this date with a specified month, day and year.
     *
     * @param month
     * @param day
     * @param year
     * @throws IllegalArgumentException if month or day are invalid
     */
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
        if (month < 1 || month > 12 || !isValidDay()) {
            throw new IllegalArgumentException("Invalid date arguments");
        }
    }

    /**
     * @param future
     * @return true if this date comes before future date
     */
    public boolean before (Date future) {
        if (year < future.year) {
            return true;
        }
        if (year == future.year && month < future.month) {
            return true;
        }
        if (year == future.year && month == future.month && day < future.day) {
            return true;
        }
        return false;
    }

    /**
     * Decides if the current year is a leap year.
     *
     * @return true if leap year, false if not.
     */
    public boolean isLeapYear() {
        /*
         * A leap year is one that is divisible by 400, or divisible
         * by 4 and not by 100.
         */
        boolean divBy400 = year % 400 == 0;
        boolean divBy4 = year % 4 == 0;
        boolean divBy100 = year % 100 == 0;
        return divBy400 || (divBy4 && !divBy100);
    }


    /**
     * Advances the day by one.
     */
    public void tick() {
        day++;
        if (!isValidDay()) {
            day = 1;
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
        }
    }

    /**
     * Advances the day.
     *
     * @param n the number of days to advance
     */
    public void tick(int n) {
        for (int i = 0; i < n; i++) {
            tick();
        }
    }

    /**
     * Decides if the day is valid.
     *
     * @return true if the day is valid, false if not
     */
    private boolean isValidDay() {
        if (day < 1 || day > 31) {
            return false;
        }

        if (isLeapYear() && month == 2) {
            return day <= 29;
        }

        // In a non-leap year, February has 28 days.
        if (month == 2) {
            return day <= 28;
        }

        // Some months have 30 days, and others have 31.
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
        } else {
            return day <= 31;
        }
    }
}