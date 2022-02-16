package logistics.services;

import logistics.entityes.Customers;

import java.util.List;

public interface CustomersService {
    Long save(Customers customers);
    Long update(Customers customers);
    void delete(Customers customers);
    List<Customers> getAll();
}
