package lk.ijse.spring.dao.custom.impl;
import lk.ijse.spring.dao.CrudDAOImpl;
import lk.ijse.spring.dao.custom.OrderDAO;
import lk.ijse.spring.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAOImpl extends CrudDAOImpl<Order,String> implements OrderDAO {

    @Override
    public String getLastOrderId() throws Exception {
        List list =  session.createQuery("SELECT o.id FROM lk.ijse.spring.entity.Order o ORDER BY o.id DESC").setMaxResults(1).list();
        return (list.size()>0? (String) list.get(0) :null);
    }
}

