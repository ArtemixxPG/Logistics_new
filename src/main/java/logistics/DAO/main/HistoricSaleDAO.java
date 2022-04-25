package logistics.DAO.main;

import logistics.entityes.HistoricSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoricSaleDAO extends JpaRepository<HistoricSale, Long> {

    //TODO delete from historic_sale where id_sale in (select id_sale from sale where product is null)
}
