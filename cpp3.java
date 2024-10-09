import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cpp3 {
    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel, timerLabel;
    private JRadioButton[] options;
    private ButtonGroup optionGroup;
    private JButton nextButton, prevButton, submitButton;
    private JPanel buttonPanel; // Declare buttonPanel here
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int totalQuestions = 30;
    private int timerCount = 3000; // 20 minutes
    private Timer timer;

    // Questions and Answers
    private String[][] questions = {
            // 1. Output of basic C++ code
            {"What is the output of the following code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    cout << 5 + 3 * 2;<br/>    return 0;<br/>}</pre>", "11", "16", "13", "None of the above"},

            // 2. Output of conditional statement
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    int x = 10;<br/>    if (x > 5) cout << \"Greater\";<br/>    else cout << \"Smaller\";<br/>    return 0;<br/>}</pre>", "Greater", "Smaller", "None of the above", "Error"},

            // 3. Output of loop
            {"What is the output of this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    for (int i = 0; i < 3; i++) cout << i;<br/>    return 0;<br/>}</pre>", "012", "123", "0123", "None of the above"},

            // 4. Output of switch-case
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    int x = 2;<br/>    switch(x) {<br/>        case 1: cout << \"One\"; break;<br/>        case 2: cout << \"Two\"; break;<br/>        default: cout << \"Default\";<br/>    }<br/>    return 0;<br/>}</pre>", "Two", "One", "Default", "None of the above"},

            // 5. Output of function
            {"What is the output of this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>void display() {<br/>    cout << \"Hello\";<br/>} <br/>int main() {<br/>    display();<br/>    return 0;<br/>}</pre>", "Hello", "Hi", "None of the above", "Error"},

            // 6. Output of array access
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    int arr[3] = {1, 2, 3};<br/>    cout << arr[1];<br/>    return 0;<br/>}</pre>", "2", "1", "3", "None of the above"},

            // 7. Output of pointer
            {"What is the output of the following code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    int a = 5;<br/>    int *p = &a;<br/>    cout << *p;<br/>    return 0;<br/>}</pre>", "5", "a", "Error", "None of the above"},

            // 8. Output of strings
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    string s = \"Hello\";<br/>    cout << s.length();<br/>    return 0;<br/>}</pre>", "5", "6", "Hello", "None of the above"},

            // 9. Output of mathematical operation
            {"What will be the output of the following code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    double a = 5.0/2.0;<br/>    cout << a;<br/>    return 0;<br/>}</pre>", "2.5", "2", "3", "None of the above"},

            // 10. Output of character
            {"What is the output of the following code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    char ch = 'A';<br/>    cout << ch;<br/>    return 0;<br/>}</pre>", "A", "B", "Error", "None of the above"},

            // 11. Output of while loop
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    int i = 0;<br/>    while(i < 3) {<br/>        cout << i;<br/>        i++;<br/>    }<br/>    return 0;<br/>}</pre>", "012", "123", "0123", "None of the above"},

            // 12. Output of nested loop
            {"What is the output of this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    for (int i = 0; i < 2; i++) {<br/>        for (int j = 0; j < 2; j++) cout << i+j;<br/>    }<br/>    return 0;<br/>}</pre>", "000112", "112", "012", "None of the above"},

            // 13. Output of continue statement
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    for (int i = 0; i < 3; i++) {<br/>        if (i == 1) continue;<br/>        cout << i;<br/>    }<br/>    return 0;<br/>}</pre>", "02", "012", "123", "None of the above"},

            // 14. Output of break statement
            {"What is the output of this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    for (int i = 0; i < 3; i++) {<br/>        if (i == 1) break;<br/>        cout << i;<br/>    }<br/>    return 0;<br/>}</pre>", "0", "01", "012", "None of the above"},

            // 15. Output of default case in switch
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    int x = 3;<br/>    switch(x) {<br/>        case 1: cout << \"One\"; break;<br/>        case 2: cout << \"Two\"; break;<br/>        default: cout << \"Default\";<br/>    }<br/>    return 0;<br/>}</pre>", "Default", "One", "Two", "None of the above"},

            // 16. Output of complex expression
            {"What is the output of the following code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    cout << (5 > 3 && 2 < 4);<br/>    return 0;<br/>}</pre>", "1", "0", "True", "False"},

            // 17. Output of logical operators
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    bool a = true, b = false;<br/>    cout << (a || b);<br/>    return 0;<br/>}</pre>", "1", "0", "True", "False"},

            // 18. Output of cin statement
            {"What is the output of the following code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    int x;<br/>    cin >> x;<br/>    cout << x;<br/>    return 0;<br/>}</pre>", "Depends on input", "0", "Error", "None of the above"},

            // 19. Output of float division
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    cout << 7 / 2;<br/>    return 0;<br/>}</pre>", "3", "3.5", "4", "None of the above"},

            // 20. Output of bitwise operator
            {"What is the output of the following code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    int x = 5; // 0101<br/>    cout << (x << 1);<br/>    return 0;<br/>}</pre>", "10", "5", "20", "None of the above"},

            // 21. Output of character arithmetic
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    char ch = 'A';<br/>    cout << ch + 1;<br/>    return 0;<br/>}</pre>", "66", "B", "A", "None of the above"},

            // 22. Output of function overloading
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>void display(int x) { cout << \"Int: \" << x; }<br/>void display(double y) { cout << \"Double: \" << y; }<br/>int main() {<br/>    display(5);<br/>    display(5.5);<br/>    return 0;<br/>}</pre>", "Int: 5Double: 5.5", "Int: 5, Double: 5.5", "5, 5.5", "None of the above"},

            // 23. Output of struct
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>struct Point {<br/>    int x;<br/>    int y;<br/>};<br/>int main() {<br/>    Point p = {10, 20};<br/>    cout << p.x << p.y;<br/>    return 0;<br/>}</pre>", "1020", "2010", "None of the above", "Error"},

            // 24. Output of vector
            {"What is the output of this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    int arr[3] = {1, 2, 3};<br/>    cout << arr[2];<br/>    return 0;<br/>}</pre>", "3", "1", "2", "None of the above"},

            // 25. Output of return value
            {"What is the output of the following code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int getNumber() {<br/>    return 42;<br/>} <br/>int main() {<br/>    cout << getNumber();<br/>    return 0;<br/>}</pre>", "42", "0", "Error", "None of the above"},

            // 26. Output of reference variable
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    int x = 10;<br/>    int &y = x;<br/>    cout << y;<br/>    return 0;<br/>}</pre>", "10", "Error", "None of the above", "10.0"},

            // 27. Output of pre-increment
            {"What is the output of the following code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    int x = 5;<br/>    cout << ++x;<br/>    return 0;<br/>}</pre>", "6", "5", "7", "None of the above"},

            // 28. Output of post-increment
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    int x = 5;<br/>    cout << x++;<br/>    return 0;<br/>}</pre>", "5", "6", "7", "None of the above"},

            // 29. Output of constant variable
            {"What will be printed by this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    const int x = 10;<br/>    cout << x;<br/>    return 0;<br/>}</pre>", "10", "Error", "None of the above", "0"},

            // 30. Output of float to int conversion
            {"What is the output of this code?<br/><pre>#include <iostream><br/>using namespace std;<br/>int main() {<br/>    float f = 3.14;<br/>    cout << (int)f;<br/>    return 0;<br/>}</pre>", "3", "3.14", "4", "None of the above"}
    };


    private String[] answers = {
            "11",          // 1. What will be the output of the following code?
            "Greater",     // 2. What will be the output of the following code?
            "012",         // 3. What will be printed by this code?
            "Two",         // 4. What will be printed by this code?
            "Hello",       // 5. What will be printed by this code?
            "2",           // 6. What will be printed by this code?
            "5",           // 7. What will be the output of the following code?
            "5",           // 8. What will be printed by this code?
            "2.5",         // 9. What will be the output of the following code?
            "A",           // 10. What will be printed by this code?
            "012",         // 11. What will be printed by this code?
            "00",          // 12. What is the output of the following code?
            "0",           // 13. What will be printed by this code?
            "012345",      // 14. What will be printed by this code?
            "Error",       // 15. What will be printed by this code?
            "42",          // 16. What will be printed by this code?
            "Hello",       // 17. What is the output of the following code?
            "10",          // 18. What will be printed by this code?
            "5",           // 19. What will be printed by this code?
            "Hello World", // 20. What is the output of the following code?
            "3",           // 21. What will be the output of the following code?
            "0",           // 22. What will be printed by this code?
            "Hello, World!", // 23. What will be printed by this code?
            "Error",       // 24. What will be printed by this code?
            "10",          // 25. What will be printed by this code?
            "Bark",        // 26. What will be printed by this code?
            "Meow",        // 27. What will be printed by this code?
            "5",           // 28. What will be printed by this code?
            "3",           // 29. What is the output of the following code?
            "Thread running" // 30. What will be printed by this code?
    };




    public cpp3() {
        frame = new JFrame("C++ Hard Level Quiz");
        panel = new JPanel();
        questionLabel = new JLabel();
        timerLabel = new JLabel("Time Left: 50:00", SwingConstants.RIGHT);
        options = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        nextButton = new JButton("Next");
        prevButton = new JButton("Previous");
        submitButton = new JButton("Submit");
        buttonPanel = new JPanel(); // Initialize buttonPanel here

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

        // Layout settings
        frame.setLayout(new BorderLayout());
        frame.add(timerLabel, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        panel.add(questionLabel);

        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            optionGroup.add(options[i]);
            panel.add(options[i]);
        }

        // Create button panel
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Initialize frame settings
        frame.setSize(800, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        displayQuestion();
        startTimer();
    }

    private void displayQuestion() {
        // Set question text, ensuring HTML is rendered properly
        questionLabel.setText("<html><h2>Q" + (currentQuestionIndex + 1) + ":</h2>" + questions[currentQuestionIndex][0] + "</html>");
        optionGroup.clearSelection();
        for (int i = 0; i < options.length; i++) {
            options[i].setText(questions[currentQuestionIndex][i + 1]);
        }

        // Show the submit button only on the last question
        if (currentQuestionIndex == totalQuestions - 1) {
            nextButton.setVisible(false);
            buttonPanel.add(submitButton);
            submitButton.setVisible(true);
        } else {
            nextButton.setVisible(true);
            submitButton.setVisible(false);
        }

        frame.revalidate();
        frame.repaint();
    }

    private void checkAnswer() {
        String selectedAnswer = "";
        for (JRadioButton option : options) {
            if (option.isSelected()) {
                selectedAnswer = option.getText();
            }
        }
        if (selectedAnswer.equals(answers[currentQuestionIndex])) {
            score++;
        }
    }

    private void endQuiz() {
        timer.stop();
        JOptionPane.showMessageDialog(frame, "Quiz Over! Your Score: " + score + "/" + totalQuestions);
        frame.dispose();
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerCount--;
                int minutes = timerCount / 60;
                int seconds = timerCount % 60;
                timerLabel.setText(String.format("Time Left: %02d:%02d", minutes, seconds));
                if (timerCount <= 0) {
                    timer.stop();
                    endQuiz();
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DSAQuizApp::new);
    }
}
