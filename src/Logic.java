import java.util.ArrayList;

public class Logic {
    private static ArrayList<String> tasks = new ArrayList<>();

//add tasks to the list
    public static void Add(ArrayList<String> newTasks) {
        if (newTasks != null) {
            for (String task : newTasks) {
                if (task != null && !task.trim().isEmpty()) {
                    tasks.add(task.trim());
                }
            }
        }
    }

//remove tasks from the list
    public static void Remove(ArrayList<Integer> indices) {
        if (indices != null) {
            for (int i = indices.size() - 1; i >= 0; i--) {
                int index = indices.get(i);
                if (index >= 0 && index < tasks.size()) {
                    tasks.remove(index);
                }
            }
        }
    }

// edit tasks in the list
    public static void Edit(ArrayList<Integer> indices, ArrayList<String> newTasks) {
        if (indices != null && newTasks != null) {
            for (int i = 0; i < indices.size() && i < newTasks.size(); i++) {
                int index = indices.get(i);
                String newTask = newTasks.get(i);
                if (index >= 0 && index < tasks.size() && newTask != null && !newTask.trim().isEmpty()) {
                    tasks.set(index, newTask.trim());
                }
            }
        }
    }

//get all tasks
    public static ArrayList<String> getTasks() {
        return tasks;
    }
}
