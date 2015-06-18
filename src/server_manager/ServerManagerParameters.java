package server_manager;

public class ServerManagerParameters {

    protected int[] predictedUsers = new int[3100];
    protected double[] bandwidthPerDay = new double[3100];
    protected double[] serversPerDay = new double[3100]; // peak number required each day
    protected double[] costPerDayOnDemand = new double[3100]; // On-Demand only pricing
    protected double onDemandHourlyCost = 0.532; // $ Per hour per server on demand
    protected double reservedHourlyCost = 0.380; // $ Per hour per server reserved
    protected final int maxBandwidth = 1000; // m3.2xlarge server = 1000 Mbps throughput
    protected int videoQuality = 20; // 1080p ~ 20Mbps
    protected Double avgServers;
    protected int maxViews;
    protected int videoLength = 10; // in minutes
    protected double peakLoadMultiplier = 8;


    public int[] getPredictedUsers() {return predictedUsers;}
    public double[] getBandwidthPerDay() {return bandwidthPerDay;}
    public double[] getServersPerDay() {return serversPerDay;}
    public double[] getCostPerDayOnDemand() {return costPerDayOnDemand;}
    public double getOnDemandHourlyCost() {return onDemandHourlyCost;}
    public double getReservedHourlyCost() {return reservedHourlyCost;}
    public int getMaxBandwidth() {return maxBandwidth;}
    public int getVideoQuality() {return videoQuality;}
    public int getAvgServers() {return avgServers.intValue();}
    public int getMaxViews() { return maxViews; }
}
