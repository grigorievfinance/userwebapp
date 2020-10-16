package org.softmaker.userwebapp.service;

import org.softmaker.userwebapp.model.Order;
import org.softmaker.userwebapp.repository.OrderRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.softmaker.userwebapp.util.DateTimeUtil.atStartOfDayOrMin;
import static org.softmaker.userwebapp.util.DateTimeUtil.atStartOfNextDayOrMax;
import static org.softmaker.userwebapp.util.ValidationUtil.checkNotFoundWithId;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order get(int id, int userId){
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public void delete(int id, int userId){
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public List<Order> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId){
        return repository.getBetweenHalfOpen(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), userId);
    }

    public List<Order> getAll(int userId){
        return repository.getAll(userId);
    }

    public void update(Order order, int userId){
        Assert.notNull(order, "order must not be null");
        checkNotFoundWithId(repository.save(order, userId), order.id());
    }

    public Order create(Order order, int userId){
        Assert.notNull(order, "order must not be null");
        return repository.save(order, userId);
    }

    public Order getWithUser(int id, int userId){
        return checkNotFoundWithId(repository.getWithUser(id, userId), id);
    }
}
