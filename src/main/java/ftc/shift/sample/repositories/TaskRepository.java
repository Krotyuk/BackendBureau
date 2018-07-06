package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Task;

import java.util.Collection;

/**
 * Интерфейес для получения данных по книгам из БД
 */
public interface TaskRepository {

  Task fetchTask(String id);

  Task updateTask(Task task);

  void deleteTask(String id);

  Task createTask(Task task);

  Collection<Task> getAllTasks();
}
