package com.tejait.batch7.detoDesignPattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonCardDetailsDTO {

    private String name;
    private Integer age;
    private String cardHolder;
    private Long cardNumber;

}
