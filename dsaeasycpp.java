import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class dsaeasycpp {
    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel, timerLabel;
    private JRadioButton[] options;
    private ButtonGroup optionGroup;
    private JButton nextButton, prevButton, submitButton;
    private JPanel buttonPanel;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int totalQuestions = 30;
    private int timerCount = 1200; // 20 minutes
    private Timer timer;

    // Quiz Details
    private final String language = "C++";
    private final String difficultyLevel = "Easy";

    // Questions and Answers
    // Questions and Answers
    private String[][] questions = {
            {"What is the time complexity of inserting an element in a linked list?", "O(1)", "O(n)", "O(log n)", "O(n^2)"},
            {"What is the time complexity of accessing an element in an array?", "O(1)", "O(n)", "O(log n)", "O(n^2)"},
            {"Which of the following data structures uses LIFO (Last In First Out) order?", "Queue", "Stack", "Array", "Linked List"},
            {"What is the average case time complexity of quicksort?", "O(n log n)", "O(n^2)", "O(n)", "O(log n)"},
            {"Which of the following is a stable sorting algorithm?", "Quick Sort", "Merge Sort", "Heap Sort", "Selection Sort"},
            {"What does a binary search tree ensure about its nodes?", "All nodes are unique", "Left child < Parent < Right child", "Height is balanced", "All of the above"},
            {"Which of the following data structures is not linear?", "Array", "Linked List", "Stack", "Binary Tree"},
            {"In which case does a linked list use more memory than an array?", "When the size is small", "When elements are not contiguous", "When elements are sorted", "None of the above"},
            {"What is the output of the following code? <br> <pre>int x = 10; if (x > 5) {<br>std::cout << 'Greater'; } else {<br>std::cout << 'Smaller'; }</pre>", "Greater", "Smaller", "10", "None of the above"},
            {"Which of the following algorithms is used for finding the shortest path in a graph?", "Dijkstra's Algorithm", "Depth First Search", "Breadth First Search", "Quick Sort"},
            {"In which of the following scenarios is a queue used?", "Function call stack", "Undo operations in text editors", "Print queue", "Memory management"},
            {"What is the time complexity of inserting an element in a linked list?", "O(1)", "O(n)", "O(log n)", "O(n^2)"},
            {"Which data structure is used to implement recursion?", "Stack", "Queue", "Array", "Linked List"},
            {"What is the main disadvantage of a linked list compared to an array?", "Memory consumption", "Size", "Ease of use", "Access time"},
            {"Which of the following is a characteristic of a binary search tree?", "All nodes have 2 children", "Left subtree contains nodes with lesser values", "Right subtree contains nodes with lesser values", "It is always balanced"},
            {"What is the worst-case time complexity for searching an element in a binary search tree?", "O(n)", "O(log n)", "O(n log n)", "O(1)"},
            {"Which of the following is not a type of graph traversal?", "Binary Search", "Depth First Search", "Breadth First Search", "Both A and B"},
            {"What is the output of the following code snippet? <br><pre>std::vector<int> v = {1, 2, 3}; std::cout << v.size();</pre>", "1", "2", "3", "3"},
            {"Which of the following statements about heaps is true?", "A heap can be implemented using an array", "Heap is a complete binary tree", "Both A and B", "None of the above"},
            {"What is the time complexity of bubble sort in the worst case?", "O(n)", "O(n log n)", "O(n^2)", "O(log n)"},
            {"Which of the following is a recursive algorithm?", "Binary Search", "Bubble Sort", "Insertion Sort", "Heap Sort"},
            {"In which case is it better to use a linked list over an array?", "When memory is a constraint", "When frequent insertions/deletions are required", "When random access is needed", "None of the above"},
            {"What does it mean if a tree is balanced?", "All leaf nodes are at the same level", "Height difference between left and right subtrees is minimal", "All nodes have two children", "None of the above"},
            {"What is the space complexity of a linked list?", "O(1)", "O(n)", "O(n^2)", "O(log n)"},
            {"What is the result of the following operation on a stack? Push(1), Push(2), Pop(), Push(3)", "1", "2", "3", "0"},
            {"Which of the following is true for a circular linked list?", "Last node points to the first node", "First node points to the last node", "Both A and B", "None of the above"},
            {"What is the time complexity of finding an element in a hash table?", "O(1)", "O(n)", "O(log n)", "O(n log n)"},
            {"Which data structure is best suited for implementing a priority queue?", "Array", "Heap", "Stack", "Linked List"},
            {"In graph theory, what is a cycle?", "A path that starts and ends at the same vertex", "A complete graph", "A connected graph", "None of the above"},
            {"What is the output of the following code snippet? <br><pre>int x = 5; std::cout << ++x;</pre>", "5", "6", "7", "0"}
    };

    private String[] answers = {
            "O(1)", "O(1)", "Stack", "O(n log n)", "Merge Sort",
            "Left child < Parent < Right child", "Binary Tree", "When elements are not contiguous",
            "Greater", "Dijkstra's Algorithm", "Print queue", "O(1)", "Stack",
            "Memory consumption", "Left subtree contains nodes with lesser values",
            "O(n)", "Binary Search", "3", "Both A and B", "O(n^2)",
            "Binary Search", "When frequent insertions/deletions are required", "Height difference between left and right subtrees is minimal",
            "O(n)", "3", "Last node points to the first node", "O(1)", "Heap",
            "A path that starts and ends at the same vertex", "6"
    };


    private Connection connectToDatabase() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/details", "root", "password");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void insertUserScore(String username, int score, String language, String difficultyLevel) {
        try (Connection connection = connectToDatabase()) {
            if (connection != null) {
                String insertQuery = "INSERT INTO user_scores (username, score, language, difficulty_level) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                    statement.setString(1, username);
                    statement.setInt(2, score);
                    statement.setString(3, language);
                    statement.setString(4, difficultyLevel);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public dsaeasycpp() {
        frame = new JFrame("DSA in C++ Easy Level Quiz");
        panel = new JPanel();
        questionLabel = new JLabel();
        timerLabel = new JLabel("Time Left: 20:00", SwingConstants.RIGHT);
        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        nextButton = new JButton("Next");
        prevButton = new JButton("Previous");
        submitButton = new JButton("Submit");
        buttonPanel = new JPanel();

        // Panel settings
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Font settings
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setHorizontalAlignment(JLabel.CENTER);

        // Button settings
        Color buttonColor = new Color(30, 144, 255); // Dodger Blue
        nextButton.setBackground(buttonColor);
        nextButton.setForeground(Color.blue);
        prevButton.setBackground(buttonColor);
        prevButton.setForeground(Color.blue);
        submitButton.setBackground(buttonColor);
        submitButton.setForeground(Color.blue);

        // Action listeners for buttons
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (optionGroup.getSelection() != null) {
                    checkAnswer();
                }
                currentQuestionIndex++;
                if (currentQuestionIndex < totalQuestions) {
                    displayQuestion();
                } else {
                    endQuiz();
                }
            }
        });

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    displayQuestion();
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (optionGroup.getSelection() != null) {
                    checkAnswer();
                }
                endQuiz();
            }
        });

        // Timer setup
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerCount--;
                int minutes = timerCount / 60;
                int seconds = timerCount % 60;
                timerLabel.setText(String.format("Time Left: %02d:%02d", minutes, seconds));
                if (timerCount == 0) {
                    endQuiz();
                }
            }
        });

        // Layout
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);

        panel.add(questionLabel);
        panel.add(timerLabel);
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionGroup.add(options[i]);
            panel.add(options[i]);
        }
        panel.add(buttonPanel);
        frame.add(panel);

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        displayQuestion();
        timer.start();
    }

    private void displayQuestion() {
        // Reset the option selection for each question
        optionGroup.clearSelection();

        // Set the current question
        questionLabel.setText("Q" + (currentQuestionIndex + 1) + ": " + questions[currentQuestionIndex][0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[currentQuestionIndex][i + 1]);
        }
    }

    private void checkAnswer() {
        int selectedOption = -1;
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selectedOption = i;
                break;
            }
        }

        if (selectedOption != -1 && options[selectedOption].getText().equals(answers[currentQuestionIndex])) {
            score++;
        }
    }

    private void endQuiz() {
        timer.stop();
        String username = JOptionPane.showInputDialog(frame, "Enter your username:");
        insertUserScore(username, score, language, difficultyLevel);
        JOptionPane.showMessageDialog(frame, "Quiz Over! Your Score: " + score + "/" + totalQuestions);
        frame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new dsaeasycpp();
            }
        });
    }
}
