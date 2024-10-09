import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class dsapython1 {
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

    public dsapython1() {
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

        questions = new String[][]{
                {"<html><pre>Which keyword is used to define a function in Python?\n</pre></html>", "func", "function", "def", "define"},
                {"<html><pre>What is the correct way to create a list in Python?\n </pre></html>", "(1, 2, 3)", "[1, 2, 3]", "{1, 2, 3}", "&lt;1, 2, 3&gt;"},
                {"<html><pre>Which of the following is a valid dictionary in Python?\n </pre></html>", "{1: 'a', 2: 'b'}", "['a', 'b']", "('a', 'b')", "{a, b}"},
                {"<html><pre>Which of the following methods adds an element to a list in Python?\n </pre></html>", "append()", "insert()", "add()", "extend()"},
                {"<html><pre>Which function is used to get the length of a list in Python?\n </pre></html>", "size()", "len()", "length()", "count()"},
                {"<html><pre>What will be the output of the following code?\n x = [1, 2, 3]\n x.append([4, 5])\n print(x)\n </pre></html>", "[1, 2, 3, 4, 5]", "[1, 2, 3, [4, 5]]", "[1, 2, 3, [4], [5]]", "None of the above"},
                {"<html><pre>Which of the following methods removes the last element from a list in Python?\n </pre></html>", "pop()", "remove()", "delete()", "discard()"},
                {"<html><pre>How do you create a tuple in Python?\n </pre></html>", "[1, 2, 3]", "(1, 2, 3)", "{1, 2, 3}", "&lt;1, 2, 3&gt;"},
                {"<html><pre>Which keyword is used to iterate over elements in Python?\n </pre></html>", "while", "for", "iterate", "loop"},
                {"<html><pre>What is the output of the following code?\n </pre></html>", "15", "510", "5 + 10", "None of the above"},

                {"<html><pre>Which of the following data types is mutable in Python?\n </pre></html>", "tuple", "string", "list", "int"},
                {"<html><pre>Which function converts a string to an integer in Python?\n </pre></html>", "int()", "str()", "chr()", "ord()"},
                {"<html><pre>What is the correct syntax to import a module in Python?\n </pre></html>", "import moduleName", "include moduleName", "require moduleName", "load moduleName"},
                {"<html><pre>Which of the following is a valid Python variable name?\n</pre></html>", "2var", "var_name", "@var", "var-name"},
                {"<html><pre>What is the correct file extension for Python files?\n</pre></html>", ".java", ".py", ".txt", ".cpp"},
                {"<html><pre>Which of the following is the correct syntax for a list comprehension?\n </pre></html>", "[x for x in range(5)]", "(x for x in range(5))", "{x for x in range(5)}", "[x: x in range(5)]"},
                {"<html><pre>Which keyword is used to handle exceptions in Python?\n </pre></html>", "try", "if", "except", "throw"},
                {"<html><pre>Which of the following is used to stop a loop in Python?\n</pre></html>", "return", "break", "continue", "exit"},
                {"<html><pre>What is the output of the following code?\n a = 'hello'\n print(a.upper())\n </pre></html>", "hello", "HELLO", "Hello", "error"},
                {"<html><pre>Which of the following is NOT a Python built-in function?\n</pre></html>", "min()", "max()", "sort()", "len()"},

                {"<html><pre>Which data structure allows duplicate elements in Python?\n</pre></html>", "set", "list", "tuple", "dictionary"},
                {"<html><pre>How do you remove the first element from a list in Python?\n </pre></html>", "list.pop(0)", "list.remove(0)", "list.delete(0)", "list.first()"},
                {"<html><pre>Which method is used to add multiple elements to a list in Python?\n</pre></html>", "append()", "insert()", "extend()", "add()"},
                {"<html><pre>What is the index of the last element in a Python list called myList?\n </pre></html>", "0", "len(myList)", "len(myList) - 1", "last()"},
                {"<html><pre>Which function is used to iterate over elements and their indices in a list?\n </pre></html>", "range()", "enumerate()", "iter()", "index()"},
                {"<html><pre>How do you convert a list to a set in Python?\n </pre></html>", "set(myList)", "list(myList)", "dict(myList)", "tuple(myList)"},
                {"<html><pre>What is the correct way to declare an empty list in Python?\n </pre></html>", "[]", "{}", "()", "&lt;&gt;"},
                {"<html><pre>Which keyword is used to return a value from a function?\n </pre></html>", "return", "output", "yield", "break"},
                {"<html><pre>How do you create a set in Python?\n</pre></html>", "[]", "()", "{}", "set()"},
                {"What is the output of the following code?<br/><pre>String str = \"Hello\";<br/>str = str.concat(\" World\");<br/>System.out.println(str);</pre>", "Hello World", "Hello", "World", "Compilation Error"},

        };

        answers = new String[]{
                "def", "[1, 2, 3]", "{1: 'a', 2: 'b'}", "append()", "len()", "[1, 2, 3, [4, 5]]", "pop()", "(1, 2, 3)", "for", "15",
                "list", "int()", "import moduleName", "var_name", ".py", "[x for x in range(5)]", "try", "break", "HELLO", "sort()",
                "list", "list.pop(0)", "extend()", "len(myList) - 1", "enumerate()", "set(myList)", "[]", "return", "set()","Hello World"
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
        SwingUtilities.invokeLater(() -> new dsapython1());
    }
}
