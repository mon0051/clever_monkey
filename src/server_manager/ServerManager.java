package server_manager;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ServerManager {

    static int[] predictedUsers = new int[31]; // predicted hits per day
    static int[] bandwidthPerDay = new int[31]; // bandwidth required per day
    static int[] serversPerDay = new int[31]; // peak number of servers required each day
    static double[] costPerDayOnDemand = new double[31]; // On-Demand only pricing
    static double[] costPerDayCombined = new double[31]; // On-Demand/Reserved pricing
    static double onDemandHourlyCost = 0.532; // $ Per hour per server on demand
    static double reservedHourlyCost = 0.380; // $ Per hour per server reserved
    static int maxBandwidth = 1000; // m3.2xlarge server = 1000 Mbps throughput
    static int videoQuality = 20; // 1080p ~ 20Mbps
    static int avgServers;
    static double lowestCost;

    /**
     * Tries to find the cheapest way to host a video, based on maximum daily
     * views contained in the input file.
     *
     * @param fileName
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void findValue(File fileName) throws FileNotFoundException,
            IOException {
        int averageViews;
        int maxViews;
        readInput(fileName);

        averageViews = calculateAverageViews();
        System.err.println("Average Views: " + averageViews);

        maxViews = getMax();
        System.err.println("Max Views: " + maxViews + "\n");

        calculateDailyBandwidth();

        avgServers = (averageViews * videoQuality) / maxBandwidth;

        calculateServersPerDay();

    }

    static void readInput(File file) throws FileNotFoundException, IOException {
        Scanner input = new Scanner(new FileReader(file));

        for (int i = 0; i < 31; i++) {
            if (input.hasNextLine()) {
                predictedUsers[i] = input.nextInt();
            }
        }

        input.close();
    }

    static int calculateAverageViews() {
        int avg, views = 0, num = 0;

        for (int i = 0; i < 31; i++) {
            if (predictedUsers[i] != 0) {
                num++;
                views += predictedUsers[i];
            }
        }

        avg = (views / num);

        return avg;
    }

    static int getMax() {
        int max = 0;

        for (int i = 0; i < 31; i++) {
            if (predictedUsers[i] > max) {
                max = predictedUsers[i];
            }
        }

        return max;
    }

    static void calculateDailyBandwidth() {
        for (int i = 0; i < 31; i++) {
            bandwidthPerDay[i] = (predictedUsers[i] * videoQuality);
        }
    }

    static void calculateServersPerDay() {
        for (int i = 0; i < 31; i++) {
            serversPerDay[i] = (bandwidthPerDay[i] / maxBandwidth);
        }
    }
}
