import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class python1 {
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

    public python1() {
        frame = new JFrame("Python Quiz - Easy Level");
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
                {"<html><pre>Which symbol is used for comments in Python?\n </pre></html>", "//", "#", "<!-- -->", ";"},
                {"<html><pre>What will be the output of the following code?\n print(2 ** 3)\n </pre></html>", "6", "8", "9", "4"},
                {"<html><pre>Which of the following is the correct syntax for defining a function in Python?\n </pre></html>", "def myFunction():", "func myFunction():", "function myFunction():", "define myFunction():"},
                {"<html><pre>What is the correct file extension for Python files?\n </pre></html>", ".py", ".pyt", ".pyth", ".pt"},
                {"<html><pre>Which of the following is used to terminate a loop in Python?\n </pre></html>", "exit", "break", "stop", "quit"},
                {"<html><pre>Which of the following will check if two variables a and b have the same value?\n </pre></html>", "a == b", "a = b", "a != b", "a equals b"},
                {"<html><pre>How can you convert a string '123' to an integer in Python?\n </pre></html>", "int('123')", "convert('123')", "str_to_int('123')", "str(123)"},
                {"<html><pre>Which function is used to display the output in Python?\n </pre></html>", "print()", "output()", "display()", "show()"},
                {"<html><pre>What will be the output of the following code?\n print('Hello ' + 'World')\n </pre></html>", "Hello World", "Hello", "World", "HelloWorld"},
                {"<html><pre>Which operator is used for division in Python?\n </pre></html>", "/", "//", "%", "&"},

                {"<html><pre>How do you write a conditional statement in Python?\n </pre></html>", "if x == 5:", "if x = 5 then:", "if (x == 5):", "if x = 5;"},
                {"<html><pre>What is the result of the following operation?\n 10 % 3\n </pre></html>", "1", "2", "3", "0"},
                {"<html><pre>Which of the following is used to create a new line in a string in Python?\n </pre></html>", "\\t", "\\n", "\\a", "\\r"},
                {"<html><pre>How do you check the type of a variable in Python?\n </pre></html>", "checktype(x)", "typeof(x)", "type(x)", "vartype(x)"},
                {"<html><pre>Which of the following is used to get input from the user in Python?\n </pre></html>", "input()", "scan()", "getInput()", "read()"},
                {"<html><pre>What is the output of the following code?\n print(5 == 5)\n </pre></html>", "True", "False", "Error", "5"},
                {"<html><pre>Which of the following is the correct way to start a comment in Python?\n </pre></html>", "<!--", "//", "##", "#"},
                {"<html><pre>How do you raise 5 to the power of 3 in Python?\n </pre></html>", "5^3", "5**3", "5^^3", "pow(5, 3)"},
                {"<html><pre>Which keyword is used to define a function in Python?\n </pre></html>", "function", "def", "func", "lambda"},
                {"<html><pre>Which of the following will check if 'x' is not equal to 5 in Python?\n </pre></html>", "x != 5", "x = 5", "x <> 5", "x == 5"},

                {"<html><pre>Which operator is used for exponentiation in Python?\n </pre></html>", "^", "**", "//", "%%"},
                {"<html><pre>What will be the output of the following code?\n print(10 // 3)\n </pre></html>", "3", "3.33", "3.0", "0"},
                {"<html><pre>Which function can be used to convert an integer to a string in Python?\n </pre></html>", "str()", "int()", "to_string()", "convert()"},
                {"<html><pre>How can you check if a number is even in Python?\n </pre></html>", "num % 2 == 0", "num / 2 == 0", "num // 2 == 0", "num & 2 == 0"},
                {"<html><pre>Which keyword is used to return a value from a function in Python?\n </pre></html>", "return", "output", "yield", "give"},
                {"<html><pre>Which method can be used to convert a string to lowercase in Python?\n </pre></html>", "lower()", "toLower()", "lowercase()", "to_lower()"},
                {"<html><pre>What will be the output of the following code?\n print(10 / 2)\n </pre></html>", "5.0", "5", "5.00", "5.1"},
                {"<html><pre>How do you write a multi-line comment in Python?\n </pre></html>", "/* comment */", "<!-- comment -->", "''' comment '''", "# comment"},
                {"<html><pre>Which built-in function can be used to find the maximum value in Python?\n </pre></html>", "max()", "maximum()", "largest()", "find_max()"},
                {"<html><pre>What is the correct way to import a module in Python?\n </pre></html>", "import module_name", "include module_name", "using module_name", "require module_name"}

        };

        answers = new String[]{
                "#", "8", "def myFunction():", ".py", "break", "a == b", "int('123')", "print()", "Hello World", "/",
                "if x == 5:", "1", "\\n", "type(x)", "input()", "True", "#", "5**3", "def", "x != 5",
                "**", "3", "str()", "num % 2 == 0", "return", "lower()", "5.0", "import module_name"
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
        SwingUtilities.invokeLater(() -> new python1());
    }
}
