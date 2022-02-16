package logistics.services;

import logistics.entityes.BOM;

public interface BOMService {
    Long save(BOM bom, String productName);
}
