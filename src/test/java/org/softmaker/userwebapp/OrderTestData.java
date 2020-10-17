package org.softmaker.userwebapp;

import org.softmaker.userwebapp.model.Order;
import org.softmaker.userwebapp.to.OrderTo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.softmaker.userwebapp.model.AbstractBaseEntity.START_SEQ;

public class OrderTestData {

    public static TestMatcher<Order> ORDER_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Order.class, "user", "dateTime");
    public static TestMatcher<OrderTo> ORDER_TO_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(OrderTo.class, "dateTime");

    public static final int NOT_FOUND = 10;
    public static final int ORDER1_ID = START_SEQ + 2;
    public static final int ADMIN_ORDER_ID = START_SEQ + 4;

    public static final Order ORDER1 = new Order(ORDER1_ID, "Create test for Java EE app", BigDecimal.valueOf(40000), LocalDate.of(2020, Month.JUNE, 30));
    public static final Order ORDER2 = new Order(ORDER1_ID + 1, "Create Android App", BigDecimal.valueOf(30000), LocalDate.of(2020, Month.JULY, 15));
    public static final Order ADMIN_ORDER = new Order(ADMIN_ORDER_ID, "Create Landing Page", BigDecimal.valueOf(10000), LocalDate.of(2020, Month.JUNE, 20));

    public static final List<Order> ORDERS = List.of(ORDER2, ORDER1);

    public static Order getNew(){
        return new Order(null, "New Order", BigDecimal.valueOf(20000), LocalDate.of(2020, Month.DECEMBER, 31));
    }

    public static Order getUpdated(){
        return new Order(ORDER1_ID, "Updated Order", ORDER1.getPrice(), ORDER1.getDeadline());
    }
}
