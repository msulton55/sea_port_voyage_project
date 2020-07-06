package msultont;

public class FitnessFunction {

    private static final double CAPACITY = 100;
    private static final int YEAR_IN_DAYS = 365;
    private double distance;
    private double velocity;
    private double time;
    private double freq;
    private double totalCapacity;

    public FitnessFunction(double distance, double velocity) {
        this.distance = distance;
        this.velocity = velocity;

        this.time = this.distance / this.velocity;
        this.freq = YEAR_IN_DAYS / this.time;
        this.totalCapacity = CAPACITY * this.freq;

    }

    public double getDistance() {
        return distance;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getTime() {
        return time;
    }

    public double getCapacity() {
        return CAPACITY;
    }

    public double getFreq() {
        return freq;
    }

    public double getTotalCapacity() {
        return totalCapacity;
    }

}
