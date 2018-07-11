package ftc.shift.sample.api;


import ftc.shift.sample.models.*;
import ftc.shift.sample.services.BidService;
import ftc.shift.sample.services.TaskService;
import ftc.shift.sample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class TasksController {

  private static final String TASKS_PATH = Resources.API_PREFIX + "tasks";
  private static final String BIDS_PATH = Resources.API_PREFIX + "tasks/{id}/bids";
  private static final String USERS_PATH = Resources.API_PREFIX + "users";

  @Autowired
  private TaskService service;
  @Autowired
  private UserService serviceUser;
  @Autowired
  private BidService serviceBid;


  @GetMapping(BIDS_PATH)
  public @ResponseBody
  BaseResponse<Collection<Bid>> listBids(@PathVariable String id) {

    BaseResponse<Collection<Bid>> response = new BaseResponse<>();
    Collection<Bid> result = serviceBid.provideBids(id);
    response.setData(result);
    return response;
  }
  

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


   @GetMapping(USERS_PATH + "/{id}")
  public @ResponseBody
  BaseResponse<User> readUser(@PathVariable String id) {
    BaseResponse<User> response = new BaseResponse<>();
    User user = serviceUser.provideUser(id);

    if (null == user) {
      response.setStatus("USER_NOT_EXIST");
      response.setMessage("Пользователь не найден!");
    } else {
      response.setData(user);
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
  BaseResponse<Task> createTask(@RequestBody Task task, String user_name) {
    BaseResponse<Task> response = new BaseResponse<>();
    Task result = service.createTask(task, user_name);
    response.setData(result);
    return response;
  }

     @PostMapping(USERS_PATH)
    public @ResponseBody
    BaseResponse<User> createUser(@RequestBody User user) {
      BaseResponse<User> response = new BaseResponse<>();
      User result = serviceUser.createUser(user);
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

  

  @GetMapping(USERS_PATH)
  public @ResponseBody
  BaseResponse<Collection<User>> listUsers() {
    BaseResponse<Collection<User>> response = new BaseResponse<>();
    Collection<User> result = serviceUser.provideUsers();
    response.setData(result);
    return response;
  }

}