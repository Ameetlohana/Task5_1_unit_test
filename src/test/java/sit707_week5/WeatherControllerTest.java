package sit707_week5;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WeatherControllerTest {
    private static WeatherController wController;
    private static double[] hourlyTemperatures;

    @BeforeClass
    public static void setUp() {
        // Initialize controller
        wController = WeatherController.getInstance();

        // Retrieve all the hours temperatures recorded as for today
        int nHours = wController.getTotalHours();
        hourlyTemperatures = new double[nHours];
        for (int i = 0; i < nHours; i++) {
            // Hour range: 1 to nHours
            hourlyTemperatures[i] = wController.getTemperatureForHour(i + 1);
        }
    }

    @AfterClass
    public static void tearDown() {
        // Shutdown controller
        wController.close();
    }

    @Test
    public void testStudentIdentity() {
        String studentId = "s222332999";
        Assert.assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Ameet";
        Assert.assertNotNull("Student name is null", studentName);
    }

    @Test
    public void testTemperatureMin() {
        System.out.println("+++ testTemperatureMin +++");

        // Calculate min temperature
        double minTemperature = Double.MAX_VALUE;
        for (double temperature : hourlyTemperatures) {
            if (minTemperature > temperature) {
                minTemperature = temperature;
            }
        }

        // Should be equal to the min value that is cached in the controller.
        Assert.assertEquals(minTemperature, wController.getTemperatureMinFromCache(), 0.01);
    }

    @Test
    public void testTemperatureMax() {
        System.out.println("+++ testTemperatureMax +++");

        // Calculate max temperature
        double maxTemperature = Double.MIN_VALUE;
        for (double temperature : hourlyTemperatures) {
            if (maxTemperature < temperature) {
                maxTemperature = temperature;
            }
        }

        // Should be equal to the max value that is cached in the controller.
        Assert.assertEquals(maxTemperature, wController.getTemperatureMaxFromCache(), 0.01);
    }

    @Test
    public void testTemperatureAverage() {
        System.out.println("+++ testTemperatureAverage +++");

        // Calculate average temperature
        double sumTemp = 0;
        for (double temperature : hourlyTemperatures) {
            sumTemp += temperature;
        }
        double averageTemp = sumTemp / hourlyTemperatures.length;

        // Should be equal to the average value that is cached in the controller.
        Assert.assertEquals(averageTemp, wController.getTemperatureAverageFromCache(), 0.01);
    }
    
    @Test
	public void testTemperaturePersist() {
		
	}
}
