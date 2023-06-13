package com.jason.remote;

import com.jason.constant.ServiceNameConstants;
import com.jason.domain.Order;
import com.jason.domain.OrderProductInfo;
import com.jason.domain.Product;
import com.jason.domain.R;
import com.jason.factory.RemoteOrderFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.List;

/**
 * @author :jason
 * @date : 2022/9/16
 */
@FeignClient(contextId = "remoteOrderService", value = ServiceNameConstants.jason_PLATFORM_ORDER, fallbackFactory = RemoteOrderFallbackFactory.class)
public interface RemoteOrderService {

        /***
         * 根据订单id查询订单
         * @param orderId
         * @return
         */
    @GetMapping("/myOrder/api/v1/order/{orderId}")
    public R<Order> getOrderInfo(@PathVariable("orderId") Long orderId);

    /***
     * 根据商品id获取商品信息
     * @param id
     * @return
     */
    @GetMapping("/myOrder/api/v1/product/{id}")
    public R<Product> getProductInfo(@PathVariable("id") Long id);


    /***
     * 支付完成根据订单id修改订单状态
     * @param orderId
     * @return
     */
    @PostMapping("/myOrder/api/v1/order/updateOrderById")
    public R<Order> updateOrderById(@RequestParam(value = "orderId") Long orderId);



    /***
     * 支付完成根据订单id修改订单状态
     * @param orderId
     * @return
     */
    @GetMapping("/myOrder/api/v1/order-product-info/selectProductByOrderId")
    public R<List<OrderProductInfo>> selectProductByOrderId(@RequestParam(value = "orderId") Long orderId);


    /***
     * 修改商品信息
     * @param product
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public R<Product> updateProduct(@RequestBody Product product);


    /***
     * 根据id修改商品库存
     * @param id
     * @param stock
     * @return
     */
    @PostMapping("/myOrder/api/v1/product/updateByOrderId")
    public R<Product> updateByOrderId(@RequestParam(value = "id", required = true) Long id, @RequestParam(value = "stock",required = true) Long stock);


    /***
     * mq是否被消费
     * @param msgId
     * @return
     */
    @PostMapping("/myOrder/api/v1/PreventMqConsumerController/isConsumerMQ")
    public R<Boolean> isConsumerMQ(@RequestParam(value = "msgId", required = true) String msgId);


    /***
     * 记录mq消费
     * @param msgId
     * @return
     */
    @PostMapping("/myOrder/api/v1/PreventMqConsumerController/addConsumerMQ")
    public R addConsumerMQ(@RequestParam(value = "msgId", required = true) String msgId);

}
