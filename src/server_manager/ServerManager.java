package server_manager;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ServerManager {
    /*
    * There are a lot of parameters and they were making the file hard to read
    * so they were moved to a separate file
    */
    ServerManagerParameters params = new ServerManagerParameters();

    /**
     * Tries to find the cheapest way to host a video, based on maximum daily
     * views contained in the input file.
     *
     * @param fileName is the file with data
     * @throws IOException
     */
    public void findValue(File fileName) throws FileNotFoundException,
            IOException {

        int averageViews;
        int maxViews;
        readInput(fileName);
        averageViews = calculateAverageViews();

        maxViews = getMax();
        System.err.println("Max Views: " + maxViews + "\n");
        calculateDailyBandwidth();
        params.avgServers = (averageViews * params.videoQuality) / params.maxBandwidth;
        calculateServersEachDay();

    }

    private void readInput(File file) throws IOException {
        Scanner input = new Scanner(new FileReader(file));

        for (int i = 0; i < 31; i++) {
            if (input.hasNextLine()) {
                params.predictedUsers[i] = input.nextInt();
            }
        }

        input.close();
    }
    private int calculateAverageViews() {
        int avg, views = 0, num = 0;

        for (int i = 0; i < 31; i++) {
            if (params.predictedUsers[i] != 0) {
                num++;
                views += params.predictedUsers[i];
            }
        }

        avg = (views / num);
        System.err.println("Average Views: " + avg);
        return avg;
    }
    private int getMax() {
        int max = 0;

        for (int i = 0; i < 31; i++) {
            if (params.predictedUsers[i] > max) {
                max = params.predictedUsers[i];
            }
        }

        return max;
    }
    private void calculateDailyBandwidth() {
        for (int i = 0; i < 31; i++) {
            params.bandwidthPerDay[i] = (params.predictedUsers[i] * params.videoQuality);
        }
    }
    private void calculateServersEachDay() {
        for (int i = 0; i < 31; i++) {
            params.serversPerDay[i] = (params.bandwidthPerDay[i] / params.maxBandwidth);
        }
    }

}
