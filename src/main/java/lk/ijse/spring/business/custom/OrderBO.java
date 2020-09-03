package lk.ijse.spring.business.custom;

import lk.ijse.spring.business.SuperBO;
import lk.ijse.spring.util.OrderDetailTM;
import lk.ijse.spring.util.OrderTM;

import java.util.List;

public interface OrderBO extends SuperBO {

    public String getNewOrderId() throws Exception;

    public void placeOrder(OrderTM order, List<OrderDetailTM> orderDetails) throws Exception;

}
