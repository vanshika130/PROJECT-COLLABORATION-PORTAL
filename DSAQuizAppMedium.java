import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DSAQuizAppMedium {
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

    public DSAQuizAppMedium() {
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
        questions = new String[][]{
                {"<html><pre>What is the time complexity of the following code?\n for (int i = 0; i < n; i++) { \n    for (int j = 0; j < i; j++) { \n        cout << i << \" \" << j; \n    } \n}</pre></html>", "O(n)", "O(n^2)", "O(log n)", "O(n log n"},
                {"<html><pre>Given the following code, what will be the output?\n vector<int> arr = {1, 2, 3, 4, 5};\n arr.insert(arr.begin() + 2, 10);\n for (int i : arr) { \n cout << i << \" \"; \n }</pre></html>", "1 2 10 3 4 5", "1 10 2 3 4 5", "1 2 3 4 10 5", "Compilation Error"},
                {"<html><pre>What does the following function compute?\n int foo(int n) { if (n == 0) return 1; else return n * foo(n - 1); }</pre></html>", "Fibonacci Number", "Factorial of n", "Sum of first n numbers", "None of the above"},
                {"<html><pre>What is the output of the following code?\n int arr[] = {1, 2, 3, 4, 5}; reverse(arr, arr + 5); for (int i : arr) { cout << i << \" \"; }</pre></html>", "5 4 3 2 1", "1 2 3 4 5", "1 5 4 3 2", "Compilation Error"},
                {"<html><pre>Which sorting algorithm does the following code represent?\n for (int i = 0; i < n-1; i++) { \n    for (int j = 0; j < n-i-1; j++) { \n        if (arr[j] > arr[j+1]) swap(arr[j], arr[j+1]); \n    } \n}</pre></html>", "Quick Sort", "Merge Sort", "Bubble Sort", "Selection Sort"},
                {"<html>What is the worst-case time complexity of a binary search algorithm?</html>", "O(n)", "O(log n)", "O(n log n)", "O(n^2)"},
                {"<html>Which data structure is used for the implementation of a queue?</html>", "Stack", "Linked List", "Array", "Heap"},
                {"<html><pre>What is the output of the following code?\n int arr[] = {1, 2, 3, 4, 5}; cout << arr[2];</pre></html>", "1", "2", "3", "4"},
                {"<html>Which function call correctly deletes all elements from a std::vector<int>?</html>", "vec.clear();", "vec.delete();", "vec.erase(vec.begin(), vec.end());", "vec.resize(0);"},
                {"<html><pre>What is the output of the following C++ code?\n string str = \"hello\"; reverse(str.begin(), str.end()); cout << str;</pre></html>", "olleh", "hello", "loleh", "Compilation Error"},
                {"<html>In C++, what does the following snippet represent?\n class Node {\n public: int data; Node* next; };</html>", "Tree Node", "Linked List Node", "Queue Node", "Stack Node"},
                {"<html>What is the best-case time complexity of Insertion Sort?</html>", "O(n^2)", "O(n log n)", "O(n)", "O(log n)"},
                {"<html><pre>What is the output of the following code?\n priority_queue<int> pq;\n pq.push(10);\n pq.push(5);\n pq.push(20);\n cout << pq.top();</pre></html>", "5", "10", "20", "Compilation Error"},
                {"<html>What is the purpose of the push_back function in a vector?</html>", "Add an element at the beginning", "Add an element at the end", "Delete an element from the end", "Remove all elements"},
                {"<html>What is the time complexity of finding the middle element in a linked list (singly linked)?</html>", "O(1)", "O(n)", "O(log n)", "O(n log n)"},
                {"<html><pre>Given the code snippet, which traversal is represented?\n void inorder(Node* node) { \n    if (node == nullptr) return; \n    inorder(node->left); \n    cout << node->data << \" \"; \n    inorder(node->right); \n }</pre></html>", "Preorder", "Postorder", "Inorder", "Level-order"},
                {"<html><pre>What does the following C++ code snippet do?\n vector<int> v = {10, 20, 30, 40, 50};\n v.erase(v.begin() + 2);\n for (int i : v) { cout << i << \" \"; }</pre></html>", "10 20 40 50", "10 20 30 40 50", "10 30 40 50", "20 30 40 50"},
                {"<html>What is the space complexity of a recursive function for calculating factorial?</html>", "O(1)", "O(n)", "O(log n)", "O(n^2)"},
                {"<html><pre>What will be the output of the following code?\n int a = 10, b = 20; swap(a, b); cout << a << \" \" << b;</pre></html>", "20 10", "10 20", "30 30", "Compilation Error"},
                {"<html>In which scenario does a queue follow the Last-In-First-Out (LIFO) principle?</html>", "Always", "Never", "When implemented as a priority queue", "When implemented as a stack"},
                {"<html>Which of the following is true for std::unordered_map?</html>", "Keys are ordered", "Insertion is O(log n)", "Insertion is O(1)", "Allows duplicate keys"},
                {"<html><pre>What is the output of the following C++ code?\n int arr[5] = {10, 20, 30, 40, 50}; cout << sizeof(arr);</pre></html>", "5", "10", "20", "20 or 40 (depends on system architecture)"},
                {"<html>Which algorithm is best suited for finding the shortest path in a weighted graph?</html>", "Breadth-First Search (BFS)", "Depth-First Search (DFS)", "Dijkstra's Algorithm", "Kruskal's Algorithm"},
                {"<html>What is the time complexity of accessing an element in a hash map?</html>", "O(1)", "O(n)", "O(log n)", "O(n log n)"},
                {"<html><pre>Which data structure does the following code implement?\n class MyStack { vector<int> vec;\n public:\n void push(int x) { vec.push_back(x); }\n void pop() { if (!vec.empty()) vec.pop_back(); }\n int top() { return vec.back(); }\n };</pre></html>", "Stack", "Queue", "Heap", "Priority Queue"},
                {"<html><pre>What is the output of the following C++ code?\n string s = \"geeksforgeeks\"; cout << s.substr(5, 3);</pre></html>", "for", "gee", "sfo", "ek"},
                {"<html><pre>What does the following code do?\n stack<int> s; s.push(1); s.push(2); s.pop(); cout << s.top();</pre></html>", "1", "2", "Compilation Error", "Stack is empty"},
                {"<html>Which of the following is not a characteristic of a good hash function?</html>", "Uniform distribution of hash values", "Fast computation", "Highly complex", "Minimizing collisions"},
                {"<html><pre>What will be the output of the following code?\n int a = 5, b = 10; cout << (a == b ? \"Equal\" : \"Not Equal\");</pre></html>", "Equal", "Not Equal", "Compilation Error", "Runtime Error"},
                {"<html>What is the purpose of the 'this' pointer in C++?</html>", "To refer to the current object", "To refer to the previous object", "To refer to a static variable", "To refer to a global variable"},

        };

        answers = new String[]{
                "O(n^2)", "1 2 10 3 4 5", "Factorial of n", "5 4 3 2 1", "Bubble Sort",
                "O(log n)", "Array", "3", "vec.clear();", "olleh", "Linked List Node", "O(n)",
                "20", "Add an element at the end", "O(n)", "Inorder", "10 20 40 50", "O(n)",
                "20 10", "Never", "Insertion is O(1)", "20 or 40 (depends on system architecture)",
                "Dijkstra's Algorithm", "O(1)", "Stack", "for", "1",
                "Highly complex", "Not Equal", "To refer to the current object", "Hello World",
                "Binary Search", "1 2 3 4", "multiset"
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
        SwingUtilities.invokeLater(() -> new DSAQuizAppMedium());
    }
}
