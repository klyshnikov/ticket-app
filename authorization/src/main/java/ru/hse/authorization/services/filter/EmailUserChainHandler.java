package ru.hse.authorization.services.filter;

import ru.hse.authorization.domain.SignUpRequest;
import ru.hse.authorization.services.dto.UserInService;
import ru.hse.authorization.services.exceptions.EmailIsNotAvailableException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUserChainHandler implements UserChainHandler{

    private final String regexString = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    public UserChainHandler getNextHandler() {
        return new PasswordUserChainHandle();
    }

    @Override
    public void handle(SignUpRequest request) {
        Pattern pattern = Pattern.compile(regexString);
        Matcher matcher = pattern.matcher(request.getEmail());
        if (!matcher.matches()) {
            throw new EmailIsNotAvailableException(
                    "Некорректный email. Пример правильного email: misha@gmail.com"
            );
        }
        getNextHandler().handle(request);
    }
}
