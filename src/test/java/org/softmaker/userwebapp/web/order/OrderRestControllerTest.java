package org.softmaker.userwebapp.web.order;

import org.junit.jupiter.api.Test;
import org.softmaker.userwebapp.OrderTestData;
import org.softmaker.userwebapp.model.Order;
import org.softmaker.userwebapp.service.OrderService;
import org.softmaker.userwebapp.util.exception.NotFoundException;
import org.softmaker.userwebapp.web.AbstractControllerTest;
import org.softmaker.userwebapp.web.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.softmaker.userwebapp.OrderTestData.*;
import static org.softmaker.userwebapp.TestUtil.readFromJson;
import static org.softmaker.userwebapp.TestUtil.userHttpBasic;
import static org.softmaker.userwebapp.UserTestData.USER;
import static org.softmaker.userwebapp.UserTestData.USER_ID;
import static org.softmaker.userwebapp.util.OrderUtil.createTo;
import static org.softmaker.userwebapp.util.OrderUtil.getTos;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = OrderRestController.REST_URL + '/';

    @Autowired
    private OrderService service;

    @Test
    void get() throws Exception{
        perform(MockMvcRequestBuilders.get(REST_URL + ORDER1_ID)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ORDER_MATCHER.contentJson(ORDER1));
    }

    @Test
    void delete() throws Exception{
       perform(MockMvcRequestBuilders.delete(REST_URL + ORDER1_ID)
               .with(userHttpBasic(USER)))
               .andDo(print())
               .andExpect(status().isNoContent());
       assertThrows(NotFoundException.class, () -> service.get(ORDER1_ID, USER_ID));
    }

    @Test
    void getAll() throws Exception{
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(ORDER_TO_MATCHER.contentJson(getTos(ORDERS)));
    }

    @Test
    void createWithLocation() throws Exception{
        Order newOrder = OrderTestData.getNew();
        ResultActions actions = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newOrder))
                .with(userHttpBasic(USER)))
                .andDo(print());

        Order created = readFromJson(actions, Order.class);
        int newId = created.id();
        newOrder.setId(newId);
        ORDER_MATCHER.assertMatch(created, newOrder);
        ORDER_MATCHER.assertMatch(service.get(newId, USER_ID), newOrder);
    }

    @Test
    void update() throws Exception{
        Order updated = OrderTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + ORDER1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNoContent());

        ORDER_MATCHER.assertMatch(service.get(ORDER1_ID, USER_ID), updated);
    }

    @Test
    void filter() throws Exception{
        perform(MockMvcRequestBuilders.get(REST_URL + "filter")
                .param("startDate", "2020-06-01").param("startTime", "00:00")
                .param("endDate", "2020-06-30").param("endTime", "00:00")
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}