import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class cpp2 {
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

    public cpp2() {
        frame = new JFrame("C++ Quiz - Medium Level");
        questionLabel = new JLabel("<html><pre></pre></html>");
        timerLabel = new JLabel("Time Left: 15:00");
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
                {"<html><pre>Which of the following is the correct way to declare a pointer in C++?\n</pre></html>", "int *p;", "int p*;", "*int p;", "int p;"},
                {"<html><pre>What is the output of the following code?\n cout << 5 + 2 * 3;\n </pre></html>", "11", "21", "8", "9"},
                {"<html><pre>Which of the following is used to handle exceptions in C++?\n </pre></html>", "try-catch", "exception-handling", "handle-exception", "try-catch-finally"},
                {"<html><pre>What is the size of an int in C++?\n </pre></html>", "2 bytes", "4 bytes", "8 bytes", "Depends on the system"},
                {"<html><pre>Which operator is used for accessing members of a class in C++?\n </pre></html>", ". (dot)", "-> (arrow)", ":: (scope resolution)", "All of the above"},
                {"<html><pre>What is the purpose of the 'const' keyword in C++?\n </pre></html>", "To declare a variable as constant", "To declare a constant pointer", "To declare a constant function", "All of the above"},
                {"<html><pre>How do you declare a function that returns a pointer in C++?\n </pre></html>", "int *function();", "function() *int;", "int function*();", "*int function();"},
                {"<html><pre>What will be the output of the following code?\n cout << (5 == 5);\n </pre></html>", "true", "1", "0", "false"},
                {"<html><pre>Which of the following is NOT a valid C++ loop?\n </pre></html>", "for", "while", "do-while", "repeat-until"},
                {"<html><pre>What is the correct way to initialize a string in C++?\n </pre></html>", "string str = 'Hello';", "string str = \"Hello\";", "string str = Hello;", "string str = `Hello`;"},
                {"<html><pre>Which of the following is the correct syntax for defining a class in C++?\n </pre></html>", "class MyClass {};", "class MyClass;", "MyClass class {};", "class MyClass: {};"},
                {"<html><pre>How do you dynamically allocate an array in C++?\n </pre></html>", "int *arr = new int[5];", "int arr = new int[5];", "int *arr = malloc(5);", "int arr[5] = new int[];"},
                {"<html><pre>Which of the following is used to include libraries in C++?\n </pre></html>", "#import", "#include", "#lib", "#define"},
                {"<html><pre>What does the 'delete' operator do in C++?\n </pre></html>", "Deallocates memory", "Deletes a variable", "Removes a function", "None of the above"},
                {"<html><pre>Which keyword is used to define a class member function outside the class definition in C++?\n </pre></html>", "class", "public", "private", "scope resolution operator (::)"},
                {"<html><pre>How do you access a base class's member function in a derived class?\n</pre></html>", "base_class::member_function();", "derived_class::member_function();", "member_function();", "class::member_function();"},
                {"<html><pre>Which of the following is a feature of Object-Oriented Programming in C++?\n A. Inheritance\n B. Polymorphism\n C. Encapsulation\n D. All of the above\n</pre></html>", "Inheritance", "Polymorphism", "Encapsulation", "All of the above"},
                {"<html><pre>What is the purpose of the 'new' keyword in C++?\n A. To allocate memory dynamically\n B. To declare a new variable\n C. To create a new class\n D. To initialize a pointer\n</pre></html>", "To allocate memory dynamically", "To declare a new variable", "To create a new class", "To initialize a pointer"},
                {"<html><pre>Which of the following is not a valid access modifier in C++?\n A. public\n B. private\n C. protected\n D. internal\n</pre></html>", "public", "private", "protected", "internal"},
                {"<html><pre>What is the output of the following code?\n cout << sizeof(int);\n A. 2\n B. 4\n C. 8\n D. Depends on the system\n</pre></html>", "2", "4", "8", "Depends on the system"},
                {"<html><pre>Which of the following statements is true about virtual functions in C++?\n A. Virtual functions cannot be overridden.\n B. Virtual functions allow dynamic polymorphism.\n C. Virtual functions cannot be declared in base classes.\n D. Virtual functions must be declared static.\n</pre></html>", "Virtual functions cannot be overridden.", "Virtual functions allow dynamic polymorphism.", "Virtual functions cannot be declared in base classes.", "Virtual functions must be declared static."},
                {"<html><pre>What is the difference between struct and class in C++?\n A. Members of a struct are private by default.\n B. Members of a class are public by default.\n C. Members of a struct are public by default.\n D. There is no difference.\n</pre></html>", "Members of a struct are private by default.", "Members of a class are public by default.", "Members of a struct are public by default.", "There is no difference."},
                {"<html><pre>What is a destructor in C++?\n A. A special function called when an object is destroyed.\n B. A function used to delete pointers.\n C. A function used to free memory manually.\n D. A function used to initialize objects.\n</pre></html>", "A special function called when an object is destroyed.", "A function used to delete pointers.", "A function used to free memory manually.", "A function used to initialize objects."},
                {"<html><pre>Which of the following is correct about operator overloading in C++?\n A. You can only overload arithmetic operators.\n B. You cannot overload the assignment operator.\n C. You can overload most operators.\n D. You cannot overload comparison operators.\n</pre></html>", "You can only overload arithmetic operators.", "You cannot overload the assignment operator.", "You can overload most operators.", "You cannot overload comparison operators."},
                {"<html><pre>What is the output of the following code?\n cout << 10 / 3;\n A. 3.333\n B. 3\n C. 3.0\n D. Depends on the compiler\n</pre></html>", "3.333", "3", "3.0", "Depends on the compiler"},
                {"<html><pre>Which of the following is not a valid type in C++?\n A. float\n B. int\n C. long\n D. real\n</pre></html>", "float", "int", "long", "real"},
                {"<html><pre>How can you create a constant pointer in C++?\n A. int *const p;\n B. const int *p;\n C. const p *int;\n D. Both A and B\n</pre></html>", "int *const p;", "const int *p;", "const p *int;", "Both A and B"},
                {"<html><pre>Which of the following operators cannot be overloaded in C++?\n A. ++ (increment)\n B. == (equality)\n C. :: (scope resolution)\n D. + (addition)\n</pre></html>", "++ (increment)", "== (equality)", ":: (scope resolution)", "+ (addition)"},
                {"<html><pre>Which keyword is used to inherit a class in C++?\n A. extends\n B. inherits\n C. super\n D. public\n</pre></html>", "extends", "inherits", "super", "public"},
                {"<html><pre>Which of the following is true about friend functions in C++?\n A. Friend functions can access private members of a class.\n B. Friend functions cannot be overloaded.\n C. Friend functions must be defined inside the class.\n D. Friend functions must be public.\n</pre></html>", "Friend functions can access private members of a class.", "Friend functions cannot be overloaded.", "Friend functions must be defined inside the class.", "Friend functions must be public."}
        };

        answers = new String[]{
                "int *p;", "11", "try-catch", "4 bytes", "All of the above", "To declare a variable as constant", "int *function();", "1", "repeat-until", "string str = \"Hello\";",
                "class MyClass {};", "int *arr = new int[5];", "#include", "Deallocates memory", "scope resolution operator (::)", "base_class::member_function();","All of the above",
                "To allocate memory dynamically",
                "internal",
                "Depends on the system",
                "Virtual functions allow dynamic polymorphism",
                "Members of a struct are public by default",
                "A special function called when an object is destroyed.",
                "You can overload most operators.",
                "3",
                "real",
                "Both A and B",
                ":: (scope resolution)",
                "public",
                "Friend functions can access private members of a class."
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
        SwingUtilities.invokeLater(() -> new java2());
    }
}
