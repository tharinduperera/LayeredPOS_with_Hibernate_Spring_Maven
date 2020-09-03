package lk.ijse.spring.business.custom.impl;

import lk.ijse.spring.business.custom.ItemBO;
import lk.ijse.spring.dao.custom.ItemDAO;
import lk.ijse.spring.db.HibernateUtil;
import lk.ijse.spring.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.ijse.spring.util.ItemTM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemBOImpl implements ItemBO {

    @Autowired
    private ItemDAO itemDAO;

    public String getNewItemCode() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx = null;
        String lastItemCode =  null;
        try {
            tx = session.beginTransaction();
            lastItemCode = itemDAO.getLastItemCode();
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw  t;
        }finally {
            session.close();
        }

        if (lastItemCode == null) {
            return "I001";
        } else {
            int maxId = Integer.parseInt(lastItemCode.replace("I", ""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "I00" + maxId;
            } else if (maxId < 100) {
                id = "I0" + maxId;
            } else {
                id = "I" + maxId;
            }
            return id;
        }
    }

    public List<ItemTM> getAllItems() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx = null;
        List<Item> allItems = null;
        try {
            tx = session.beginTransaction();
            allItems = itemDAO.getAll();
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw  t;
        }finally {
            session.close();
        }

        List<ItemTM> itemTMS = new ArrayList<>();
        for (Item allItem : allItems) {
            itemTMS.add(new ItemTM(allItem.getCode(), allItem.getDescription(), allItem.getQtyOnHand(), allItem.getUnitPrice().doubleValue()));
        }
        return itemTMS;
    }

    public void saveItem(String code, String description, int qtyOnHand, double unitPrice) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            itemDAO.save(new Item(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw  t;
        }finally {
            session.close();
        }
    }

    public void deleteItem(String itemCode) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            itemDAO.delete(itemCode);
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw  t;
        }finally {
            session.close();
        }
    }

    public void updateItem(String description, int qtyOnHand, double unitPrice, String itemCode) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        itemDAO.setSession(session);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            itemDAO.update(new Item(itemCode, description, BigDecimal.valueOf(unitPrice), qtyOnHand));
            tx.commit();
        }catch (Throwable t){
            tx.rollback();
            throw  t;
        }finally {
            session.close();
        }
    }

}
