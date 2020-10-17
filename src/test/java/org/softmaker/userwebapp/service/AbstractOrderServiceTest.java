package org.softmaker.userwebapp.service;

import org.junit.jupiter.api.Test;
import org.softmaker.userwebapp.model.Order;
import org.softmaker.userwebapp.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.softmaker.userwebapp.OrderTestData.*;
import static org.softmaker.userwebapp.UserTestData.ADMIN_ID;
import static org.softmaker.userwebapp.UserTestData.USER_ID;

public abstract class AbstractOrderServiceTest extends AbstractServiceTest{

    @Autowired
    protected OrderService service;

    @Test
    void get() throws Exception{
        Order actual = service.get(ADMIN_ORDER_ID, ADMIN_ID);
        ORDER_MATCHER.assertMatch(actual, ADMIN_ORDER);
    }

    @Test
    void delete() throws Exception{
        service.delete(ORDER1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(ORDER1_ID, USER_ID));
    }

    @Test
    void getBetweenInclusive() throws Exception{
        ORDER_MATCHER.assertMatch(service.getBetweenInclusive(
                LocalDate.of(2020, Month.JUNE, 01),
                LocalDate.of(2020, Month.JUNE, 30),
                USER_ID),
                ORDER2, ORDER1);
    }

    @Test
    void getAll() throws Exception{
        ORDER_MATCHER.assertMatch(service.getAll(USER_ID), ORDERS);
    }

    @Test
    void update() throws Exception{
        Order updated = getUpdated();
        service.update(updated, USER_ID);
        ORDER_MATCHER.assertMatch(service.get(ORDER1_ID, USER_ID), getUpdated());
    }

    @Test
    void create() throws Exception{
        Order created = service.create(getNew(), USER_ID);
        int newId = created.id();
        Order newOrder = getNew();
        newOrder.setId(newId);
        ORDER_MATCHER.assertMatch(created, newOrder);
        ORDER_MATCHER.assertMatch(service.get(newId, USER_ID), newOrder);
    }
}
