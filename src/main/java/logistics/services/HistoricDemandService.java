package logistics.services;

public interface HistoricDemandService {

    void save(String fileName);
    void save();
    void updateCountTR(String fileName);
}
