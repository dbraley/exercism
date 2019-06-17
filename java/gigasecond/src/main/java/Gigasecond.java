import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gigasecond {
    private static final long ONE_BILLION = 1000000000;
    private final LocalDateTime date;

    public Gigasecond(LocalDate start) {
        this(start.atStartOfDay());
    }

    public Gigasecond(LocalDateTime start) {
        this.date = start.plusSeconds(ONE_BILLION);
    }

    public LocalDateTime getDate() {
        return date;
    }
}
