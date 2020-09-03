package lk.ijse.spring.test;

import lk.ijse.spring.dao.custom.QueryDAO;
import lk.ijse.spring.db.HibernateUtil;
import lk.ijse.spring.entity.CustomEntity;
import org.hibernate.Session;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

//        QueryDAO dao = DAOFactory.getInstance().getDAO(DAOType.QUERY);
//        dao.setSession(session);
//
//        CustomEntity od001 = dao.getOrderDetail("OD001");
//        System.out.println(od001.getOrderId());
//        System.out.println(od001.getOrderDate());
//        System.out.println(od001.getCustomerName());

        session.getTransaction().commit();
        session.close();
    }
}
