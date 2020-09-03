package lk.ijse.spring.business.custom.impl;

import lk.ijse.spring.business.custom.CustomerBO;
import lk.ijse.spring.dao.custom.CustomerDAO;
import lk.ijse.spring.db.HibernateUtil;
import lk.ijse.spring.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.ijse.spring.util.CustomerTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    private CustomerDAO customerDAO; //= DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);

    public String getNewCustomerId() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;
        String lastCustomerId = null;
        try {
            tx = session.beginTransaction();
            lastCustomerId = customerDAO.getLastCustomerId();
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw  t;
        }finally {
            session.close();
        }

        if (lastCustomerId == null) {
            return "C001";
        } else {
            int maxId = Integer.parseInt(lastCustomerId.replace("C", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "C00" + maxId;
            } else if (maxId < 100) {
                id = "C0" + maxId;
            } else {
                id = "C" + maxId;
            }
            return id;
        }
    }


    public List<CustomerTM> getAllCustomers() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        List<Customer> allCustomers = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            allCustomers = customerDAO.getAll();
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw  t;
        }finally {
            session.close();
        }

        List<CustomerTM> customerTMS = new ArrayList<>();
        for (Customer customer : allCustomers) {
            customerTMS.add(new CustomerTM(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerTMS;
    }

    public void saveCustomer(String id, String name, String address) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            customerDAO.save(new Customer(id, name, address));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw  t;
        }finally {
            session.close();
        }
    }

    public void deleteCustomer(String customerId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            customerDAO.delete(customerId);
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw  t;
        }finally {
            session.close();
        }
    }

    public void updateCustomer(String name, String address, String customerId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        customerDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            customerDAO.update(new Customer(customerId, name, address));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw  t;
        }finally {
            session.close();
        }
    }

}
