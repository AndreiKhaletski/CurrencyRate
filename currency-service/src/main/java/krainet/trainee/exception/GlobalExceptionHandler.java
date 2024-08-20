//package krainet.trainee.exception;
//
//
//
//import krainet.trainee.service.core.EnumErrors;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.util.List;
//
//@Slf4j
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<BadRequestExceptionDto> handelException(MethodArgumentNotValidException e) {
//        log.warn("Ошибка на стороне пользователя", e);
//
//        List<BadRequestExceptionDto.FieldExceptionDescription> fieldErrors =
//                e.getBindingResult().getFieldErrors()
//                        .stream()
//                        .map(it -> new BadRequestExceptionDto.FieldExceptionDescription(it.getField(), it.getDefaultMessage()))
//                        .toList();
//
//        BadRequestExceptionDto badRequestExceptionDto = new BadRequestExceptionDto(EnumErrors.structured_error.name(), fieldErrors);
//
//        return new ResponseEntity<>(badRequestExceptionDto, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<InternalServerExceptionDto> handelException(RuntimeException e) {
//        log.error("Ошибка на стороне сервера", e);
//
//        InternalServerExceptionDto dto = new InternalServerExceptionDto(
//                EnumErrors.error.name(),
//                "Внутренняя ошибка сервера. Сервер не смог корректно обработать запрос"
//        );
//
//        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<InternalServerExceptionDto> handelException(HttpMessageNotReadableException e) {
//        log.error("Ошибка на стороне клиента", e);
//
//        InternalServerExceptionDto dto = new InternalServerExceptionDto(
//                EnumErrors.error.name(),
//                "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз"
//        );
//
//        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
//    }
//}
