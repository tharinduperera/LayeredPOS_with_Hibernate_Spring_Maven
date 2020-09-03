package lk.ijse.spring.dao.custom.impl;

import lk.ijse.spring.dao.CrudDAOImpl;
import lk.ijse.spring.dao.custom.OrderDetailDAO;
import lk.ijse.spring.entity.OrderDetail;
import lk.ijse.spring.entity.OrderDetailPK;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail,OrderDetailPK> implements OrderDetailDAO {

}
