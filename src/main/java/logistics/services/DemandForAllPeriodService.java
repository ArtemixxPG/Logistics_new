package logistics.services;

import logistics.entityes.Customers;

import java.util.List;

public interface DemandForAllPeriodService {

    void save();
    List<Customers> getCurrentCustomers();

}
