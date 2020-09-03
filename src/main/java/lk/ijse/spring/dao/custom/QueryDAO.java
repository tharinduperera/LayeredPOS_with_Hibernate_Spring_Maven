package lk.ijse.spring.dao.custom;

import lk.ijse.spring.dao.SuperDAO;
import lk.ijse.spring.entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO{
    // join queries

    CustomEntity getOrderDetail(String orderId) throws Exception;

    CustomEntity getOrderDetail2(String orderID) throws Exception;

//    List<CustomEntity> findAll() throws  Exception;
//
//    List<CustomEntity> searchAll(String key) throws Exception;
}
