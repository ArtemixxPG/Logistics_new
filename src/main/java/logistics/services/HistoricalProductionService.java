package logistics.services;

import logistics.entityes.HistoricalDemand;
import logistics.entityes.HistoricalProduction;

import java.util.Date;

public interface HistoricalProductionService {

    Long save(HistoricalProduction historicalProduction, String productName,
              String factorialName, String station, Date period);

    void updateAllBOM();
}
