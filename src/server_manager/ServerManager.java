package server_manager;

import java.io.File;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class ServerManager {
    /*
    * There are a lot of parameters and they were making the file hard to read
    * so they were moved to a separate file
    */
    public ServerManagerParameters params = new ServerManagerParameters();

    /**
     * Tries to find the cheapest way to host a video, based on maximum daily
     * views contained in the input file.
     *
     * @param fileName is the file with data
     * @throws IOException
     */
    public void findValue(File fileName) throws IOException {
        readInput(fileName);
        doMath();
    }


    private void doMath(){
        getMax();
        calculateDailyBandwidth();
        calculateServersEachDay();
        params.avgServers = calculateAverageViews() *
                params.videoQuality / 24.0 /60 /
                params.maxBandwidth * params.videoLength
                * params.peakLoadMultiplier;

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
    private void getMax() {


        for (int i = 0; i < 31; i++) {
            if (params.predictedUsers[i] > params.maxViews) {
                params.maxViews = params.predictedUsers[i];
            }
        }
        System.err.println("Max Views: " + params.maxViews + "\n");
    }
    private void calculateDailyBandwidth() {
        for (int i = 0; i < 31; i++) {
            params.bandwidthPerDay[i] = ((params.predictedUsers[i] * params.videoQuality) / 24 / 60 *params.videoLength);
        }
    }
    private void calculateServersEachDay() {
        for (int i = 0; i < 31; i++) {
            params.serversPerDay[i] = (params.bandwidthPerDay[i] / params.maxBandwidth);
            System.err.print(params.serversPerDay[i]);
        }
    }

}
