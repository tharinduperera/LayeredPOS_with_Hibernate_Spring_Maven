package lk.ijse.spring.dao.custom.impl;

import lk.ijse.spring.dao.CrudDAOImpl;
import lk.ijse.spring.dao.custom.ItemDAO;
import lk.ijse.spring.entity.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemDAOImpl extends CrudDAOImpl<Item,String> implements ItemDAO {
//
//    private Session session;
//
//    @Override
//    public void setSession(Session session) {
//        this.session = session;
//    }

    @Override
    public String getLastItemCode() throws Exception {
        List list = session.createQuery("SELECT i.code FROM lk.ijse.spring.entity.Item i ORDER BY i.code DESC ").setMaxResults(1).list();
        return (list.size()>0? (String) list.get(0) :null);
    }

//    @Override
//    public List<Item> getAll() throws Exception {
//        return session.createQuery("FROM lk.ijse.spring.entity.Item",Item.class).list();
//    }
//
//    @Override
//    public Item find(String key) throws Exception {
//        return session.get(Item.class,key);
//    }
//
//    @Override
//    public void save(Item lk.ijse.spring.entity) throws Exception {
//        session.save(lk.ijse.spring.entity);
//
//    }
//
//
//    @Override
//    public void update(Item lk.ijse.spring.entity) throws Exception {
//        session.update(lk.ijse.spring.entity);
//    }
//
//    @Override
//    public void delete(String key) throws Exception {
//        session.delete(session.get(Item.class,key));
//    }
}
