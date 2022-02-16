package logistics.services.results;

import logistics.entityes.SiteState;

import java.util.List;

public interface SiteStateService {

    Long save(SiteState siteState);
    List<SiteState> getAll();
}
