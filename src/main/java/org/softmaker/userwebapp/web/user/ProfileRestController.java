package org.softmaker.userwebapp.web.user;

import org.softmaker.userwebapp.model.User;
import org.springframework.stereotype.Controller;

@Controller
public class ProfileRestController extends AbstractUserController{
    @Override
    public User get(int id) {
        return super.get(id);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(User user) {
        super.update(user);
    }
}
