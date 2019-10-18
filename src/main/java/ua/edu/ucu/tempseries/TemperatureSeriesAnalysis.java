package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatures;
    private int tempsLen;

    private static final int DEFAULT_TEMPS_CAPACITY = 8;
    private static final double MINIMUM_TEMP_VALUE = -273.0;


    public TemperatureSeriesAnalysis() {
        temperatures = new double[DEFAULT_TEMPS_CAPACITY];
        tempsLen = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        checkTempsValues(temperatureSeries);

        temperatures = new double[temperatureSeries.length * 2];
        tempsLen = temperatureSeries.length;

        //copying one array to another
        System.arraycopy(
                temperatureSeries,0, temperatures,0, temperatureSeries.length
        );
    }

    public double average() {
        checkIsEmpty();

        double sum = 0;
        for (int i = 0; i < tempsLen; i++) {
            sum += temperatures[i];
        }

        return sum / tempsLen;
    }

    public double deviation() {
        checkIsEmpty();

        double aversgeTemp = average(), deviation = 0;

        for (int i = 0; i < tempsLen; i++) {
            double diff = temperatures[i] - aversgeTemp;
            deviation += diff * diff;
        }

        return Math.sqrt(deviation / tempsLen);
    }

    public double min() {
        checkIsEmpty();

        double minimum = temperatures[0];
        for (int i = 1; i < tempsLen; i++) {
            minimum = Math.min(minimum, temperatures[i]);
        }

        return minimum;
    }

    public double max() {
        checkIsEmpty();

        double maximum = temperatures[0];
        for (int i = 1; i < tempsLen; i++) {
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

        for (int i = 1; i < tempsLen; i++) {
            if (
                    Math.abs(tempValue - temperatures[i]) <= Math.abs(tempValue - closest)
            ) {
                if (Math.abs(closest) == Math.abs(temperatures[i])) {
                    closest = Math.max(closest, temperatures[i]);
                } else {
                    closest = temperatures[i];
                }
            }
        }

        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        checkIsEmpty();

        int size = 0;

        // count size
        for (int i = 0; i < tempsLen; i++) {
            if (temperatures[i] < tempValue) {
                size++;
            }
        }

        double[] smallerTemperatures = new double[size];
        int pos = 0;
        for (int i = 0; i < tempsLen; i++) {
            if (temperatures[i] < tempValue) {
                smallerTemperatures[pos++] = temperatures[i];
            }
        }

        return smallerTemperatures;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        checkIsEmpty();

        int size = 0;

        // count size
        for (int i = 0; i < tempsLen; i++) {
            if (temperatures[i] > tempValue) {
                size++;
            }
        }

        double[] greaterTemperatures = new double[size];

        int pos = 0;

        for (int i = 0; i < tempsLen; i++) {
            if (temperatures[i] > tempValue) {
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
        checkTempsValues(temps);

        if (temperatures.length < tempsLen + temps.length) {
            int newSize = Math.max(
                    tempsLen + temps.length,
                    temperatures.length * 2
            );

            // allocate bigger array
            double[] newTemperatures = new double[newSize];

            //copying one array to another
            System.arraycopy(
                    temperatures,0,
                    newTemperatures,0,
                    temperatures.length
            );

            // save reference to bigger temperatures array
            temperatures = newTemperatures;
        }

        //copying one array to another
        System.arraycopy(temps,0, temperatures,tempsLen, temps.length);

        tempsLen += temps.length;

        return tempsLen;
    }

    private void checkIsEmpty() {
        if (tempsLen == 0) {
            throw new IllegalArgumentException();
        }
    }

    private void checkTempsValues(double[] temps) {
        for (double temp: temps) {
            if (temp < MINIMUM_TEMP_VALUE) {
                throw new InputMismatchException();
            }
        }
    }


    public double[] getTemperatures() {
        return temperatures;
    }
    public int getTemps_len() {
        return tempsLen;
    }
}
