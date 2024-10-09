import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dsajavahard {
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
    private int timerCount = 3000; // 5 minutes
    private Timer timer;

    // Questions and Answers
    private String[][] questions = {
            {"What is the output of the following code?<br/><pre>List<Integer> list = new ArrayList<>();<br/>list.add(1);<br/>list.add(2);<br/>System.out.println(list.get(0) + list.get(1));</pre>", "3", "12", "2", "1"},
            {"What will be the output of this code?<br/><pre>int[] arr = {1, 2, 3, 4};<br/>System.out.println(arr.length);</pre>", "4", "3", "2", "1"},
            {"What is the output of the following code?<br/><pre>Set<String> set = new HashSet<>();<br/>set.add(\"A\");<br/>set.add(\"B\");<br/>System.out.println(set.size());</pre>", "1", "2", "3", "0"},
            {"What will be printed by the following code?<br/><pre>StringBuilder sb = new StringBuilder();<br/>sb.append(\"Hello\").append(\" \").append(\"World\");<br/>System.out.println(sb.toString());</pre>", "Hello World", "HelloWorld", "Hello", "World"},
            {"What is the output of this code?<br/><pre>Map<String, Integer> map = new HashMap<>();<br/>map.put(\"A\", 1);<br/>map.put(\"B\", 2);<br/>System.out.println(map.get(\"A\") + map.get(\"B\"));</pre>", "3", "2", "1", "0"},
            {"What will be the output of the following code?<br/><pre>Queue<Integer> queue = new LinkedList<>();<br/>queue.add(10);<br/>queue.add(20);<br/>System.out.println(queue.poll());</pre>", "10", "20", "0", "null"},
            {"What is the output of the following code?<br/><pre>Stack<Integer> stack = new Stack<>();<br/>stack.push(5);<br/>stack.push(10);<br/>System.out.println(stack.pop());</pre>", "10", "5", "0", "null"},
            {"What will be printed by this code?<br/><pre>int[] arr = {1, 2, 3};<br/>System.out.println(arr[arr.length - 1]);</pre>", "1", "2", "3", "ArrayIndexOutOfBoundsException"},
            {"What is the output of the following code?<br/><pre>List<String> list = new ArrayList<>();<br/>list.add(\"Hello\");<br/>list.add(\"World\");<br/>System.out.println(list.get(1));</pre>", "Hello", "World", "HelloWorld", "Compilation Error"},
            {"What will be the output of this code?<br/><pre>int[] arr = {1, 2, 3, 4};<br/>System.out.println(arr[2] + arr[3]);</pre>", "6", "7", "5", "4"},
            {"What is the output of the following code?<br/><pre>List<Integer> nums = Arrays.asList(1, 2, 3, 4);<br/>System.out.println(nums.size());</pre>", "4", "3", "2", "1"},
            {"What will be printed by the following code?<br/><pre>HashMap<Integer, String> map = new HashMap<>();<br/>map.put(1, \"One\");<br/>map.put(2, \"Two\");<br/>System.out.println(map.get(3));</pre>", "One", "Two", "null", "Compilation Error"},
            {"What is the output of this code?<br/><pre>int[] nums = {1, 2, 3, 4};<br/>for (int n : nums) {<br/>System.out.print(n + \" \");<br/>}</pre>", "1 2 3 4 ", "1 2 3 4", "4 3 2 1", "None of the above"},
            {"What will be the output of the following code?<br/><pre>String[] colors = {\"Red\", \"Green\", \"Blue\"};<br/>System.out.println(colors[1]);</pre>", "Red", "Green", "Blue", "ArrayIndexOutOfBoundsException"},
            {"What will be printed by the following code?<br/><pre>int x = 5;<br/>List<Integer> list = new ArrayList<>();<br/>list.add(x);<br/>System.out.println(list.get(0));</pre>", "5", "10", "0", "None of the above"},
            {"What is the output of the following code?<br/><pre>Map<Integer, Integer> map = new HashMap<>();<br/>map.put(1, 10);<br/>map.put(2, 20);<br/>map.remove(1);<br/>System.out.println(map.size());</pre>", "2", "1", "0", "null"},
            {"What will be the output of the following code?<br/><pre>List<Integer> list = new ArrayList<>();<br/>for (int i = 0; i < 5; i++) {<br/>list.add(i);<br/>}<br/>System.out.println(list.get(3));</pre>", "2", "3", "4", "5"},
            {"What is the output of this code?<br/><pre>int[][] arr = {{1, 2}, {3, 4}};<br/>System.out.println(arr[1][0]);</pre>", "1", "2", "3", "ArrayIndexOutOfBoundsException"},
            {"What will be printed by the following code?<br/><pre>HashSet<String> set = new HashSet<>();<br/>set.add(\"Hello\");<br/>set.add(\"Hello\");<br/>System.out.println(set.size());</pre>", "1", "2", "0", "Compilation Error"},
            {"What is the output of the following code?<br/><pre>Queue<Integer> queue = new LinkedList<>();<br/>queue.offer(1);<br/>queue.offer(2);<br/>System.out.println(queue.peek());</pre>", "1", "2", "0", "null"},
            {"What will be printed by this code?<br/><pre>Stack<String> stack = new Stack<>();<br/>stack.push(\"A\");<br/>stack.push(\"B\");<br/>System.out.println(stack.peek());</pre>", "A", "B", "Compilation Error", "null"},
            {"What is the output of the following code?<br/><pre>List<String> list = new ArrayList<>();<br/>list.add(\"Apple\");<br/>list.add(\"Banana\");<br/>System.out.println(list.get(0).length());</pre>", "5", "6", "7", "Compilation Error"},
            {"What is the output of the following code?<br/><pre>String str = \"Java\";<br/>System.out.println(str.charAt(1));</pre>", "J", "a", "v", "Error"},
            {"What will be printed by the following code?<br/><pre>double a = 5.0 / 2.0;<br/>System.out.println(a);</pre>", "2", "2.5", "5", "Error"},
            {"What is the output of this code?<br/><pre>boolean result = (5 > 3) && (3 < 4);<br/>System.out.println(result);</pre>", "true", "false", "1", "0"},
            {"What will be the output of the following code?<br/><pre>String s1 = \"Hello\";<br/>String s2 = \"Hello\";<br/>System.out.println(s1 == s2);</pre>", "true", "false", "Compilation Error", "Error"},
            {"What is the output of the following code?<br/><pre>int a = 10;<br/>int b = 20;<br/>System.out.println(a > b ? a : b);</pre>", "10", "20", "Compilation Error", "Error"},
            {"What will be printed by this code?<br/><pre>String str = \"Java\";<br/>str += \" Programming\";<br/>System.out.println(str);</pre>", "Java", "Java Programming", "JavaProgramming", "Error"},
            {"What is the output of the following code?<br/><pre>int a = 5;<br/>System.out.println(a++);<br/>System.out.println(++a);</pre>", "5\n6", "6\n7", "5\n7", "Error"},
            {"What will be the output of the following code?<br/><pre>String[] fruits = {\"Apple\", \"Banana\", \"Cherry\"};<br/>System.out.println(fruits[2]);</pre>", "Apple", "Banana", "Cherry", "Error"},

    };

    private String[] answers = {
            "3",                       // Q1: Output of addition
            "4",                       // Q2: Output of array length
            "2",                       // Q3: Output of set size
            "Hello World",             // Q4: Output of StringBuilder
            "3",                       // Q5: Output of map sum
            "10",                      // Q6: Output of queue poll
            "10",                      // Q7: Output of stack pop
            "3",                       // Q8: Output of last index
            "World",                   // Q9: Output of list index
            "7",                       // Q10: Output of array sum
            "4",                       // Q11: Output of list size
            "null",                    // Q12: Output of missing map key
            "1 2 3 4 ",               // Q13: Output of loop
            "Green",                   // Q14: Output of array index
            "5",                       // Q15: Output of list index
            "1",                       // Q16: Output of map size
            "3",                       // Q17: Output of list index
            "3",                       // Q18: Output of multi-dimensional array
            "1",                       // Q19: Output of HashSet size
            "1",                       // Q20: Output of queue peek
            "B",                       // Q21: Output of stack peek
            "5",                       // Q22: Output of string length
            "a",        // What is the output of the following code? (charAt)
            "2.5",     // What will be printed by the following code? (double division)
            "true",    // What is the output of this code? (boolean expression)
            "true",    // What will be the output of the following code? (string comparison)
            "20",      // What is the output of the following code? (ternary operator)
            "Java Programming", // What will be printed by this code? (string concatenation)
            "5\n7",    // What is the output of the following code? (postfix and prefix increment)
            "Cherry"
    };



    public dsajavahard() {
        frame = new JFrame("DSA Hard Level Quiz");
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
        frame.setSize(800, 600);
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
