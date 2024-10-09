import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class dsajava2 {
    private JFrame frame;
    private JLabel questionLabel, timerLabel;
    private JRadioButton[] options;
    private ButtonGroup optionGroup;
    private JButton nextButton, prevButton, submitButton;
    private String[][] questions;
    private String[] answers;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private Timer timer;
    private int timerCount = 1800; // 15 minutes
    private int totalQuestions = 30;

    public dsajava2() {
        frame = new JFrame("DSA Quiz - Medium Level");
        questionLabel = new JLabel("<html><pre></pre></html>");
        timerLabel = new JLabel("Time Left: 30:00");
        timerLabel.setForeground(Color.RED); // Timer color is red
        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        nextButton = new JButton("Next");
        prevButton = new JButton("Previous");
        submitButton = new JButton("Submit");

        // Set button colors to blue
        nextButton.setBackground(Color.BLUE);
        nextButton.setForeground(Color.BLUE);
        prevButton.setBackground(Color.BLUE);
        prevButton.setForeground(Color.BLUE);
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.BLUE);

        // Questions and answers
        // Questions and answers for hard level
        // New questions and answers for hard level
        // New questions and answers for hard level
        // Easy level Java questions and answers
        questions = new String[][]{
                {"What is the time complexity of accessing an element in an ArrayList?", "O(1)", "O(log n)", "O(n)", "O(n^2)"},
                {"Which data structure is implemented using a FIFO (First In, First Out) principle?", "Stack", "Queue", "Heap", "HashMap"},
                {"What is the worst-case time complexity of QuickSort?", "O(n)", "O(log n)", "O(n^2)", "O(n log n)"},
                {"What is the space complexity of Merge Sort?", "O(1)", "O(n)", "O(log n)", "O(n log n)"},
                {"Which data structure uses a LIFO (Last In, First Out) principle?", "Queue", "Stack", "Linked List", "Binary Tree"},
                {"What is the purpose of the `hashCode()` method in Java?", "Finds the length of a string", "Generates a hash value for objects", "Compares two strings", "Creates a new object"},
                {"What is the best-case time complexity of Bubble Sort?", "O(n)", "O(n log n)", "O(n^2)", "O(log n)"},
                {"Which Java collection allows duplicate elements?", "Set", "Map", "List", "HashMap"},
                {"What is the output of the following code?\n int[] arr = {1, 2, 3, 4, 5}; Arrays.sort(arr); System.out.println(arr[2]);", "1", "2", "3", "4"},
                {"What is the primary difference between a HashMap and a TreeMap?", "HashMap is ordered, TreeMap is unordered", "HashMap is unordered, TreeMap is ordered", "Both are ordered", "Both are unordered"},

                {"What is the time complexity of searching in a binary search tree (BST) in the best case?", "O(1)", "O(n)", "O(log n)", "O(n^2)"},
                {"Which sorting algorithm is based on divide and conquer?", "Insertion Sort", "Bubble Sort", "Merge Sort", "Selection Sort"},
                {"What data structure is used for implementing Depth-First Search (DFS)?", "Queue", "Stack", "Heap", "HashMap"},
                {"Which of the following is not a balanced binary search tree?", "AVL Tree", "Red-Black Tree", "Splay Tree", "Binary Search Tree (BST)"},
                {"What is the average time complexity for search, insert, and delete operations in a hash table?", "O(1)", "O(log n)", "O(n)", "O(n log n)"},
                {"What is the time complexity of inserting a node at the head of a singly linked list?", "O(1)", "O(n)", "O(log n)", "O(n^2)"},
                {"Which algorithm is best suited for finding the shortest path in an unweighted graph?", "Breadth-First Search (BFS)", "Depth-First Search (DFS)", "Dijkstra's Algorithm", "Bellman-Ford Algorithm"},
                {"What is the time complexity of removing an element from the end of an ArrayList?", "O(1)", "O(log n)", "O(n)", "O(n log n)"},
                {"Which data structure is used for implementing a priority queue?", "Stack", "Heap", "Queue", "HashMap"},
                {"Which of the following is a self-balancing binary search tree?", "Splay Tree", "AVL Tree", "Binary Search Tree (BST)", "Heap"},

                {"What is the worst-case time complexity of searching in a hash table with collisions?", "O(1)", "O(n)", "O(log n)", "O(n^2)"},
                {"What is the output of the following code?\n int[] arr = {10, 20, 30}; arr[1] = arr[0] + arr[2]; System.out.println(arr[1]);", "10", "30", "40", "50"},
                {"Which algorithm is commonly used for cycle detection in a graph?", "Kruskal’s Algorithm", "Prim’s Algorithm", "Floyd-Warshall Algorithm", "Tarjan’s Algorithm"},
                {"What is the time complexity of inserting an element into a Binary Search Tree (BST) in the average case?", "O(1)", "O(n)", "O(log n)", "O(n^2)"},
                {"Which traversal technique visits all nodes in a binary tree level by level?", "Inorder", "Preorder", "Postorder", "Level-order"},
                {"What is the best data structure for implementing a LRU (Least Recently Used) cache?", "Array", "Linked List", "HashMap with LinkedList", "Heap"},
                {"Which of the following algorithms is not used for sorting?", "Merge Sort", "Quick Sort", "Dijkstra’s Algorithm", "Heap Sort"},
                {"What is the output of the following code?\n List<Integer> list = Arrays.asList(10, 20, 30); list.add(40);", "Compilation Error", "10 20 30 40", "10 40 30", "10 30 40"},
                {"Which of the following operations is not supported by an ArrayList?", "Add an element at the end", "Remove an element from the middle", "Sort elements", "Insert an element at the beginning"},
                {"What is the primary difference between a Linked List and an Array?", "Linked List has a fixed size", "Array has a fixed size", "Array supports dynamic resizing", "Linked List elements are stored contiguously"}
        };

        answers = new String[]{
                "O(1)", "Queue", "O(n^2)", "O(n)", "Stack",
                "Generates a hash value for objects", "O(n)", "List", "3", "HashMap is unordered, TreeMap is ordered",
                "O(1)", "Merge Sort", "Stack", "Binary Search Tree (BST)", "O(1)",
                "O(1)", "Breadth-First Search (BFS)", "O(1)", "Heap", "AVL Tree",
                "O(n)", "40", "Tarjan’s Algorithm", "O(log n)", "Level-order",
                "HashMap with LinkedList", "Dijkstra’s Algorithm", "Compilation Error", "Insert an element at the beginning", "Array has a fixed size"
        };






        setupUI();
        startTimer();
    }

    private void setupUI() {
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(5, 1));
        JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel navigationPanel = new JPanel(new FlowLayout());

        // Timer
        timerPanel.add(timerLabel);
        frame.add(timerPanel, BorderLayout.NORTH);

        // Question and options
        mainPanel.add(questionLabel);
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            optionGroup.add(options[i]);
            mainPanel.add(options[i]);
        }

        // Navigation Buttons
        navigationPanel.add(prevButton);
        navigationPanel.add(nextButton);
        navigationPanel.add(submitButton);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(navigationPanel, BorderLayout.SOUTH);

        displayQuestion(currentQuestionIndex);

        prevButton.addActionListener(e -> prevQuestion());
        nextButton.addActionListener(e -> nextQuestion());
        submitButton.addActionListener(e -> submitQuiz());

        frame.setSize(800, 900);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initially, hide the previous and submit buttons
        prevButton.setVisible(false);
        submitButton.setVisible(false);
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timerCount > 0) {
                    timerCount--;
                    int minutes = timerCount / 60;
                    int seconds = timerCount % 60;
                    timerLabel.setText(String.format("Time Left: %02d:%02d", minutes, seconds));
                } else {
                    ((Timer) e.getSource()).stop();
                    submitQuiz();
                }
            }
        });
        timer.start();
    }

    private void displayQuestion(int questionIndex) {
        questionLabel.setText("<html> <h2> Q" + (currentQuestionIndex + 1) + ":</h2>" + questions[currentQuestionIndex][0] + "</html> ");
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[questionIndex][i + 1]);
        }
        optionGroup.clearSelection();

        // Show or hide navigation buttons based on the current question
        prevButton.setVisible(currentQuestionIndex > 0);
        nextButton.setVisible(currentQuestionIndex < totalQuestions - 1);
        submitButton.setVisible(currentQuestionIndex == totalQuestions - 1);
    }

    private void nextQuestion() {
        if (currentQuestionIndex < totalQuestions - 1) {
            checkAnswer();
            currentQuestionIndex++;
            displayQuestion(currentQuestionIndex);
        }
    }

    private void prevQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            displayQuestion(currentQuestionIndex);
        }
    }

    private void checkAnswer() {
        String selectedAnswer = null;
        for (JRadioButton option : options) {
            if (option.isSelected()) {
                selectedAnswer = option.getText();
                break;
            }
        }

        if (selectedAnswer != null && selectedAnswer.equals(answers[currentQuestionIndex])) {
            score++;
        }
    }

    private void submitQuiz() {
        checkAnswer();
        timer.stop();
        JOptionPane.showMessageDialog(frame, "Quiz Completed! Your score: " + score + "/" + totalQuestions);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new dsajava2());
    }
}
