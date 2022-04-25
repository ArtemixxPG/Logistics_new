package logistics.DAO.main;

import logistics.entityes.DCsAndFactories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DC_FactoriesDAO extends JpaRepository<DCsAndFactories, Long> {
    @Query("select d from DCsAndFactories as d where d.name=:name")
    DCsAndFactories getDCsAndFactoriesByName(@Param("name")String name);

    @Query("select d from DCsAndFactories as d where d.name like CONCAT('%',:bName)")
    List<DCsAndFactories> getDCsAndFactoriesByRole(@Param("bName")String name);
}
