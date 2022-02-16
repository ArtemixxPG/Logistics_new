package logistics.DAO;

import logistics.entityes.TimePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TimePeriodDAO extends JpaRepository<TimePeriod, Long> {

    @Query("select d from TimePeriod as d where d.name=:name")
    TimePeriod getTimePeriodByName(@Param("name")Date name);
}
