import org.joda.time.DateTime;
import org.joda.time.DateTimeField;

public class Meetup {
    private final int month;
    private final int year;

    public Meetup(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public DateTime day(int dayOfWeek, MeetupSchedule schedule) {
        switch(schedule) {
            case FIRST:
                return firstWeekdayBeforeOrOn(dayOfWeek, 7);
            case SECOND:
                return firstWeekdayBeforeOrOn(dayOfWeek, 14);
            case THIRD:
                return firstWeekdayBeforeOrOn(dayOfWeek, 21);
            case FOURTH:
                return firstWeekdayBeforeOrOn(dayOfWeek, 28);
            case TEENTH:
                return firstWeekdayBeforeOrOn(dayOfWeek, 19);
            case LAST:
            default:
                return firstWeekdayBeforeOrOn(dayOfWeek, lastDayOfMonth());
        }
    }

    private int lastDayOfMonth() {
        return new DateTime(year, month, 1, 0, 0).plusMonths(1).minusDays(1).getDayOfMonth();
    }

    private DateTime firstWeekdayBeforeOrOn(int weekday, int dayOfMonth) {
        final DateTime last = new DateTime(year, month, dayOfMonth, 0, 0);
        final DateTime guess = last.withDayOfWeek(weekday);
        if (guess.toDateTime().isAfter(last)) {
            return guess.minusDays(7);
        } else {
            return guess;
        }
    }
}
