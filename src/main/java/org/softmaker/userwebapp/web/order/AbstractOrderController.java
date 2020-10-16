package org.softmaker.userwebapp.web.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softmaker.userwebapp.SecurityUtil;
import org.softmaker.userwebapp.model.Order;
import org.softmaker.userwebapp.service.OrderService;
import org.softmaker.userwebapp.to.OrderTo;
import org.softmaker.userwebapp.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.softmaker.userwebapp.util.ValidationUtil.assureIdConsistent;
import static org.softmaker.userwebapp.util.ValidationUtil.checkNew;

public abstract class AbstractOrderController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService service;

    public Order get(int id){
        int userId = SecurityUtil.authUserId();
        logger.info("get order {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id){
        int userId = SecurityUtil.authUserId();
        logger.info("delete order {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<OrderTo> getAll(){
        int userId = SecurityUtil.authUserId();
        logger.info("get all orders for user {}", userId);
        return OrderUtil.getTos(service.getAll(userId));
    }

    public Order create(Order order){
        int userId = SecurityUtil.authUserId();
        checkNew(order);
        logger.info("create order {} for user {}", order, userId);
        return service.create(order, userId);
    }

    public void update(Order order, int id){
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(order, id);
        logger.info("update order {} for user {}", order, userId);
        service.update(order, userId);
    }

    public List<OrderTo> getBetween(@Nullable LocalDate startDate, @Nullable LocalTime startTime,
                                    @Nullable LocalDate endDate,@Nullable LocalTime endTime){
        int userId = SecurityUtil.authUserId();
        logger.info("getBetween dates({} - {}) time ({} - {}) for user {}", startDate, startTime, endDate, endTime, userId);
        List<Order> ordersDateFiltered = service.getBetweenInclusive(startDate, endDate, userId);
        return OrderUtil.getFilteredTos(ordersDateFiltered, startTime, endTime);
    }
}
