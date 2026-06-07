package studentrecordsystem1gui;
/**
 *
 * @author Huzaifa
 */

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentRecordSystem1GUI{

    static final File STUDENT_FILE = new File("Student.txt");

    static final Color BG_COLOR = new Color(245, 247, 250);
    static final Color CARD_COLOR = new Color(230, 244, 250);
    static final Color PRIMARY_COLOR = new Color(35, 115, 180);
    static final Color DANGER_COLOR = new Color(190, 55, 55);
    static final Color SUCCESS_COLOR = new Color(45, 145, 85);
    static final Font TITLE_FONT = new Font("Tahoma", Font.BOLD, 22);
    static final Font LABEL_FONT = new Font("Tahoma", Font.BOLD, 14);
    static final Font BUTTON_FONT = new Font("Tahoma", Font.BOLD, 13);

    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Record System");
        JPanel container = new JPanel();
        container.setBackground(BG_COLOR);
        container.setLayout(new GridBagLayout());

        JPanel innerpanel = new JPanel();
        innerpanel.setLayout(null);
        innerpanel.setBackground(CARD_COLOR);
        innerpanel.setPreferredSize(new Dimension(440, 520));
        innerpanel.setBorder(BorderFactory.createLineBorder(new Color(190, 210, 220), 2));

        JLabel label = new JLabel("Student Management System", SwingConstants.CENTER);
        label.setFont(new Font("Tahoma", Font.BOLD, 23));
        label.setForeground(new Color(30, 70, 100));
        label.setBounds(20, 25, 400, 50);
        innerpanel.add(label);

        JLabel subLabel = new JLabel("Java Swing | File Handling | CRUD Operations", SwingConstants.CENTER);
        subLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        subLabel.setForeground(new Color(70, 90, 105));
        subLabel.setBounds(20, 70, 400, 25);
        innerpanel.add(subLabel);

        JButton button1 = createMenuButton("Add Student", PRIMARY_COLOR);
        button1.setBounds(130, 125, 180, 42);
        innerpanel.add(button1);

        JButton button2 = createMenuButton("View All Students", PRIMARY_COLOR);
        button2.setBounds(130, 180, 180, 42);
        innerpanel.add(button2);

        JButton button3 = createMenuButton("Search Student", PRIMARY_COLOR);
        button3.setBounds(130, 235, 180, 42);
        innerpanel.add(button3);

        JButton button4 = createMenuButton("Edit Student", SUCCESS_COLOR);
        button4.setBounds(130, 290, 180, 42);
        innerpanel.add(button4);

        JButton button5 = createMenuButton("Delete Student", DANGER_COLOR);
        button5.setBounds(130, 345, 180, 42);
        innerpanel.add(button5);

        JButton button6 = createMenuButton("Exit", new Color(80, 80, 80));
        button6.setBounds(130, 400, 180, 42);
        innerpanel.add(button6);

        container.add(innerpanel);
        frame.add(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 620);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        button1.addActionListener(e -> openAddStudentFrame());
        button2.addActionListener(e -> openViewStudentsFrame());
        button3.addActionListener(e -> openSearchStudentFrame());
        button4.addActionListener(e -> openEditStudentFrame());
        button5.addActionListener(e -> openDeleteStudentFrame());
        button6.addActionListener(e -> System.exit(0));
    }

    static JButton createMenuButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }

    static JLabel createLabel(String text, int x, int y, int w, int h) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setFont(LABEL_FONT);
        return label;
    }

    static JTextField createTextField(int x, int y, int w, int h) {
        JTextField field = new JTextField();
        field.setBounds(x, y, w, h);
        field.setFont(new Font("Tahoma", Font.PLAIN, 14));
        return field;
    }

    static JFrame createWindow(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(BG_COLOR);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    static JPanel createPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBackground(CARD_COLOR);
        panel.setBorder(BorderFactory.createLineBorder(new Color(190, 210, 220), 2));
        return panel;
    }

    static ArrayList<String[]> readStudents() {
        ArrayList<String[]> students = new ArrayList<>();

        if (!STUDENT_FILE.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 3) {
                    students.add(new String[]{data[0], data[1], data[2]});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }

        return students;
    }

    static void writeStudents(ArrayList<String[]> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE))) {
            for (String[] student : students) {
                writer.write(student[0] + "," + student[1] + "," + student[2]);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing file: " + e.getMessage());
        }
    }

    static boolean isDuplicateID(String id) {
        ArrayList<String[]> students = readStudents();

        for (String[] student : students) {
            if (student[1].equals(id)) {
                return true;
            }
        }

        return false;
    }

    static boolean isValidCGPA(String cgpaText) {
        try {
            double cgpa = Double.parseDouble(cgpaText);

            if (cgpa < 0 || cgpa > 4.0) {
                JOptionPane.showMessageDialog(null, "CGPA must be between 0.0 and 4.0.");
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid CGPA. Please enter a numeric value.");
            return false;
        }
    }

    static void openAddStudentFrame() {
        JFrame addframe = createWindow("Add Student", 520, 480);
        JPanel pan = createPanel(470, 400);

        JLabel titlelabel = new JLabel("Add Student Details", SwingConstants.CENTER);
        titlelabel.setBounds(50, 30, 370, 45);
        titlelabel.setFont(TITLE_FONT);
        titlelabel.setForeground(new Color(30, 70, 100));

        JLabel nameLabel = createLabel("Enter Name:", 80, 110, 120, 30);
        JTextField nameField = createTextField(200, 110, 180, 28);

        JLabel idLabel = createLabel("Enter ID:", 80, 160, 120, 30);
        JTextField idField = createTextField(200, 160, 180, 28);

        JLabel cgpaLabel = createLabel("Enter CGPA:", 80, 210, 120, 30);
        JTextField cgpaField = createTextField(200, 210, 180, 28);

        JButton submitButton = createMenuButton("Save Student", SUCCESS_COLOR);
        submitButton.setBounds(185, 280, 140, 38);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String id = idField.getText().trim();
            String cgpa = cgpaField.getText().trim();

            if (name.isEmpty() || id.isEmpty() || cgpa.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name, ID and CGPA cannot be empty.");
                return;
            }

            if (!isValidCGPA(cgpa)) {
                return;
            }

            if (isDuplicateID(id)) {
                JOptionPane.showMessageDialog(null, "ID already exists. Please enter a different ID.");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE, true))) {
                writer.write(name + "," + id + "," + cgpa);
                writer.newLine();

                JOptionPane.showMessageDialog(null, "Student saved successfully!");

                nameField.setText("");
                idField.setText("");
                cgpaField.setText("");
                nameField.requestFocus();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        });

        pan.add(titlelabel);
        pan.add(nameLabel);
        pan.add(nameField);
        pan.add(idLabel);
        pan.add(idField);
        pan.add(cgpaLabel);
        pan.add(cgpaField);
        pan.add(submitButton);

        addframe.add(pan);
        addframe.setVisible(true);
    }

    static void openViewStudentsFrame() {
        JFrame showframe = new JFrame("All Student Details");
        showframe.setLayout(new BorderLayout());
        showframe.getContentPane().setBackground(BG_COLOR);

        ArrayList<String[]> students = readStudents();

        String[] columns = {"Name", "ID", "CGPA"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (String[] student : students) {
            model.addRow(new Object[]{student[0], student[1], student[2]});
        }

        JTable table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(table);
        showframe.add(scroll, BorderLayout.CENTER);

        JLabel title = new JLabel("Student Records", SwingConstants.CENTER);
        title.setFont(TITLE_FONT);
        title.setForeground(new Color(30, 70, 100));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        showframe.add(title, BorderLayout.NORTH);

        showframe.setSize(600, 420);
        showframe.setLocationRelativeTo(null);
        showframe.setVisible(true);
    }

    static void openSearchStudentFrame() {
        JFrame searchframe = createWindow("Search Student By ID", 520, 480);
        JPanel panel = createPanel(470, 380);

        JLabel title = new JLabel("Search Student By ID", SwingConstants.CENTER);
        title.setBounds(50, 25, 370, 45);
        title.setFont(TITLE_FONT);
        title.setForeground(new Color(30, 70, 100));

        JLabel idLabel = createLabel("Enter ID:", 80, 100, 120, 30);
        JTextField idField = createTextField(200, 100, 180, 28);

        JButton searchButton = createMenuButton("Search", PRIMARY_COLOR);
        searchButton.setBounds(200, 150, 120, 38);

        JLabel resultTitle = new JLabel("Student Details", SwingConstants.CENTER);
        resultTitle.setBounds(50, 210, 370, 30);
        resultTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        resultTitle.setVisible(false);

        JLabel nameResult = createLabel("Name:", 100, 255, 300, 30);
        JLabel idResult = createLabel("ID:", 100, 285, 300, 30);
        JLabel cgpaResult = createLabel("CGPA:", 100, 315, 300, 30);

        nameResult.setVisible(false);
        idResult.setVisible(false);
        cgpaResult.setVisible(false);

        searchButton.addActionListener(e -> {
            String searchID = idField.getText().trim();

            if (searchID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter student ID.");
                return;
            }

            boolean found = false;
            ArrayList<String[]> students = readStudents();

            for (String[] student : students) {
                if (student[1].equals(searchID)) {
                    found = true;

                    resultTitle.setVisible(true);
                    nameResult.setVisible(true);
                    idResult.setVisible(true);
                    cgpaResult.setVisible(true);

                    nameResult.setText("Student Name: " + student[0]);
                    idResult.setText("Student ID: " + student[1]);
                    cgpaResult.setText("Student CGPA: " + student[2]);
                    break;
                }
            }

            if (!found) {
                resultTitle.setVisible(false);
                nameResult.setVisible(false);
                idResult.setVisible(false);
                cgpaResult.setVisible(false);

                JOptionPane.showMessageDialog(null, "Student not found.");
            }
        });

        panel.add(title);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(searchButton);
        panel.add(resultTitle);
        panel.add(nameResult);
        panel.add(idResult);
        panel.add(cgpaResult);

        searchframe.add(panel);
        searchframe.setVisible(true);
    }

    static void openEditStudentFrame() {
        JFrame editframe = createWindow("Edit Student By ID", 550, 540);
        JPanel panel = createPanel(500, 450);

        JLabel title = new JLabel("Edit Student Details", SwingConstants.CENTER);
        title.setBounds(50, 25, 400, 45);
        title.setFont(TITLE_FONT);
        title.setForeground(new Color(30, 70, 100));

        JLabel searchLabel = createLabel("Enter ID:", 70, 95, 120, 30);
        JTextField searchField = createTextField(190, 95, 190, 28);

        JButton searchButton = createMenuButton("Search", PRIMARY_COLOR);
        searchButton.setBounds(390, 92, 85, 34);

        JLabel nameLabel = createLabel("New Name:", 70, 170, 120, 30);
        JTextField nameField = createTextField(190, 170, 210, 28);

        JLabel idLabel = createLabel("Student ID:", 70, 220, 120, 30);
        JTextField idField = createTextField(190, 220, 210, 28);
        idField.setEditable(false);
        idField.setBackground(new Color(230, 230, 230));

        JLabel cgpaLabel = createLabel("New CGPA:", 70, 270, 120, 30);
        JTextField cgpaField = createTextField(190, 270, 210, 28);

        JButton updateButton = createMenuButton("Update Student", SUCCESS_COLOR);
        updateButton.setBounds(185, 340, 150, 40);

        nameLabel.setVisible(false);
        nameField.setVisible(false);
        idLabel.setVisible(false);
        idField.setVisible(false);
        cgpaLabel.setVisible(false);
        cgpaField.setVisible(false);
        updateButton.setVisible(false);

        final int[] foundIndex = {-1};

        searchButton.addActionListener(e -> {
            String searchID = searchField.getText().trim();

            if (searchID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter student ID.");
                return;
            }

            ArrayList<String[]> students = readStudents();
            foundIndex[0] = -1;

            for (int i = 0; i < students.size(); i++) {
                if (students.get(i)[1].equals(searchID)) {
                    foundIndex[0] = i;

                    nameField.setText(students.get(i)[0]);
                    idField.setText(students.get(i)[1]);
                    cgpaField.setText(students.get(i)[2]);

                    nameLabel.setVisible(true);
                    nameField.setVisible(true);
                    idLabel.setVisible(true);
                    idField.setVisible(true);
                    cgpaLabel.setVisible(true);
                    cgpaField.setVisible(true);
                    updateButton.setVisible(true);

                    return;
                }
            }

            JOptionPane.showMessageDialog(null, "Student not found.");
            nameLabel.setVisible(false);
            nameField.setVisible(false);
            idLabel.setVisible(false);
            idField.setVisible(false);
            cgpaLabel.setVisible(false);
            cgpaField.setVisible(false);
            updateButton.setVisible(false);
        });

        updateButton.addActionListener(e -> {
            if (foundIndex[0] == -1) {
                JOptionPane.showMessageDialog(null, "Please search a student first.");
                return;
            }

            String newName = nameField.getText().trim();
            String newID = idField.getText().trim();
            String newCGPA = cgpaField.getText().trim();

            if (newName.isEmpty() || newCGPA.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name and CGPA cannot be empty.");
                return;
            }

            if (!isValidCGPA(newCGPA)) {
                return;
            }

            ArrayList<String[]> students = readStudents();

            students.set(foundIndex[0], new String[]{newName, newID, newCGPA});
            writeStudents(students);

            JOptionPane.showMessageDialog(null, "Student updated successfully.");

            searchField.setText("");
            nameField.setText("");
            idField.setText("");
            cgpaField.setText("");

            foundIndex[0] = -1;

            nameLabel.setVisible(false);
            nameField.setVisible(false);
            idLabel.setVisible(false);
            idField.setVisible(false);
            cgpaLabel.setVisible(false);
            cgpaField.setVisible(false);
            updateButton.setVisible(false);
        });

        panel.add(title);
        panel.add(searchLabel);
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(cgpaLabel);
        panel.add(cgpaField);
        panel.add(updateButton);

        editframe.add(panel);
        editframe.setVisible(true);
    }

    static void openDeleteStudentFrame() {
        JFrame deleteframe = createWindow("Delete Student By ID", 520, 500);
        JPanel panel = createPanel(470, 400);

        JLabel title = new JLabel("Delete Student By ID", SwingConstants.CENTER);
        title.setBounds(50, 25, 370, 45);
        title.setFont(TITLE_FONT);
        title.setForeground(new Color(30, 70, 100));

        JLabel idLabel = createLabel("Enter ID:", 80, 100, 120, 30);
        JTextField idField = createTextField(200, 100, 180, 28);

        JButton searchButton = createMenuButton("Search", PRIMARY_COLOR);
        searchButton.setBounds(175, 150, 120, 38);

        JLabel resultTitle = new JLabel("Student Details", SwingConstants.CENTER);
        resultTitle.setBounds(50, 220, 370, 30);
        resultTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        resultTitle.setVisible(false);

        JLabel nameResult = createLabel("Name:", 100, 260, 300, 30);
        JLabel idResult = createLabel("ID:", 100, 290, 300, 30);
        JLabel cgpaResult = createLabel("CGPA:", 100, 320, 300, 30);

        JButton deleteButton = createMenuButton("Delete", DANGER_COLOR);
        deleteButton.setBounds(175, 355, 120, 38);

        nameResult.setVisible(false);
        idResult.setVisible(false);
        cgpaResult.setVisible(false);
        deleteButton.setVisible(false);

        searchButton.addActionListener(e -> {
            String searchID = idField.getText().trim();

            if (searchID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter student ID.");
                return;
            }

            boolean found = false;
            ArrayList<String[]> students = readStudents();

            for (String[] student : students) {
                if (student[1].equals(searchID)) {
                    found = true;

                    resultTitle.setVisible(true);
                    nameResult.setVisible(true);
                    idResult.setVisible(true);
                    cgpaResult.setVisible(true);
                    deleteButton.setVisible(true);

                    nameResult.setText("Student Name: " + student[0]);
                    idResult.setText("Student ID: " + student[1]);
                    cgpaResult.setText("Student CGPA: " + student[2]);
                    break;
                }
            }

            if (!found) {
                resultTitle.setVisible(false);
                nameResult.setVisible(false);
                idResult.setVisible(false);
                cgpaResult.setVisible(false);
                deleteButton.setVisible(false);

                JOptionPane.showMessageDialog(null, "Student not found.");
            }
        });

        deleteButton.addActionListener(e -> {
            String deleteID = idField.getText().trim();

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this student?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            ArrayList<String[]> students = readStudents();
            boolean deleted = false;

            for (int i = 0; i < students.size(); i++) {
                if (students.get(i)[1].equals(deleteID)) {
                    students.remove(i);
                    deleted = true;
                    break;
                }
            }

            if (deleted) {
                writeStudents(students);
                JOptionPane.showMessageDialog(null, "Student deleted successfully.");

                idField.setText("");
                resultTitle.setVisible(false);
                nameResult.setVisible(false);
                idResult.setVisible(false);
                cgpaResult.setVisible(false);
                deleteButton.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Student not found.");
            }
        });

        panel.add(title);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(searchButton);
        panel.add(resultTitle);
        panel.add(nameResult);
        panel.add(idResult);
        panel.add(cgpaResult);
        panel.add(deleteButton);

        deleteframe.add(panel);
        deleteframe.setVisible(true);
    }
}
