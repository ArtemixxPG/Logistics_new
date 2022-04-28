package logistics.services.results;

import logistics.DAO.result.all.SiteStateDAO;
import logistics.entityes.SiteState;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SiteStateServiceImpl implements SiteStateService {

    private SiteStateDAO siteStateDAO;

    public SiteStateServiceImpl(SiteStateDAO siteStateDAO){
        this.siteStateDAO = siteStateDAO;
    }

    @Override
    public Long save(SiteState siteState) {
        return siteStateDAO.save(siteState).getId();
    }

    @Override
    public List<SiteState> getAll() {
        return siteStateDAO.findAll();
    }
}
