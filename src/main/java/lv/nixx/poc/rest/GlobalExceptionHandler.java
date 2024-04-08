package lv.nixx.poc.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.text.StringEscapeUtils;
import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handle(MethodArgumentNotValidException e, HttpServletRequest request) {

        BindingResult bindingResult = e.getBindingResult();

        //TODO Implement this part
        List<String> collect = bindingResult.getGlobalErrors().stream()
                    .map(t -> {
                        System.out.println("-");
                        return "";
                    }).collect(toList());

        List<FieldValidationResponse> fieldValidationErrors = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError ->
                        FieldValidationResponse.builder()
                                .index(0)
                                .field(fieldError.getField())
                                .value(escapeValue(fieldError.getRejectedValue()))
                                .message(fieldError.getDefaultMessage())
                                .build()
                ).toList();


        return createResponseEntity(request, fieldValidationErrors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handle(ConstraintViolationException e, HttpServletRequest request) {

        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        Collection<FieldValidationResponse> fieldValidations = constraintViolations.stream()
                .map(t -> {
                    PathImpl p = (PathImpl) t.getPropertyPath();
                    NodeImpl leafNode = p.getLeafNode();
                    Integer index = leafNode.getIndex();

                    return FieldValidationResponse.builder()
                            .index(index == null ? 0 : index)
                            .field(leafNode.getName())
                            .value(escapeValue(leafNode.getValue()))
                            .message(t.getMessage())
                            .build();
                }).toList();

        return createResponseEntity(request, fieldValidations);
    }

    private String escapeValue(Object v) {
        if (v == null) {
            return null;
        }

        if (v instanceof String) {
            return StringEscapeUtils.escapeHtml4((String) v);
        }
        return v.toString();
    }

    private static ResponseEntity<Object> createResponseEntity(HttpServletRequest request, Collection<FieldValidationResponse> fieldValidationResponse) {
        return ResponseEntity.badRequest().body(new ValidationResponse(request.getRequestURI(), request.getMethod(), fieldValidationResponse));
    }

    @Getter
    static class ValidationResponse {
        private final String path;
        private final String httpMethod;

        private final Map<Integer, List<FieldValidationResponse>> fieldsResponse;

        ValidationResponse(String path, String httpMethod, Collection<FieldValidationResponse> fieldsResponse) {
            this.path = path;
            this.httpMethod = httpMethod;
            this.fieldsResponse = fieldsResponse.stream()
                    .collect(groupingBy(FieldValidationResponse::getIndex, TreeMap::new, toList()));
        }
    }

    @Getter
    @Builder
    static class FieldValidationResponse {
        @JsonIgnore
        private final int index;
        private final String field;
        private final String value;
        private final String message;
    }

}
