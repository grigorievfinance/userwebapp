package org.softmaker.userwebapp.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softmaker.userwebapp.HasId;
import org.softmaker.userwebapp.model.AbstractBaseEntity;
import org.softmaker.userwebapp.model.User;
import org.softmaker.userwebapp.service.UserService;
import org.softmaker.userwebapp.to.UserTo;
import org.softmaker.userwebapp.util.UserUtil;
import org.softmaker.userwebapp.util.exception.ModificationRestrictionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;

import static org.softmaker.userwebapp.util.ValidationUtil.assureIdConsistent;
import static org.softmaker.userwebapp.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private WebDataBinder binder;

    @Autowired
    protected UserService service;

    @Autowired
    private UniqueMailValidator emailValidator;

    private boolean modificationRestriction;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        if (binder.getTarget() != null && emailValidator.supports(binder.getTarget().getClass())){
            binder.addValidators(emailValidator);
            this.binder = binder;
        }
    }

    public List<User> getAll(){
        logger.info("get all users");
        return service.getAll();
    }

    public User get(int id){
        logger.info("get user {}", id);
        return service.get(id);
    }

    public User create(User user){
        logger.info("create user {}", user);
        checkNew(user);
        return service.create(user);
    }

    public User create(UserTo userTo){
        logger.info("create user from to {}", userTo);
        return create(UserUtil.createNewFromTo(userTo));
    }

    public void delete(int id){
        logger.info("delete user {}", id);
        checkModificationAllowed(id);
        service.delete(id);
    }

    public void update(UserTo userTo, int id){
        logger.info("update user from to {} with id={}", userTo, id);
        assureIdConsistent(userTo, id);
        checkModificationAllowed(id);
        service.update(userTo);
    }

    protected void checkAndValidateForUpdate(HasId user, int id) throws BindException{
        logger.info("update user {} with id={}", user, id);
        assureIdConsistent(user, id);
        checkModificationAllowed(id);
        binder.validate();
        if (binder.getBindingResult().hasErrors()){
            throw new BindException(binder.getBindingResult());
        }
    }

    public User getByEmail(String email){
        logger.info("get user by email {}", email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enabled){
        logger.info(enabled ? "enable {}" : "disable {}", id);
        service.enable(id, enabled);
    }

    private void checkModificationAllowed(int id){
        if (modificationRestriction && id < AbstractBaseEntity.START_SEQ + 2){
            throw  new ModificationRestrictionException();
        }
    }
}
