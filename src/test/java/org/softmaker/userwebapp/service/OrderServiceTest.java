package org.softmaker.userwebapp.service;

import org.junit.jupiter.api.Test;
import org.softmaker.userwebapp.UserTestData;
import org.softmaker.userwebapp.model.Order;

import static org.softmaker.userwebapp.OrderTestData.*;
import static org.softmaker.userwebapp.UserTestData.ADMIN_ID;

class OrderServiceTest extends AbstractOrderServiceTest{

    @Test
    void getWithUser() throws Exception{
        Order adminOrder = service.getWithUser(ADMIN_ORDER_ID,ADMIN_ID);
        ORDER_MATCHER.assertMatch(adminOrder, ADMIN_ORDER);
        UserTestData.USER_MATCHER.assertMatch(adminOrder.getUser(), UserTestData.ADMIN);
    }
}