package lk.ijse.spring.dao.custom.impl;

import lk.ijse.spring.dao.custom.QueryDAO;
import lk.ijse.spring.entity.CustomEntity;
import lk.ijse.spring.entity.Customer;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class QueryDAOImpl implements QueryDAO {

    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public CustomEntity getOrderDetail(String orderId) throws Exception {
        return (CustomEntity) session.createNativeQuery("SELECT c.name AS customerName ,o.id AS orderId,o.date AS orderDate FROM `Order` o INNER JOIN Customer c ON o.customerID = c.id WHERE o.id = ?1").setParameter(1,orderId).setResultTransformer(Transformers.aliasToBean(Customer.class)).uniqueResult();
    }

    @Override
    public CustomEntity getOrderDetail2(String orderID) throws Exception {
        return (CustomEntity) session.createQuery("SELECT c.name,o.id,c.id FROM `Order` o INNER JOIN Customer c ON o.customerID = c.id WHERE o.id = :id").setParameter("id",orderID).uniqueResult();
    }

//    @Override
//    public List<CustomEntity> findAll() throws Exception {
//        ResultSet resultSet = CrudUtil.execute("SELECT o.id,c.id,o.date,c.name,SUM(od.qty*od.unitPrice) AS total FROM Customer c INNER JOIN `Order` o ON o.customerID = c.id   INNER JOIN OrderDetail od ON o.id = od.orderId GROUP BY o.id,o.date,c.id,c.name");
//        List<CustomEntity> customEntities = new ArrayList<>();
//        while (resultSet.next()) {
//            customEntities.add(new CustomEntity(resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getDate(3),
//                    resultSet.getString(4),
//                    resultSet.getBigDecimal(5)));
//        }
//        return customEntities;
//    }
//
//    @Override
//    public List<CustomEntity> searchAll(String key) throws Exception {
//        ResultSet resultSet = CrudUtil.execute("SELECT o.id,c.id,o.date,c.name,SUM(od.qty*od.unitPrice) AS total FROM Customer c INNER JOIN `Order` o ON o.customerID = c.id   INNER JOIN OrderDetail od ON o.id = od.orderId WHERE o.id = ? OR o.date = ? OR c.id = ? OR c.name = ? GROUP BY o.id,o.date,c.id,c.name", key, key, key, key);
//        List<CustomEntity> customEntities = new ArrayList<>();
//        while (resultSet.next()) {
//            customEntities.add(new CustomEntity(resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getDate(3),
//                    resultSet.getString(4),
//                    resultSet.getBigDecimal(5)));
//        }
//        return customEntities;
//    }
}

