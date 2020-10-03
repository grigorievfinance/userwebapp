package org.softmaker.userwebapp.web.user;

import org.softmaker.userwebapp.HasIdAndEmail;
import org.softmaker.userwebapp.model.User;
import org.softmaker.userwebapp.repository.UserRepository;
import org.softmaker.userwebapp.web.ExceptionInfoHandler;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UniqueMailValidator implements org.springframework.validation.Validator{

    private final UserRepository repository;

    public UniqueMailValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return HasIdAndEmail.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        HasIdAndEmail user = ((HasIdAndEmail) o);
        User dbUser = repository.getByEmail(user.getEmail().toLowerCase());
        if (dbUser != null && !dbUser.getId().equals(user.getId())){
            errors.rejectValue("email", ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL);
        }
    }
}
