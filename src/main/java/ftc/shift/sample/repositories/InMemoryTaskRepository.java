package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Task;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализиция, хранящая все данные в памяти приложения
 */
@Repository
public class InMemoryTaskRepository implements TaskRepository {


  private Map<String, Task> taskCache = new HashMap<>();
  private Map<String, Task> taskCacheFull = new HashMap<>();


  public InMemoryTaskRepository() {

    taskCache.put("1", new Task(1, 3, "Перенести бабушку", "С пятого на первый этаж!", "09.09.2009"));
    taskCache.put("2", new Task(2, 2, "Переставить винду", "Нужно срочно!!1!", "08.08.2008"));

    taskCacheFull.put("1", new Task(1, 3, "Перенести бабушку", "Бабку надо нести очень долго и мучительно, чтобы отбить желание жить", "05.05.1999", "ok"));
    taskCacheFull.put("2", new Task(2, 2, "Переставить винду", "Винду просит переустановить одинокий бородатый мужик", "09.09.2009", "ok"));


  }

  @Override
  public Task fetchTask(final String id) {
    return taskCacheFull.get(id);
  }

  @Override
  public Task updateTask(final Task task) {
    taskCache.put(task.getTask_id().toString(), task);
    return task;
  }

  @Override
  public void deleteTask(final String id) {
    taskCache.remove(id);
  }

  @Override
  public Task createTask(final Task task) {
    task.setTask_id(Integer.valueOf((int)(System.currentTimeMillis())));  //очень плохой способ генерировать Id, тут только для примера
    taskCache.put(task.getTask_id().toString(), task);
    return task;
  }

  @Override
  public Collection<Task> getAllTasks() {
    return taskCache.values();
  }

}
