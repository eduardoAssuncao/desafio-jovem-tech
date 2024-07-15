package br.com.jovemtech.productordermanager.config.exception;

import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends CustomError{

    private final List<FieldMessage> erros = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addErro(String fieldName, String message){
        erros.add(new FieldMessage(fieldName, message));
    }

}
