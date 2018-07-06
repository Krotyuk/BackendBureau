package ftc.shift.sample.services;

import ftc.shift.sample.models.Task;
import ftc.shift.sample.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  @Autowired
  public TaskService(final TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public Task provideTask(String id) {
    return taskRepository.fetchTask(id);
  }

  public Task updateTask(Task task) {
    taskRepository.updateTask(task);
    return task;
  }

  public void deleteTask(String id) {
    taskRepository.deleteTask(id);
  }


  public Task createTask(Task task) {
    taskRepository.createTask(task);
    return task;
  }

  public Collection<Task> provideTasks() {
    return taskRepository.getAllTasks();
  }

}
