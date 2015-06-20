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


    private void doMath() {
        getMax();
        calculateDailyBandwidth();
        calculateServersEachDay();
        params.avgServers = calculateAverageViews() *
                params.videoQuality / 24.0 / 60 /
                params.bandwidthPerServer * params.videoLength
                * params.peakLoadMultiplier;

    }

    private void readInput(File file) throws IOException {
        params.predictedUsers.clear();
        Scanner input = new Scanner(new FileReader(file));
        while(input.hasNextLine()){
            params.predictedUsers.add(input.nextInt());
        }
        input.close();
    }

    private double calculateAverageViews() {
        double avg, views = 0, num = 0;
        for(double dailyViews : params.predictedUsers){
            num++;
            views += dailyViews;
        }
        avg = (views / num);
        System.err.println("Average Views: " + avg);
        return avg;
    }

    private void getMax() {
        params.maxViews = 0;
        for(Integer dailyUsers : params.predictedUsers){
            if (dailyUsers > params.maxViews) {
                params.maxViews = dailyUsers;
            }
        }
        System.err.println("Max Views: " + params.maxViews + "\n");
    }

    private void calculateDailyBandwidth() {
        params.bandwidthPerDay.clear();
        for(Integer dailyUsers : params.predictedUsers){
            Double dailyBandwidth = dailyUsers * (double)params.videoQuality * params.videoLength /24 /60;
            params.bandwidthPerDay.add(dailyBandwidth);
        }
    }

    private void calculateServersEachDay() {
        for(Double dailyBandwidth : params.bandwidthPerDay){
            params.serversPerDay.clear();
            params.serversPerDay.add(dailyBandwidth / params.bandwidthPerServer);
        }
    }

    public Double getEstimatedCost() {
        Double cost = (params.reservedHourlyCost * params.getAvgServers());
        cost += (params.getAvgServers()*0.7)*0.3;

        return cost;

    }
}
