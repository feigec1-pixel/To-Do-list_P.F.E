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


public class GUI extends JFrame {
    private JList<String> taskList;
    private JTextField addTaskField;

    public GUI() {
        setTitle("To do List");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        JButton AddButton = new JButton("Add Task");
        AddButton.addActionListener(new ActionListener() {
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

        JButton RemoveButton = new JButton("Remove Task");
        RemoveButton.addActionListener(new ActionListener() {
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

        JButton EditButton = new JButton("Edit Task");
        EditButton.addActionListener(new ActionListener() {
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

        leftPanel.add(buttonPanel);
        add(leftPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        JLabel AddLabel = new JLabel("Enter a task:");
        addTaskField = new JTextField(30);
        centerPanel.add(AddLabel);
        centerPanel.add(addTaskField);
        add(centerPanel, BorderLayout.CENTER);

        taskList = new JList<>();
        add(taskList, BorderLayout.EAST);

        setVisible(true);
    }

    private void updateTaskList() {
        ArrayList<String> tasks = Logic.getTasks();
        String[] numberedTasks = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            numberedTasks[i] = (i + 1) + ". " + tasks.get(i);
        }
        taskList.setListData(numberedTasks);
    }

    public static void main(String[] args) throws Exception {
        new GUI();
    }
}