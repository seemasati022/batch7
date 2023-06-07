package com.tejait.batch7.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ErrorDetails {

    Date timestamp;
    Integer status;
    String error;
}
