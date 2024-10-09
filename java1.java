import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class java1 {
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
    private int timerCount = 1200; // 15 minutes
    private int totalQuestions = 30;

    public java1() {
        frame = new JFrame("Java Quiz - Easy Level");
        questionLabel = new JLabel("<html><pre></pre></html>");
        timerLabel = new JLabel("Time Left: 20:00");
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
                {"<html><pre>Which symbol is used for comments in Java?\n </pre></html>", "//", "#", "<!-- -->", ";"},
                {"<html><pre>What will be the output of the following code?\n System.out.println(2 + 3);\n </pre></html>", "5", "23", "Error", "2 + 3"},
                {"<html><pre>Which of the following is the correct syntax for defining a method in Java?\n  </pre></html>", "void myMethod() {}", "myMethod() {} void", "def myMethod() {}", "function myMethod() {}"},
                {"<html><pre>What is the correct file extension for Java files?\n </pre></html>", ".jav", ".java", ".class", ".jv"},
                {"<html><pre>Which of the following is used to terminate a loop in Java?\n </pre></html>", "exit", "break", "stop", "quit"},
                {"<html><pre>Which of the following is used to create an object in Java?\n </pre></html>", "new Object();", "create Object();", "Object.create();", "make Object();"},
                {"<html><pre>How can you convert a string '123' to an integer in Java?\n </pre></html>", "Integer.parseInt('123')", "convert('123')", "str_to_int('123')", "Integer('123')"},
                {"<html><pre>Which method is used to display output in Java?\n </pre></html>", "display()", "output()", "System.out.println()", "print()"},
                {"<html><pre>What will be the output of the following code?\n System.out.println('Hello ' + 'World');\n </pre></html>", "Hello World", "Hello", "World", "HelloWorld"},
                {"<html><pre>Which operator is used for division in Java?\n </pre></html>", "/", "//", "%", "&"},

                {"<html><pre>How do you write a conditional statement in Java?\n </pre></html>", "if (x == 5) {", "if x = 5 then {", "if (x == 5) then {", "if x = 5;"},
                {"<html><pre>What is the result of the following operation?\n 10 % 3\n </pre></html>", "1", "2", "3", "0"},
                {"<html><pre>Which of the following is used to create a new line in a string in Java?\n </pre></html>", "\\t", "\\n", "\\a", "\\r"},
                {"<html><pre>How do you check the type of a variable in Java?\n </pre></html>", "checktype(x)", "typeof(x)", "instanceof x", "x.getClass()"},
                {"<html><pre>Which of the following is used to get input from the user in Java?\n </pre></html>", "Scanner scanner = new Scanner(System.in);", "input();", "getInput();", "read();"},
                {"<html><pre>What is the output of the following code?\n System.out.println(5 == 5);\n </pre></html>", "true", "false", "Error", "5"},
                {"<html><pre>Which of the following is the correct way to start a comment in Java?\n </pre></html>", "<!--", "//", "##", "#"},
                {"<html><pre>How do you raise 5 to the power of 3 in Java?\n </pre></html>", "Math.pow(5, 3)", "5^3", "5**3", "pow(5, 3)"},
                {"<html><pre>Which keyword is used to define a method in Java?\n </pre></html>", "function", "def", "func", "void"},
                {"<html><pre>Which of the following will check if 'x' is not equal to 5 in Java?\n </pre></html>", "x != 5", "x = 5", "x <> 5", "x == 5"},

                {"<html><pre>Which operator is used for exponentiation in Java?\n </pre></html>", "^", "**", "Math.pow()", "%%"},
                {"<html><pre>What will be the output of the following code?\n System.out.println(10 / 2);\n </pre></html>", "5.0", "5", "5.00", "5.1"},
                {"<html><pre>How do you write a multi-line comment in Java?\n </pre></html>", "/* comment */", "<!-- comment -->", "''' comment '''", "# comment"},
                {"<html><pre>Which built-in function can be used to find the maximum value in Java?\n </pre></html>", "Math.max()", "maximum()", "largest()", "find_max()"},
                {"<html><pre>Which of the following is used to declare a variable in Java?\n </pre></html>", "var x;", "x : int;", "int x;", "declare x as int;"},
                {"<html><pre>What will be the output of the following code?\n System.out.println(15 / 4);\n </pre></html>", "3.75", "3", "4", "3.0"},
                {"<html><pre>Which keyword is used to create a subclass in Java?\n </pre></html>", "inherits", "extends", "implements", "subclass"},
                {"<html><pre>Which of the following is a valid way to create an array in Java?\n </pre></html>", "int[] arr = new int[5];", "int arr[5];", "array int arr = new int[5];", "int arr = new int[5];"},
                {"<html><pre>How do you access the first element of an array named 'arr'?\n </pre></html>", "arr[0]", "arr{0}", "arr(0)", "arr.0"},
                {"<html><pre>Which loop is guaranteed to execute at least once in Java?\n </pre></html>", "for loop", "while loop", "do-while loop", "foreach loop"},
        };

        answers = new String[]{
                "//", "5", "void myMethod() {}", ".java", "break", "new Object();", "Integer.parseInt('123')", "System.out.println()", "Hello World", "/",
                "if (x == 5) {", "1", "\\n", "instanceof x", "Scanner scanner = new Scanner(System.in);", "true", "//", "Math.pow(5, 3)", "void", "x != 5",
                "Math.pow()", "5", "5.0", "/* comment */", "Math.max()","int x;", "3", "extends", "int[] arr = new int[5];", "arr[0]", "do-while loop",""
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
        SwingUtilities.invokeLater(() -> new java1());
    }
}
