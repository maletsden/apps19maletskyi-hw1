package ua.edu.ucu.tempseries;

public class TemperatureSeriesAnalysis {
    private double[] temperatures;
    private int temps_len;

    public TemperatureSeriesAnalysis() {
        temperatures = new double[8];
        temps_len = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        temperatures = new double[temperatureSeries.length * 2];
        temps_len = temperatureSeries.length;

        //copying one array to another
        System.arraycopy(temperatureSeries,0, temperatures,0, temperatureSeries.length);
    }

    public double average() {
        checkIsEmpty();

        double sum = 0;
        for (int i = 0; i < temps_len; i++) {
            sum += temperatures[i];
        }

        return sum / temperatures.length;
    }

    public double deviation() {
        checkIsEmpty();

        double aversge_temp = average(), deviation = 0;

        for (int i = 0; i < temps_len; i++) {
            deviation += Math.pow(temperatures[i] - aversge_temp, 2);
        }

        return Math.sqrt(deviation / temperatures.length);
    }

    public double min() {
        checkIsEmpty();

        double minimum = temperatures[0];
        for (int i = 1; i < temps_len; i++) {
            minimum = Math.min(minimum, temperatures[i]);
        }

        return minimum;
    }

    public double max() {
        checkIsEmpty();

        double maximum = temperatures[0];
        for (int i = 1; i < temps_len; i++) {
            maximum = Math.max(maximum, temperatures[i]);
        }

        return maximum;
    }

    public double findTempClosestToZero() {
        checkIsEmpty();

        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        checkIsEmpty();

        double closest = temperatures[0];
        double difference = Math.abs(tempValue - closest);

        for (int i = 1; i < temps_len; i++) {
            if (Math.abs(tempValue - temperatures[i]) < difference) {
                closest =
                        (Math.abs(closest) == Math.abs(temperatures[i]))
                                ? Math.max(closest, temperatures[i])
                                : temperatures[i];
                difference = Math.abs(tempValue - closest);
            }
        }

        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        checkIsEmpty();

        double[] smallerTemperatures = new double[temperatures.length];
        int pos = 0;

        for (int i = 0; i < temps_len; i++) {
            if (temperatures[i] < tempValue) {
                smallerTemperatures[pos++] = temperatures[i];
            }
        }

        return smallerTemperatures;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        checkIsEmpty();

        double[] greaterTemperatures = new double[temperatures.length];
        int pos = 0;

        for (int i = 0; i < temps_len; i++) {
            if (temperatures[i] < tempValue) {
                greaterTemperatures[pos++] = temperatures[i];
            }
        }

        return greaterTemperatures;
    }

    public TempSummaryStatistics summaryStatistics() {
        checkIsEmpty();

        return new TempSummaryStatistics(
                average(),
                deviation(),
                min(),
                max()
        );
    }

    public int addTemps(double... temps) {
        if (temperatures.length == temps_len) {
            int newSize = Math.max(temperatures.length + temps.length, temperatures.length * 2);

            // allocate bigger array
            double[] newTemperatures = new double[newSize];

            //copying one array to another
            System.arraycopy(temperatures,0, newTemperatures,0, temperatures.length);

            // save reference to bigger temperatures array
            temperatures = newTemperatures;

            // clean old temperatures array
            System.gc();
        }

        //copying one array to another
        System.arraycopy(temps,0, temperatures,temps_len, temps.length);

        temps_len += temps.length;

        return temps_len;
    }

    private void checkIsEmpty() {
        if (temps_len == 0) {
            throw new IllegalArgumentException();
        }
    }
}
