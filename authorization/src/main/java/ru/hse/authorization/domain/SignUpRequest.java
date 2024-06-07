package ru.hse.authorization.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на регистрацию")
public class SignUpRequest {

    @Schema(description = "Имя пользователя", example = "Misha")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String nickname;

    @Schema(description = "Адрес электронной почты", example = "misha@gmail.com")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    private String email;

    @Schema(description = "Пароль", example = "**S1ngLetoN!**")
    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;

}
