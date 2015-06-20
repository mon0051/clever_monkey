package server_manager;

import java.util.ArrayList;

public class ServerManagerParameters {

    protected ArrayList<Integer> predictedUsers = new ArrayList<Integer>();
    protected ArrayList<Double> bandwidthPerDay = new ArrayList<Double>();
    protected ArrayList<Double> serversPerDay = new ArrayList<Double>(); // peak number required each day
    protected ArrayList<Double> costPerDayOnDemand = new ArrayList<Double>(); // On-Demand only pricing
    protected double onDemandHourlyCost = 0.532; // $ Per hour per server on demand
    protected double reservedHourlyCost = 0.380; // $ Per hour per server reserved
    protected final int bandwidthPerServer = 1000; // m3.2xlarge server = 1000 Mbps throughput
    protected int videoQuality = 20; // 1080p ~ 20Mbps
    protected Double avgServers;
    protected int maxViews;
    protected int videoLength = 10; // in minutes
    protected double peakLoadMultiplier = 8;


    public ArrayList<Integer> getPredictedUsers() {return predictedUsers;}
    public ArrayList<Double> getBandwidthPerDay() {return bandwidthPerDay;}
    public ArrayList<Double> getServersPerDay() {return serversPerDay;}
    public ArrayList<Double> getCostPerDayOnDemand() {return costPerDayOnDemand;}
    public double getOnDemandHourlyCost() {return onDemandHourlyCost;}
    public double getReservedHourlyCost() {return reservedHourlyCost;}
    public int getBandwidthPerServer() {return bandwidthPerServer;}
    public int getVideoQuality() {return videoQuality;}
    public int getAvgServers() {return avgServers.intValue();}
    public int getMaxViews() { return maxViews; }
}
