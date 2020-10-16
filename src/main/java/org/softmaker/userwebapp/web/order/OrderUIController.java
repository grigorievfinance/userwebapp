package org.softmaker.userwebapp.web.order;

import org.softmaker.userwebapp.View;
import org.softmaker.userwebapp.model.Order;
import org.softmaker.userwebapp.to.OrderTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/profile/orders")
public class OrderUIController extends AbstractOrderController{

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderTo> getAll() {
        return super.getAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(@Validated(View.Web.class) Order order) {
        if (order.isNew()){
            super.create(order);
        } else {
            super.update(order, order.getId());
        }
    }

    @Override
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderTo> getBetween(@RequestParam @Nullable LocalDate startDate,
                                    @RequestParam @Nullable LocalTime startTime,
                                    @RequestParam @Nullable LocalDate endDate,
                                    @RequestParam @Nullable LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
