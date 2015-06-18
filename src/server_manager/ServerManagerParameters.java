package server_manager;

public class ServerManagerParameters {
    protected int[] predictedUsers = new int[31];
    protected int[] bandwidthPerDay = new int[31];
    protected int[] serversPerDay = new int[31]; // peak number required each day
    protected double[] costPerDayOnDemand = new double[31]; // On-Demand only pricing
    protected double onDemandHourlyCost = 0.532; // $ Per hour per server on demand
    protected double reservedHourlyCost = 0.380; // $ Per hour per server reserved
    protected final int maxBandwidth = 1000; // m3.2xlarge server = 1000 Mbps throughput
    protected int videoQuality = 20; // 1080p ~ 20Mbps
    protected int avgServers;
}
