public class SpaceAge {
    private static double EARTH_SECONDS_PER_YEAR = 31557600;
    private enum Planet {
        MERCURY(0.2408467),
        VENUS(0.61519726),
        EARTH(1.0),
        MARS(1.8808158),
        JUPITER(11.862615),
        SATURN(29.447498),
        URANUS(84.016846),
        NEPTUNE(164.79132);

        private final double secondsPerYear;
        Planet(double orbitalPeriod) {
            secondsPerYear = orbitalPeriod * EARTH_SECONDS_PER_YEAR;
        }
        public double getSecondsPerYear() {
            return secondsPerYear;
        }
    }
    private double seconds;

    public SpaceAge(double seconds) {

        this.seconds = seconds;
    }

    public double getSeconds() {
        return seconds;
    }

    public double onEarth() {
        return yearsOn(Planet.EARTH);
    }

    public double onMercury() {
        return yearsOn(Planet.MERCURY);
    }

    public double onVenus() {
        return yearsOn(Planet.VENUS);
    }

    public double onMars() {
        return yearsOn(Planet.MARS);
    }

    public double onJupiter() {
        return yearsOn(Planet.JUPITER);
    }

    public double onSaturn() {
        return yearsOn(Planet.SATURN);
    }

    public double onUranus() {
        return yearsOn(Planet.URANUS);
    }

    public double onNeptune() {
        return yearsOn(Planet.NEPTUNE);
    }

    private double yearsOn(Planet planet) {
        return seconds / planet.getSecondsPerYear();
    }
}
