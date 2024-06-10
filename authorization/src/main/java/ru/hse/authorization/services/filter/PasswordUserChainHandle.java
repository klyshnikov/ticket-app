package ru.hse.authorization.services.filter;

import ru.hse.authorization.domain.SignUpRequest;
import ru.hse.authorization.services.exceptions.EmailIsNotAvailableException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PasswordUserChainHandle implements UserChainHandler {
    private final String regexString = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    @Override
    public UserChainHandler getNextHandler() {
        return null;
    }

    @Override
    public void handle(SignUpRequest request) {
        Pattern pattern = Pattern.compile(regexString);
        Matcher matcher = pattern.matcher(request.getPassword());
        if (!matcher.matches()) {
            throw new EmailIsNotAvailableException(
                    "Некорректный пароль \n" +
                            "В пароле должны содержаться хотя-бы одна маленькая латинская буква, хотя-бы одна" +
                            "большая латинская буква, цифра, спец символ из списка [@$!%*?&].\n" +
                            "Других символов быть не должно"
            );
        }
    }
}
