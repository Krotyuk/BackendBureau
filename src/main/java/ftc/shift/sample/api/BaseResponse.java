package ftc.shift.sample.api;

import lombok.Data;

/**
 * Базовая модель ответа сервера
 */
@Data
public class BaseResponse<T> {

    public static final String SUCCESS_STATUS = "OK";

    //Обязательное поле. Статус - либо "успех", либо оттенок ошибки, либо признак альтернативного сценария, на который смогут завязаться клиенты для отрисовки разного контента/вьюшек
    private String status = SUCCESS_STATUS;
    //прикладные данные, которые клиент ждет от сервера (не обязательное поле, т.к. не на все запросы сервер должен возвращать данные, иногда достаточно простого "ОК")
    private T data;
    //текст ошибки, в случае, если операция прошла с ошибкой (не обязательное поле)
    private String message;

}