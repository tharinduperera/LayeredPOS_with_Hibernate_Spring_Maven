package lk.ijse.spring.dao.custom;

import lk.ijse.spring.dao.CrudDAO;
import lk.ijse.spring.entity.Order;

public interface OrderDAO extends CrudDAO<Order,String> {
    String getLastOrderId() throws Exception;
}
