package logistics.services;

import logistics.DAO.main.TimePeriodDAO;
import logistics.entityes.TimePeriod;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TimePeriodServiceImpl implements  TimePeriodService{

    private TimePeriodDAO timePeriodDAO;


    TimePeriodServiceImpl(TimePeriodDAO timePeriodDAO){
        this.timePeriodDAO = timePeriodDAO;
    }



    @Override
    public Long save(TimePeriod timePeriod) {
        return timePeriodDAO.save(timePeriod).getId();
    }
}
