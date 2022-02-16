package logistics.services;

import logistics.DAO.BOMDAO;
import logistics.DAO.ProductDAO;
import logistics.entityes.BOM;
import logistics.entityes.Products;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BOMServiceImpl implements BOMService{

    private BOMDAO bomdao;
    private ProductDAO productDAO;


    public BOMServiceImpl(BOMDAO bomdao, ProductDAO productDAO){
        this.bomdao = bomdao;
        this.productDAO = productDAO;
    }

    @Override
    public Long save(BOM bom, String productName) {
        Products products = productDAO.getProductsByName(productName);
        bom.setProducts(products);
        return bomdao.save(bom).getId();
    }
}
