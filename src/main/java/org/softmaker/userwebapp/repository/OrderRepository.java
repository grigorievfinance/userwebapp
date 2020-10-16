package org.softmaker.userwebapp.repository;

import org.softmaker.userwebapp.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository {

    Order save(Order order, int userId);

    boolean delete(int id, int userId);

    Order get(int id, int userId);

    List<Order> getAll(int userId);

    List<Order> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    default Order getWithUser(int id, int userId){
        throw new UnsupportedOperationException();
    }
}
