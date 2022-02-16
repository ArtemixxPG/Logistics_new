package logistics.services;

import logistics.entityes.HistoricalDemand;

import java.io.FileWriter;
import java.io.IOException;

public interface HistoricalDemandService {

    Long save(HistoricalDemand historicalDemand,String productName, String customerName, String station, FileWriter fileWriter) throws IOException;
}
