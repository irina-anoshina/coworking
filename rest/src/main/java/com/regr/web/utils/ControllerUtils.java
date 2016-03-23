package com.regr.web.utils;

import com.regr.web.controller.dto.ErrorFieldResultDto;
import com.regr.web.controller.dto.ObjectResultDto;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maratische on 23.03.16.
 */
public class ControllerUtils {

    private ControllerUtils() {

    }

    public static List<ObjectResultDto> parseFieldErrors(List<FieldError> fieldErrors,
                                                         String fieldErrorCode)
    {
        List<ObjectResultDto> messages = new ArrayList<>();
        for(FieldError err : fieldErrors)
        {
            messages.add(new ErrorFieldResultDto(err.getField(), err.getDefaultMessage(), fieldErrorCode));
        }

        return messages;
    }

}
