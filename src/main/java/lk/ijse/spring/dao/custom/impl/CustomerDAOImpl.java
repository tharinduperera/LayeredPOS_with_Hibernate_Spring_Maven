package lk.ijse.spring.dao.custom.impl;

import lk.ijse.spring.dao.CrudDAOImpl;
import lk.ijse.spring.dao.custom.CustomerDAO;
import lk.ijse.spring.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDAOImpl extends CrudDAOImpl<Customer,String> implements CustomerDAO {

    @Override
    public String getLastCustomerId() throws Exception {
        return (String) session.createNativeQuery("SELECT id FROM CUSTOMER ORDER BY id DESC LIMIT 1").uniqueResult();
    }

}
