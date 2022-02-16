package logistics.services;

import logistics.entityes.Suppliers;

import java.util.List;

public interface SuppliersService {
    Long save(Suppliers suppliers);
    Long update(Suppliers suppliers);
    void delete(Suppliers suppliers);
    List<Suppliers> getAll();
}
