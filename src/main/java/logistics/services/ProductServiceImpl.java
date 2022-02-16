package logistics.services;

import logistics.DAO.ProductDAO;
import logistics.entityes.Products;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{


    private ProductDAO productDAO;


    public ProductServiceImpl(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    @Override
    public Long save(Products products) {
        return productDAO.save(products).getId();
    }
}
