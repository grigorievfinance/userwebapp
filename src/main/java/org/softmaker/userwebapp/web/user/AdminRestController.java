package org.softmaker.userwebapp.web.user;

import org.softmaker.userwebapp.model.User;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdminRestController extends AbstractUserController{
    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User get(int id) {
        return super.get(id);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(User user) {
        super.update(user);
    }

    @Override
    public User getByEmail(String email) {
        return super.getByEmail(email);
    }
}
