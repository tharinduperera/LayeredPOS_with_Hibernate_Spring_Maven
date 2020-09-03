package lk.ijse.spring.dao.custom;

import lk.ijse.spring.dao.CrudDAO;
import lk.ijse.spring.entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    String getLastCustomerId() throws Exception ;
}
