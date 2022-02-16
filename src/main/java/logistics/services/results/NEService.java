package logistics.services.results;

import logistics.diagrams.NEDiagrams;
import logistics.entityes.results.NamedExpression;

import java.util.List;

public interface NEService {

    Long save(NamedExpression namedExpression);
    void delete(NamedExpression namedExpression);
    List<NamedExpression> getByIteration(Integer iteration);
    Long update(NamedExpression namedExpression);
     NEDiagrams getDataset();
}
