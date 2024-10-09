import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class python2 {
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

    public python2() {
        frame = new JFrame("Pyhton Quiz - Medium Level");
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
                {"What is the output of the following code?\n\n" +
                        "def func(x, y=[]):\n\n" +
                        "    y.append(x)\n\n" +
                        "    return y\n\n" +
                        "print(func(1))\n\n" +
                        "print(func(2))\n",
                        "[1]\n[2]",
                        "[1]\n[1, 2]",
                        "[1, 2]\n[1, 2]",
                        "Error"},
                {"What is the result of the following code?\n x = [1, 2, 3]\n y = x\n y.append(4)\n print(x)", "[1, 2, 3]", "[1, 2]", "[1, 2, 3, 4]", "Error"},
                {"How do you create an anonymous function in Python?", "def", "lambda", "func", "anon"},
                {"Which method can be used to convert a list of tuples into a dictionary in Python?", "tuple()", "list()", "dict()", "set()"},
                {"What will be the output of the following code?\n print(bool(0), bool(1), bool(-1))", "True True True", "False True True", "False True False", "False False True"},
                {"How do you open a file for reading in Python?", "open('file.txt', 'w')", "open('file.txt', 'r')", "open('file.txt', 'rw')", "open('file.txt', 'a')"},
                {"Which method is used to get the length of a string in Python?", "len()", "count()", "length()", "size()"},
                {"What is the output of the following code?\n list1 = [1, 2, 3]\n list2 = list1\n list2.append(4)\n print(list1)", "[1, 2, 3]", "[1, 2]", "[1, 2, 3, 4]", "Error"},
                {"How do you handle exceptions in Python?", "try-except", "try-finally", "catch-throw", "catch-finally"},
                {"Which method returns an iterator object?", "next()", "iter()", "yield", "map()"},

                {"Which keyword is used to create a class in Python?", "def", "lambda", "class", "function"},
                {"Which method is called when an object is created in Python?", "__main__", "__class__", "__init__", "__start__"},
                {"What will the following code output?\n class A:\n    def __init__(self):\n        self.x = 10\n obj = A()\n print(obj.x)", "None", "0", "10", "Error"},
                {"What is the purpose of the `super()` function in Python?", "Returns the parent class", "Creates a class", "Creates an instance", "Returns an iterator"},
                {"Which data type in Python allows duplicate elements?", "Set", "List", "Dictionary", "Tuple"},
                {"What does the `zip()` function do?", "Joins two lists", "Combines two or more lists element-wise", "Sorts a list", "Removes duplicates from a list"},
                {"Which method is used to get both the index and value when iterating through a list?", "index()", "enumerate()", "list()", "iterator()"},
                {"How can you check if a key exists in a dictionary?", "dict.has_key(key)", "key in dict", "dict.contains(key)", "dict.exist(key)"},
                {"What is the output of the following code?\n x = [2, 4, 6, 8]\n y = [i**2 for i in x if i > 5]\n print(y)", "[4, 16, 36, 64]", "[36, 64]", "[16, 36]", "Error"},
                {"How can you stop an infinite loop in Python?", "Using `break`", "Using `return`", "Using `exit()`", "Raising an exception"},

                {"What will the following code return?\n list1 = [1, 2, 3]\n result = list(map(lambda x: x + 2, list1))\n print(result)", "[3, 4, 5]", "[2, 3, 4]", "[1, 2, 3]", "Error"},
                {"What is the result of the following code?\n list1 = [5, 6, 7]\n result = [x for x in list1 if x % 2 == 0]\n print(result)", "[6]", "[5, 6, 7]", "[6, 7]", "Error"},
                {"Which function is used to read all lines from a file?", "read()", "readlines()", "input()", "file()"},
                {"How do you create a generator in Python?", "Using `def`", "Using `yield`", "Using `return`", "Using `lambda`"},
                {"Which of the following methods is used to remove an item from a list by value?", "del", "remove", "pop", "discard"},
                {"How do you copy a list in Python?", "copy()", "clone()", "list()", "deepcopy()"},
                {"What does the `enumerate()` function return?", "Iterator with index and value", "List of indices", "Dictionary", "Set of values"},
                {"Which of the following is used to generate a random number?", "random.randint()", "math.random()", "numpy.random()", "random.sample()"},
                {"Which function is used to sort a list in Python?", "sort()", "order()", "arrange()", "shuffle()"},
                {"What is the output of the following code?\n import random\n print(random.randint(1, 10))", "Random float", "Random integer between 1 and 10", "1", "10"}
        };
        answers = new String[]{
                "[1, 2]\n[1, 2]", "[1, 2, 3, 4]", "lambda", "dict()", "False True True",
                "open('file.txt', 'r')", "len()", "[1, 2, 3, 4]", "try-except", "iter()",
                "class", "__init__", "10", "Returns the parent class", "List",
                "Combines two or more lists element-wise", "enumerate()", "key in dict", "[36, 64]", "Using `break`",
                "[3, 4, 5]", "[6]", "readlines()", "Using `yield`", "remove",
                "copy()", "Iterator with index and value", "random.randint()", "sort()", "Random integer between 1 and 10"
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
        SwingUtilities.invokeLater(() -> new dsapython2());
    }
}
