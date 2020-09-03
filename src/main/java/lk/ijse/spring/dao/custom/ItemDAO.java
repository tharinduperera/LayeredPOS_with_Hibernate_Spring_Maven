package lk.ijse.spring.dao.custom;

import lk.ijse.spring.dao.CrudDAO;
import lk.ijse.spring.entity.Item;

public interface ItemDAO extends CrudDAO<Item,String> {

    String getLastItemCode()throws Exception;
}
