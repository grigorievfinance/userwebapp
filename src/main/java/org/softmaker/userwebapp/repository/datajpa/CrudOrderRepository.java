package org.softmaker.userwebapp.repository.datajpa;

import org.softmaker.userwebapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudOrderRepository extends JpaRepository<Order, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Order o WHERE o.id=:id AND o.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId" ) int userId);

    @Query("SELECT o FROM Order o WHERE o.user.id=:userId ORDER BY o.dateTime DESC ")
    List<Order> getAll(@Param("userId") int userId);

    @Query("SELECT o FROM Order o WHERE o.user.id=:userId AND o.dateTime >= :startDate AND o.dateTime < :endDate ORDER BY o.dateTime DESC ")
    List<Order> getBetweenHalfOpen(@Param("startDate")LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);

    @Query("SELECT o FROM Order o JOIN FETCH o.user WHERE o.id = ?1 AND o.user.id = ?2")
    Order getWithUser(int id, int userId);
}
