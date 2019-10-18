package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testClassCreationWithoutArguments() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();

        // compare expected result with actual result
        assertEquals(8, seriesAnalysis.temperatures.length);
        assertEquals(0, seriesAnalysis.temps_len);
    }

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

//    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

//    @Ignore
    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {1.0, 1.0, 1.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);

        double[] temperatureSeries1 = {5.0, -1.0, 3.0, 3.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries1);

        expResult = 2.17944947;

        actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {1.0, 1.0, 1.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(1.0, seriesAnalysis.min(), 0.00001);

        double[] temperatureSeries1 = {5.0, -1.0, 3.0, 3.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries1);

        assertEquals(-1.0, seriesAnalysis.min(), 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {1.0, 1.0, 1.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(1.0, seriesAnalysis.max(), 0.00001);

        double[] temperatureSeries1 = {5.0, -1.0, 3.0, 3.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries1);

        assertEquals(5.0, seriesAnalysis.max(), 0.00001);
    }



    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {1.0, 1.0, 1.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(1.0, seriesAnalysis.findTempClosestToZero(), 0.00001);

        double[] temperatureSeries1 = {5.0, -1.0, 3.0, 3.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries1);

        assertEquals(-1.0, seriesAnalysis.findTempClosestToZero(), 0.00001);

        double[] temperatureSeries2 = {5.0, -1.0, 3.0, 1.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries2);

        assertEquals(1.0, seriesAnalysis.findTempClosestToZero(), 0.00001);
    }


    @Test
    public void testFindTempClosestToGivenValue() {
        double[] temperatureSeries = {1.0, 1.0, 1.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(1.0, seriesAnalysis.findTempClosestToValue(1.0), 0.00001);

        double[] temperatureSeries1 = {5.0, -1.0, 3.0, 3.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries1);

        assertEquals(3.0, seriesAnalysis.findTempClosestToValue(4.0), 0.00001);

        double[] temperatureSeries2 = {5.0, -2.0, 3.0, 2.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries2);

        assertEquals(2.0, seriesAnalysis.findTempClosestToValue(0.0), 0.00001);
    }


    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {1.0, 2.0, 3.0, 4.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double[] smallerTemps = {1.0, 2.0};
        double[] resultTemps = seriesAnalysis.findTempsLessThen(3.0);

        for (int i = 0; i < resultTemps.length; i++) {
            assertEquals(smallerTemps[i], resultTemps[i], 0.00001);
        }

        double[] temperatureSeries1 = {1.0, 2.0, 3.0, 4.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries1);

        double[] smallerTemps1 = {};
        resultTemps = seriesAnalysis.findTempsLessThen(1.0);

        for (int i = 0; i < resultTemps.length; i++) {
            assertEquals(smallerTemps1[i], resultTemps[i], 0.00001);
        }
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {1.0, 2.0, 3.0, 4.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double[] smallerTemps = {3.0, 4.0};
        double[] resultTemps = seriesAnalysis.findTempsGreaterThen(2.0);

        for (int i = 0; i < resultTemps.length; i++) {
            assertEquals(smallerTemps[i], resultTemps[i], 0.00001);
        }

        double[] temperatureSeries1 = {1.0, 2.0, 3.0, 4.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries1);

        double[] smallerTemps1 = {};
        resultTemps = seriesAnalysis.findTempsGreaterThen(5.0);

        for (int i = 0; i < resultTemps.length; i++) {
            assertEquals(smallerTemps1[i], resultTemps[i], 0.00001);
        }
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {1.0, 2.0, 3.0, 4.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        TempSummaryStatistics resultStatistics = seriesAnalysis.summaryStatistics();

        assertEquals(2.5, resultStatistics.avgTemp, 0.00001);
        assertEquals(1.1180339, resultStatistics.devTemp, 0.00001);
        assertEquals(1.0, resultStatistics.minTemp, 0.00001);
        assertEquals(4.0, resultStatistics.maxTemp, 0.00001);

    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {1.0, 2.0, 3.0, 4.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(4, seriesAnalysis.temps_len);
        assertEquals(8, seriesAnalysis.temperatures.length);

        assertEquals(5, seriesAnalysis.addTemps(5.0));

        assertEquals(5, seriesAnalysis.temps_len);
        assertEquals(8, seriesAnalysis.temperatures.length);

        assertEquals(9, seriesAnalysis.addTemps(6.0, 7.0, 8.0, 9.0));

        assertEquals(9, seriesAnalysis.temps_len);
        assertEquals(16, seriesAnalysis.temperatures.length);


        double[] temperatureSeries1 = {1.0};
        seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries1);

        assertEquals(1, seriesAnalysis.temps_len);
        assertEquals(2, seriesAnalysis.temperatures.length);

        assertEquals(5, seriesAnalysis.addTemps(2.0, 3.0, 4.0, 5.0));

        assertEquals(5, seriesAnalysis.temps_len);
        assertEquals(5, seriesAnalysis.temperatures.length);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsError() {
        double[] temperatureSeries = {1.0, 2.0, 3.0, 4.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.addTemps(-275.0);
    }

    @Test(expected = InputMismatchException.class)
    public void testCreateClassWithToSmallTemps() {
        double[] temperatureSeries = {-275.0};

        // expect exception here
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
    }

}
