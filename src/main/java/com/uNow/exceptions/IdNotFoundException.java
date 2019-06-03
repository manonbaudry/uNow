package com.uNow.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class IdNotFoundException extends RuntimeException {
}
