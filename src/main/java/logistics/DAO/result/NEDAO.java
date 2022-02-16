package logistics.DAO.result;

import logistics.entityes.results.NamedExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NEDAO extends JpaRepository<NamedExpression, Long> {

    @Query("select ne from NamedExpression as ne where ne.iteration =:iteration")
    List<NamedExpression> getNamedExpressionsByIteration(@Param("iteration")Integer iteration);

    @Query("select distinct ne.iteration from NamedExpression as ne ORDER BY ne.iteration")
    List<Integer> getNamedExpressionsUniqueIterations();
}
