package org.softmaker.userwebapp.repository.datajpa;

import org.softmaker.userwebapp.model.Order;
import org.softmaker.userwebapp.repository.OrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaOrderRepository implements OrderRepository {

    private final CrudOrderRepository crudOrderRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaOrderRepository(CrudOrderRepository crudOrderRepository, CrudUserRepository crudUserRepository) {
        this.crudOrderRepository = crudOrderRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    @Transactional
    public Order save(Order order, int userId) {
        if (!order.isNew() && get(order.getId(), userId) == null){
            return null;
        }
        order.setUser(crudUserRepository.getOne(userId));
        return crudOrderRepository.save(order);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudOrderRepository.delete(id, userId) != 0;
    }

    @Override
    public Order get(int id, int userId) {
        return crudOrderRepository.findById(id)
                .filter(order -> order.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Order> getAll(int userId) {
        return crudOrderRepository.getAll(userId);
    }

    @Override
    public List<Order> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudOrderRepository.getBetweenHalfOpen(startDateTime, endDateTime, userId);
    }

    @Override
    public Order getWithUser(int id, int userId) {
        return crudOrderRepository.getWithUser(id, userId);
    }
}
