import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class represents the graphical user interface (GUI) for a to-do list application.
 * It allows users to add, remove, and edit tasks in the list. 
 * The GUI is built using Java Swing components and interacts with the Logic class to manage the tasks.
 */

public class GUI extends JFrame {
    private JList<String> taskList;
    private JTextField addTaskField;
// Constructor to set up the GUI components and event listeners
    @SuppressWarnings("Convert2Lambda")
    public GUI() {
        setTitle("To do List");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
// Left panel for buttons
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
// Button panel to hold the action buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
//  Add button to add tasks to the list
        JButton AddButton = new JButton("Add Task");
        AddButton.addActionListener(new ActionListener() {
            @SuppressWarnings("override")
            public void actionPerformed(ActionEvent e) {
                String task = addTaskField.getText();
                ArrayList<String> taskList = new ArrayList<>();
                taskList.add(task);
                Logic.Add(taskList);
                updateTaskList();
                addTaskField.setText("");
            }
        });
        buttonPanel.add(AddButton);
//  Remove button to remove selected tasks from the list   
        JButton RemoveButton = new JButton("Remove Task");
        RemoveButton.addActionListener(new ActionListener() {
            @SuppressWarnings("override")
            public void actionPerformed(ActionEvent e) {
                int index = taskList.getSelectedIndex();
                if (index >= 0) {
                    ArrayList<Integer> indices = new ArrayList<>();
                    indices.add(index);  // index is already 0-based
                    Logic.Remove(indices);
                    updateTaskList();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a task to remove.");
                }
            }
        });
        buttonPanel.add(RemoveButton);
//  Edit button to edit selected tasks in the list
        JButton EditButton = new JButton("Edit Task");
        EditButton.addActionListener(new ActionListener() {
            @SuppressWarnings("override")
            public void actionPerformed(ActionEvent e) {
                int index = taskList.getSelectedIndex();
                if (index >= 0) {
                    String newTask = JOptionPane.showInputDialog("Edit task:", Logic.getTasks().get(index));
                    if (newTask != null && !newTask.trim().isEmpty()) {
                        ArrayList<Integer> indices = new ArrayList<>();
                        indices.add(index);
                        ArrayList<String> newTasks = new ArrayList<>();
                        newTasks.add(newTask);
                        Logic.Edit(indices, newTasks);
                        updateTaskList();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a task to edit.");
                }
            }
        });
        buttonPanel.add(EditButton);
// Add the button panel to the left panel and add the left panel to the frame
        leftPanel.add(buttonPanel);
        add(leftPanel, BorderLayout.WEST);
// Center panel for adding new tasks
        JPanel centerPanel = new JPanel();
        JLabel AddLabel = new JLabel("Enter a task:");
        addTaskField = new JTextField(30);
        centerPanel.add(AddLabel);
        centerPanel.add(addTaskField);
        add(centerPanel, BorderLayout.CENTER);
//  Right panel to display the list of tasks
        taskList = new JList<>();
        add(taskList, BorderLayout.EAST);
//  Update the task list to display the current tasks and make the frame visible
        setVisible(true);
    }
//  Method to update the task list display with the current tasks from the Logic class
    private void updateTaskList() {
        ArrayList<String> tasks = Logic.getTasks();
        String[] numberedTasks = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            numberedTasks[i] = (i + 1) + ". " + tasks.get(i);
        }
        taskList.setListData(numberedTasks);
    }
//  Main method to launch the application
    public static void main(String[] args) {
        new GUI();
    }
}