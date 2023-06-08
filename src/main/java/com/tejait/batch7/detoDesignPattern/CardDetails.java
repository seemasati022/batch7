package com.tejait.batch7.detoDesignPattern;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.util.Internal;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "card_details")
public class CardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    private String cardHolder;
    private Long cardNumber;
    private Integer pin;
}
