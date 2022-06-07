package dao;

import java.util.List;

import Model.CartInfo;
import Model.OrderDetailInfo;
import Model.OrderInfo;
import Model.PaginationResult;

public interface OrderDAO {

    public void saveOrder(CartInfo cartInfo);

    public PaginationResult<OrderInfo> listOrderInfo(int page,
                                                     int maxResult, int maxNavigationPage);

    public OrderInfo getOrderInfo(String orderId);

    public List<OrderDetailInfo> listOrderDetailInfos(String orderId);

}
