package br.org.amigosdoautista.cadastroautista.web.error;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import br.org.amigosdoautista.cadastroautista.util.LogUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    private static final String NOT_FOUND = "Not Found";
    private static final String FORBIDDEN = "Forbidden";
    private static final String BAD_REQUEST = "Bad Request";

    private final HttpServletRequest theRequest;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<InternalServerErrorSchema> handleAllExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();

        InternalServerErrorSchema internalErrorSchema = new InternalServerErrorSchema();
        internalErrorSchema.setTimestamp(new Date());
        internalErrorSchema.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        internalErrorSchema.setError("Internal Server Error");

        StackTraceElement[] stackTrace = ex.getStackTrace();
        internalErrorSchema.setClassName(stackTrace[0].getClassName());

        internalErrorSchema.setMessage(ex.getMessage());
        internalErrorSchema.setPath(theRequest.getRequestURL().toString());

        String stacktrace = LogUtil.getStackTrace(ex);
        internalErrorSchema.setStacktrace(stacktrace);

        return new ResponseEntity<>(internalErrorSchema, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            NotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ResponseEntity<AppErrorSchema> handleNotFound(Exception ex, WebRequest request) {
        return new ResponseEntity<>(getErrorSchema(ex, HttpStatus.NOT_FOUND, NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            BadRequestException.class,
            IOException.class,
            EnvironmentException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<AppErrorSchema> handleBadRequest(Exception ex, WebRequest request) {
        ex.printStackTrace();

        return new ResponseEntity<>(getErrorSchema(ex, HttpStatus.BAD_REQUEST, BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ConstraintViolationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<AppErrorSchema> handleFieldErrors(ConstraintViolationException ex, WebRequest request) {
        ex.printStackTrace();

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<String> errorsList = constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .filter(Objects::nonNull)
                .toList();

        return new ResponseEntity<>(getErrorSchema(ex, errorsList, HttpStatus.BAD_REQUEST, BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            DataIntegrityViolationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<AppErrorSchema> handleDuplicateKeysErrors(
            DataIntegrityViolationException ex, WebRequest request) {
        ex.printStackTrace();

        List<String> errorsList = List.of("Cadastro duplicado: " + ex.getMostSpecificCause().getMessage());

        return new ResponseEntity<>(getErrorSchema(ex, errorsList, HttpStatus.BAD_REQUEST, BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            BadCredentialsException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<Object> handleBadCredentials(@NonNull BadCredentialsException ex,
            WebRequest request) {
        ex.printStackTrace();

        return new ResponseEntity<>(getErrorSchema(ex, HttpStatus.UNAUTHORIZED, "Credenciais inválidas"),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({
            TokenExpiredException.class,
            JWTVerificationException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<Object> handleNotLoggedIn(@NonNull BadCredentialsException ex,
            WebRequest request) {
        ex.printStackTrace();

        return new ResponseEntity<>(getErrorSchema(ex, HttpStatus.UNAUTHORIZED, "Você não está autenticado."),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({
            ForbiddenException.class
    })
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public final ResponseEntity<AppErrorSchema> handleForbidden(Exception ex, WebRequest request) {
        ex.printStackTrace();

        return new ResponseEntity<>(getErrorSchema(ex, HttpStatus.FORBIDDEN, FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    @Override
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNoHandlerFoundException(@NonNull NoHandlerFoundException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        ex.printStackTrace();

        AppErrorSchema notFoundSchema = getErrorSchema(ex, HttpStatus.NOT_FOUND, NOT_FOUND);
        return super.handleExceptionInternal(ex, notFoundSchema, headers, HttpStatus.NOT_FOUND, request);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleServletRequestBindingException(@NonNull ServletRequestBindingException ex,
            @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        return handleGenericInternalErrors(ex, headers, status, request);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NonNull HttpMessageNotReadableException ex,
            @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        return handleGenericInternalErrors(ex, headers, status, request);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMissingServletRequestPart(@NonNull MissingServletRequestPartException ex,
            @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        return handleGenericInternalErrors(ex, headers, status, request);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            @NonNull MissingServletRequestParameterException ex,
            @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        return handleGenericInternalErrors(ex, headers, status, request);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMissingPathVariable(@NonNull MissingPathVariableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        return handleGenericInternalErrors(ex, headers, status, request);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleTypeMismatch(@NonNull TypeMismatchException ex, @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        return handleGenericInternalErrors(ex, headers, status, request);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        ex.printStackTrace();

        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<String> errorsList = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .filter(Objects::nonNull)
                .toList();

        AppErrorSchema appErrorSchema = getErrorSchema(ex, errorsList, HttpStatus.BAD_REQUEST, BAD_REQUEST);
        return super.handleExceptionInternal(ex, appErrorSchema, headers, status, request);
    }

    private ResponseEntity<Object> handleGenericInternalErrors(@NonNull Exception ex,
            @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        ex.printStackTrace();

        AppErrorSchema badRequestSchema = getErrorSchema(ex, HttpStatus.BAD_REQUEST, BAD_REQUEST);
        return super.handleExceptionInternal(ex, badRequestSchema, headers, status, request);
    }

    private AppErrorSchema getErrorSchema(Exception ex, List<String> messages, HttpStatus httpStatus,
            String errorTitle) {
        AppErrorSchema badRequestSchema = new AppErrorSchema();
        badRequestSchema.setTimestamp(new Date());
        badRequestSchema.setStatus(httpStatus.value());
        badRequestSchema.setError(errorTitle);

        badRequestSchema.setClassName(ex.getStackTrace()[0].getClassName());

        badRequestSchema.setMessages(messages);
        badRequestSchema.setPath(theRequest.getRequestURL().toString());

        return badRequestSchema;
    }

    private AppErrorSchema getErrorSchema(Exception ex, HttpStatus httpStatus, String errorTitle) {
        return getErrorSchema(
                ex,
                List.of(ex.getMessage()),
                httpStatus, errorTitle);
    }

}
