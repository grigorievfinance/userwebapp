package org.softmaker.userwebapp.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softmaker.userwebapp.model.User;
import org.softmaker.userwebapp.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class InMemoryUserRepository implements UserRepository {
    public static final int USER_ID1 = 1;
    public static final int USER_ID2 = 2;

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public User save(User user) {
        log.info("save {} user", user);
        if (user.isNew()){
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete user {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        log.info("get user {}", id);
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        log.info("get user with email {}", email);
        return repository.values().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findAny().orElse(null);
    }

    @Override
    public List<User> getAll() {
        log.info("get all users");
        return repository.values().stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
    }
}
