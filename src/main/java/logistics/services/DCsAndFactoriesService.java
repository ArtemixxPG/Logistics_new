package logistics.services;

import logistics.entityes.DCsAndFactories;

import java.util.List;

public interface DCsAndFactoriesService {
    Long save(DCsAndFactories dCsAndFactories);
    Long update(DCsAndFactories dCsAndFactories);
    void delete(DCsAndFactories dCsAndFactories);
    List<DCsAndFactories> getAll();
}
