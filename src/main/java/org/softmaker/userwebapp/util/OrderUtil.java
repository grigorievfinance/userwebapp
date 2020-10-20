package org.softmaker.userwebapp.util;

import org.softmaker.userwebapp.model.Order;
import org.softmaker.userwebapp.to.OrderTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OrderUtil {

    public OrderUtil() {
    }

    public static List<OrderTo> getTos(Collection<Order> orders){
        return filteredByPredicate(orders, order -> true);
    }

    public static List<OrderTo> getFilteredTos(Collection<Order> orders, LocalTime startTime, LocalTime endTime){
        return filteredByPredicate(orders, order -> Util.isBetweenHalfOpen(order.getTime(), startTime, endTime));
    }

    public static List<OrderTo> filteredByPredicate(Collection<Order> orders, Predicate<Order> filter){
        return orders.stream()
                .filter(filter)
                .map(order -> createTo(order, LocalDate.now().isAfter(order.getDeadline())))
                .collect(Collectors.toList());
    }

    public static OrderTo createTo(Order order, boolean excess){
        return new OrderTo(order.getId(), order.getDateTime(), order.getDescription(), order.getPrice(), order.getDeadline(), excess);
    }
}
