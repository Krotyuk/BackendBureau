package ftc.shift.sample.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * Lombok (https://projectlombok.org/) инструмент, позволяющий не писать геттеры-сеттеры, конструкторы и тд. Они генерируются автоматом.
 * Его использование не является обязательным, но во многих ситуациях заметно упрощает разработку.
 * Так же его использование не отменяет осознанного подхода к инкапсуляции, а именно необходимости открывать доступ к полям (геттеры-сеттеры).
 * Для dto-классов обычно это не распространяется, т.к. они требуют наличия этих методов, а так же equals/hashCode и конструктора
 */


@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Task implements Serializable{

    Task task;
    private Integer task_id;
    private Integer user_id; //Потом будет получаться из другого источника
    private String title;
    private String short_description;
    private String description;
    private String date;
    private String status;


    //для запроса общего списка задач
    public Task(Integer task_id, Integer user_id, String title, String short_description, String date){
        this.task_id = task_id;
        this.user_id = user_id;
        this.title = title;
        this.short_description = short_description;
        this.date = date;

    }
    //для запроса конкретной задачи по иду
    public Task(Integer task_id, Integer user_id, String title, String description, String date, String status){
        this.task_id = task_id;
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.status = status;

    }


}
