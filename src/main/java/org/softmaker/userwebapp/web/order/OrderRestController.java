package org.softmaker.userwebapp.web.order;

import org.softmaker.userwebapp.View;
import org.softmaker.userwebapp.model.Order;
import org.softmaker.userwebapp.to.OrderTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = OrderRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRestController extends AbstractOrderController{
    static final String REST_URL = "/rest/profile/orders";

    @Override
    @GetMapping("/{id}")
    public Order get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<OrderTo> getAll() {
        return super.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createWithLocation(@Validated(View.Web.class) @RequestBody Order order) {
        Order created = super.create(order);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(View.Web.class) @RequestBody Order order, @PathVariable int id) {
        super.update(order, id);
    }

    @Override
    @GetMapping(value = "/filter")
    public List<OrderTo> getBetween(
            @RequestParam @Nullable LocalDate startDate,
            @RequestParam @Nullable LocalTime startTime,
            @RequestParam @Nullable LocalDate endDate,
            @RequestParam @Nullable LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}
