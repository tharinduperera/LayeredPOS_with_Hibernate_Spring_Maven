package lk.ijse.spring.business.custom.impl;

import lk.ijse.spring.business.custom.OrderBO;
import lk.ijse.spring.dao.custom.CustomerDAO;
import lk.ijse.spring.dao.custom.ItemDAO;
import lk.ijse.spring.dao.custom.OrderDAO;
import lk.ijse.spring.dao.custom.OrderDetailDAO;
import lk.ijse.spring.db.HibernateUtil;
import lk.ijse.spring.entity.Item;
import lk.ijse.spring.entity.Order;
import lk.ijse.spring.entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.ijse.spring.util.OrderDetailTM;
import lk.ijse.spring.util.OrderTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Component
public class OrderBOImpl implements OrderBO {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private CustomerDAO customerDAO;


    public String getNewOrderId() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        orderDAO.setSession(session);
        Transaction tx = null;
        String lastOrderId =  null;
        try {
            tx = session.beginTransaction();
            lastOrderId = orderDAO.getLastOrderId();
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw  t;
        }finally {
            session.close();
        }

        if (lastOrderId == null) {
            return "OD001";
        } else {
            int maxId = Integer.parseInt(lastOrderId.replace("OD", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "OD00" + maxId;
            } else if (maxId < 100) {
                id = "OD0" + maxId;
            } else {
                id = "OD" + maxId;
            }
            return id;
        }
    }


    public void placeOrder(OrderTM order, List<OrderDetailTM> orderDetails) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        orderDAO.setSession(session);
        orderDetailDAO.setSession(session);
        customerDAO.setSession(session);

        try {
            session.getTransaction().begin();

            orderDAO.save(new Order(order.getOrderId(),
                    Date.valueOf(order.getOrderDate()),
                    customerDAO.find(order.getCustomerId())));


            for (OrderDetailTM orderDetail : orderDetails) {
                orderDetailDAO.save(new OrderDetail(order.getOrderId(),
                        orderDetail.getCode(),
                        orderDetail.getQty(),
                        BigDecimal.valueOf(orderDetail.getUnitPrice())));

                Item item = itemDAO.find(orderDetail.getCode());
                item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getQty());
                itemDAO.update(item);

            }

            session.getTransaction().commit();

        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        }finally {
           session.close();
        }

    }

}
