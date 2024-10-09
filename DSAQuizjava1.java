import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DSAQuizjava1 {
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

    public DSAQuizjava1() {
        frame = new JFrame("DSA Quiz - Easy Level");
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
                {"<html><pre>Which of the following is the correct syntax for the main method in Java?\n </pre></html>", "public static void main(String[] args)", "public void main(String args[])", "static public void main(String[] args)", "void main(String[] args)"},
                {"<html><pre>Which keyword is used to create a class in Java?\n </pre></html>", "class", "create", "new", "void"},
                {"<html><pre>What is the default value of an int variable in Java?\n </pre></html>", "0", "null", "undefined", "1"},
                {"<html><pre>Which of the following is NOT a Java primitive type?\n </pre></html>", "int", "boolean", "String", "char"},
                {"<html><pre>Which operator is used to compare two values in Java?\n </pre></html>", "=", "==", "===", "!="},
                {"<html><pre>What is the output of the following code?\n int a = 5;\n int b = 10;\n System.out.println(a + b);\n </pre></html>", "15", "510", "5 + 10", "None of the above"},
                {"<html><pre>Which method is used to find the length of a string in Java?\n </pre></html>", "length()", "size()", "getSize()", "getLength()"},
                {"<html><pre>Which of the following statements is used to create an object in Java?\n </pre></html>", "object obj = new object();", "object obj = new Object();", "Object obj = new object();", "Object obj = new Object();"},
                {"<html><pre>What is the correct way to declare an array in Java?\n </pre></html>", "int[] arr;", "arr[] int;", "int arr[];", "Both A and C"},
                {"<html><pre>Which of the following is used to handle exceptions in Java?\n </pre></html>", "try-catch", "if-else", "switch-case", "for-each"},

                {"<html><pre>What is the correct way to write a comment in Java?\n </pre></html>", "<!-- comment -->", "/* comment */", "// comment", "Both B and C"},
                {"<html><pre>Which keyword is used to inherit a class in Java?\n </pre></html>", "implements", "extends", "inherits", "super"},
                {"<html><pre>What is the size of an int variable in Java?\n </pre></html>", "16 bits", "32 bits", "64 bits", "8 bits"},
                {"<html><pre>Which of the following statements is true about constructors in Java?\n </pre></html>", "Constructors have no return type.", "Constructors can be overloaded.", "Constructors can call other constructors.", "All of the above."},
                {"<html><pre>What is the result of the expression 5 + 10 + '20' in Java?\n </pre></html>", "1520", "152", "20", "15"},
                {"<html><pre>What is the access modifier for a class in Java if no modifier is specified?\n</pre></html>", "public", "private", "protected", "package-private"},

                {"<html><pre>Which of the following is a wrapper class in Java?\n </pre></html>", "int", "Integer", "char", "String"},
                {"<html><pre>Which of the following collections allows duplicate elements?\n </pre></html>", "Set", "List", "Map", "Queue"},
                {"<html><pre>What is the keyword used to create an interface in Java?\n </pre></html>", "interface", "implements", "class", "extends"},
                {"<html><pre>Which of the following is NOT a Java collection framework class?\n </pre></html>", "ArrayList", "HashMap", "LinkedList", "TreeNode"},
                {"<html><pre>What is the return type of the method parseInt() in Java?\n </pre></html>", "int", "Integer", "String", "void"},
                {"<html><pre>Which keyword is used to define a constant variable in Java?\n </pre></html>", "constant", "static", "final", "const"},

                {"<html><pre>Which of the following is a valid method signature in Java?\n </pre></html>", "void myMethod[];", "void myMethod()[];", "int myMethod(int x);", "int myMethod[] (int x);"},
                {"<html><pre>What is the output of the following code?\n System.out.println(10 + 20 + \"30\");\n </pre></html>", "30", "1020", "1030", "1020"},
                {"<html><pre>What does JVM stand for in Java?\n </pre></html>", "Java Variable Manager", "Java Virtual Machine", "Java Visual Machine", "Java Verified Machine"},
                {"<html><pre>Which of the following is used to define a block of code that may throw an exception?\n </pre></html>", "try", "catch", "finally", "throw"},
                {"<html><pre>Which keyword is used to prevent a class from being subclassed in Java?\n </pre></html>", "static", "final", "abstract", "private"},
                {"<html><pre>What does the keyword 'super' refer to in Java?\n </pre></html>", "The current class", "The parent class", "A static method", "A final method"},
                {"<html><pre>Which exception is thrown when an array is accessed with an illegal index in Java?\n </pre></html>", "NullPointerException", "ArrayIndexOutOfBoundsException", "ClassNotFoundException", "IllegalArgumentException"},
                {"<html><pre>Which of the following methods is used to terminate a program in Java?\n </pre></html>", "exit()", "terminate()", "end()", "System.exit()"}

        };

        answers = new String[]{
                "public static void main(String[] args)", "class", "0", "String", "==",
                "15", "length()", "Object obj = new Object();", "Both A and C", "try-catch",
                "Both B and C", "extends", "32 bits", "All of the above.", "1520",
                "package-private", "Integer", "List", "interface", "TreeNode",
                "int", "final", "int myMethod(int x);", "1030", "Java Virtual Machine", "try","final", // Which keyword is used to prevent a class from being subclassed in Java?
                "The parent class", // What does the keyword 'super' refer to in Java?
                "ArrayIndexOutOfBoundsException", // Which exception is thrown when an array is accessed with an illegal index in Java?
                "package","System.exit()"

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
        SwingUtilities.invokeLater(() -> new DSAQuizjava1());
    }
}
