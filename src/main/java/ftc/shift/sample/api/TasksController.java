package ftc.shift.sample.api;


import ftc.shift.sample.models.*;
import ftc.shift.sample.services.BidService;
import ftc.shift.sample.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class TasksController {

  private static final String TASKS_PATH = Resources.API_PREFIX + "tasks";
  private static final String BIDS_PATH = Resources.API_PREFIX + "bids";

  @Autowired
  private TaskService service;
  private BidService serviceBid;

  @GetMapping(TASKS_PATH + "/{id}")
  public @ResponseBody
  BaseResponse<Task> readTask(@PathVariable String id) {
    BaseResponse<Task> response = new BaseResponse<>();
    Task task = service.provideTask(id);

    if (null == task) {
      response.setStatus("TASK_NOT_EXIST");  //для статусов  можно сделать отдельные Enum-ы или вынести как строковые константы
      response.setMessage("Задача не найдена!");
    } else {
      response.setData(task);
    }
    return response;
  }

  @GetMapping(TASKS_PATH)
  public @ResponseBody
  BaseResponse<Collection<Task>> listTasks() {
    BaseResponse<Collection<Task>> response = new BaseResponse<>();
    Collection<Task> result = service.provideTasks();
    response.setData(result);
    return response;
  }



  @PostMapping(TASKS_PATH)
  public @ResponseBody
  BaseResponse<Task> createTask(@RequestBody Task task) {
    BaseResponse<Task> response = new BaseResponse<>();
    Task result = service.createTask(task);
    response.setData(result);
    return response;
  }

  @DeleteMapping(TASKS_PATH + "/{id}")
  public @ResponseBody
  BaseResponse deleteTask(@PathVariable String id) {
    service.deleteTask(id);
    return new BaseResponse(); //нет тела, только статус
  }

  @PatchMapping(TASKS_PATH + "/{id}")
  public @ResponseBody
  BaseResponse<Task> updateTask(@PathVariable String id, @RequestBody Task task) {
    BaseResponse<Task> response = new BaseResponse<>();
    Task result = service.updateTask(task);
    response.setData(result);
    return response;
  }

  @GetMapping(BIDS_PATH)
  public @ResponseBody
  BaseResponse<Collection<Bid>> listBids() {
    BaseResponse<Collection<Bid>> response = new BaseResponse<>();
    Collection<Bid> result = serviceBid.provideBids();
    response.setData(result);
    return response;
  }


}