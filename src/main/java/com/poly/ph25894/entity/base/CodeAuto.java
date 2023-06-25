package com.poly.ph25894.entity.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.util.Random;

@Getter
@Setter
@Component
public class CodeAuto {

    public String generateRandomCode(String firstCharacter) {
        StringBuilder codeBuilder = new StringBuilder();
        codeBuilder.append(firstCharacter);
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            codeBuilder.append(random.nextInt(10));
        }
        return codeBuilder.toString();
    }

}
