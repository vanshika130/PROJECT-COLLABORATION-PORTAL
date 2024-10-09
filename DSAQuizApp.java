import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DSAQuizApp {
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

            {"What is the output of the following C++ code?<br/><pre>#include &lt;iostream&gt;<br/>using namespace std;<br/>int main() {<br/>int a = 5, b = 10;<br/>cout &lt;&lt; (a &lt; b ? a : b);<br/>return 0;<br/>}</pre>", "5", "10", "Error", "None of the above"},
            {"What is the time complexity of the following code?<br/><pre>int binarySearch(int arr[], int x, int l, int r) {<br/>while (l &lt;= r) {<br/>int m = l + (r - l) / 2;<br/>if (arr[m] == x)<br/>return m;<br/>if (arr[m] &lt; x)<br/>l = m + 1;<br/>else<br/>r = m - 1;<br/>}<br/>return -1;<br/>}</pre>", "O(n)", "O(log n)", "O(n log n)", "O(1)"},
            {"What is the output of this C++ code?<br/><pre>#include &lt;iostream&gt;<br/>using namespace std;<br/>void merge(int arr[], int l, int m, int r) {<br/>int i, j, k;<br/>for (i = l; i &lt;= m; i++)<br/>cout &lt;&lt; arr[i] &lt;&lt; ' ';<br/>}<br/>int main() {<br/>int arr[] = {12, 11, 13, 5, 6, 7};<br/>merge(arr, 0, 2, 5);<br/>return 0;<br/>}</pre>", "12 11 13", "11 12 13", "12 11 13 5 6 7", "None of the above"},
            {"Which algorithm is the most suitable for finding a minimum spanning tree?<br/><pre>struct Edge { int src, dest, weight; };<br/>struct Graph { int V, E;<br/>struct Edge* edge;<br/>};<br/>Graph* createGraph(int V, int E) {<br/>Graph* graph = new Graph;<br/>graph->V = V;<br/>graph->E = E;<br/>graph->edge = new Edge[E];<br/>return graph;<br/>}</pre>", "Kruskal's Algorithm", "Prim's Algorithm", "Dijkstra's Algorithm", "Both A and B"},
            {"What is the output of the following C++ code?<br/><pre>#include &lt;iostream&gt;<br/>using namespace std;<br/>void quickSort(int arr[], int low, int high) {<br/>if (low &lt; high) {<br/>int pi = partition(arr, low, high);<br/>quickSort(arr, low, pi - 1);<br/>quickSort(arr, pi + 1, high);<br/>}<br/>}<br/>int main() {<br/>int arr[] = {10, 7, 8, 9, 1, 5};<br/>int n = sizeof(arr)/sizeof(arr[0]);<br/>quickSort(arr, 0, n - 1);<br/>return 0;<br/>}</pre>", "1 5 7 8 9 10", "10 9 8 7 5 1", "5 7 8 9 10", "None of the above"},
            {"What does this function do?<br/><pre>int mystery(int n) {<br/>if (n == 0) return 0;<br/>return mystery(n - 1) + n;<br/>}</pre>", "Returns the factorial of n", "Returns the sum of first n natural numbers", "Returns the Fibonacci of n", "None of the above"},
            {"What is the time complexity of merge sort algorithm?<br/><pre>void merge(int arr[], int l, int m, int r) {<br/>int n1 = m - l + 1;<br/>int n2 = r - m;<br/>int L[n1], R[n2];<br/>for (int i = 0; i < n1; i++)<br/>L[i] = arr[l + i];<br/>for (int j = 0; j < n2; j++)<br/>R[j] = arr[m + 1 + j];<br/>}</pre>", "O(n)", "O(n log n)", "O(n^2)", "O(log n)"},
            {"What is the output of the following C++ code?<br/><pre>#include &lt;iostream&gt;<br/>using namespace std;<br/>void swap(int &a, int &b) {<br/>int temp = a;<br/>a = b;<br/>b = temp;<br/>}<br/>int main() {<br/>int x = 10, y = 20;<br/>swap(x, y);<br/>cout &lt;&lt; x &lt;&lt; ' ' &lt;&lt; y;<br/>return 0;<br/>}</pre>", "10 20", "20 10", "Error", "None of the above"},
            {"What is the space complexity of binary search algorithm?<br/><pre>int binarySearch(int arr[], int x, int l, int r) {<br/>while (l &lt;= r) {<br/>int m = l + (r - l) / 2;<br/>if (arr[m] == x)<br/>return m;<br/>if (arr[m] &lt; x)<br/>l = m + 1;<br/>else<br/>r = m - 1;<br/>}<br/>return -1;<br/>}</pre>", "O(1)", "O(log n)", "O(n)", "O(n log n)"},
            {"What is the output of the following C++ code?<br/><pre>#include &lt;iostream&gt;<br/>using namespace std;<br/>int main() {<br/>int a = 6, b = 12;<br/>cout &lt;&lt; (a &lt; b ? a : b);<br/>return 0;<br/>}</pre>", "6", "12", "Error", "None of the above"},
            {"What is the best case time complexity of quicksort algorithm?<br/><pre>void quickSort(int arr[], int low, int high) {<br/>if (low &lt; high) {<br/>int pi = partition(arr, low, high);<br/>quickSort(arr, low, pi - 1);<br/>quickSort(arr, pi + 1, high);<br/>}</pre>", "O(n)", "O(n log n)", "O(log n)", "O(1)"},
            {"What is the output of this C++ code?<br/><pre>#include &lt;iostream&gt;<br/>using namespace std;<br/>int factorial(int n) {<br/>return (n == 1 || n == 0) ? 1 : n * factorial(n - 1);<br/>}<br/>int main() {<br/>int n = 5;<br/>cout &lt;&lt; factorial(n);<br/>return 0;<br/>}</pre>", "5", "10", "120", "None of the above"},
            {"What is the average case time complexity of merge sort?<br/><pre>void merge(int arr[], int l, int m, int r) {<br/>int n1 = m - l + 1;<br/>int n2 = r - m;<br/>int L[n1], R[n2];<br/>}</pre>", "O(n log n)", "O(n^2)", "O(n)", "O(log n)"},
            {"What is the output of the following code?<br/><pre>#include &lt;iostream&gt;<br/>using namespace std;<br/>int main() {<br/>int n = 10;<br/>cout &lt;&lt; n*(n+1)/2;<br/>return 0;<br/>}</pre>", "45", "55", "100", "None of the above"},
            {"What is the output of the following C++ code?<br/><pre>#include &lt;iostream&gt;<br/>using namespace std;<br/>int arr[] = {10, 20, 30};<br/>for (int i = 0; i < 3; i++)<br/>cout &lt;&lt; arr[i] &lt;&lt; ' ';<br/>return 0;<br/>}</pre>", "10 20 30", "10 30 20", "20 10 30", "None of the above"},
            {"What is the time complexity of the following function?<br/><pre>int sum(int arr[], int n) {<br/>int total = 0;<br/>for (int i = 0; i < n; i++)<br/>total += arr[i];<br/>return total;<br/>}</pre>", "O(n)", "O(n^2)", "O(log n)", "O(1)"},
            {"What is the output of the following C++ code?<br/><pre>#include &lt;iostream&gt;<br/>using namespace std;<br/>int main() {<br/>int x = 1;<br/>if (x == 1)<br/>cout &lt;&lt; 'X is 1';<br/>else<br/>cout &lt;&lt; 'X is not 1';<br/>return 0;<br/>}</pre>", "X is 1", "X is not 1", "Error", "None of the above"},
            {"Which of the following is a stable sorting algorithm?<br/><pre>void bubbleSort(int arr[], int n) {<br/>for (int i = 0; i < n - 1; i++)<br/>for (int j = 0; j < n - i - 1; j++)<br/>if (arr[j] &gt; arr[j + 1])<br/>swap(arr[j], arr[j + 1]);<br/>}</pre>", "Merge Sort", "Quick Sort", "Heap Sort", "Selection Sort"},
            {"What is the space complexity of the following code?<br/><pre>void selectionSort(int arr[], int n) {<br/>for (int i = 0; i < n - 1; i++) {<br/>int minIndex = i;<br/>for (int j = i + 1; j < n; j++)<br/>if (arr[j] &lt; arr[minIndex])<br/>minIndex = j;<br/>swap(arr[minIndex], arr[i]);<br/>}<br/>}</pre>", "O(1)", "O(n)", "O(n^2)", "O(log n)"},
            {"What is the time complexity of searching an element in a sorted array using linear search?<br/><pre>int linearSearch(int arr[], int n, int x) {<br/>for (int i = 0; i < n; i++)<br/>if (arr[i] == x)<br/>return i;<br/>return -1;<br/>}</pre>", "O(n)", "O(log n)", "O(1)", "O(n^2)"},
            {"What is the output of the following C++ code?<br/><pre>#include &lt;iostream&gt;<br/>using namespace std;<br/>int main() {<br/>int a = 0, b = 5;<br/>cout &lt;&lt; (a &gt;&gt; b);<br/>return 0;<br/>}</pre>", "0", "5", "Error", "None of the above"},
            {"What is the best case time complexity of binary search?<br/><pre>int binarySearch(int arr[], int x, int l, int r) {<br/>if (l &gt;= r)<br/>return -1;<br/>int m = l + (r - l) / 2;<br/>if (arr[m] == x)<br/>return m;<br/>if (arr[m] &lt; x)<br/>return binarySearch(arr, x, m + 1, r);<br/>return binarySearch(arr, x, l, m - 1);<br/>}</pre>", "O(1)", "O(log n)", "O(n)", "O(n log n"},
            {"What is the output of the following C++ code?<br/><pre>#include &lt;iostream&gt;<br/>using namespace std;<br/>int main() {<br/>for (int i = 1; i <= 5; i++)<br/>cout &lt;&lt; i * i &lt;&lt; ' ';<br/>return 0;<br/>}</pre>", "1 4 9 16 25", "0 1 4 9 16", "1 2 3 4 5", "Error"},
            {"What is the time complexity of the following C++ function?<br/><pre>void printNumbers(int n) {<br/>for (int i = 1; i &lt;= n; i++)<br/>cout &lt;&lt; i &lt;&lt; ' ';<br/>}</pre>", "O(n)", "O(n^2)", "O(log n)", "O(1)"},
            {"Which data structure is used to implement a LRU cache?<br/><pre>class LRUCache {<br/>public:<br/>    LRUCache(int capacity);<br/>    int get(int key);<br/>    void put(int key, int value);<br/>};<br/></pre>", "HashMap and Doubly Linked List", "Array", "Stack", "Queue"},
            {"What is the time complexity of inserting an element in an array?<br/><pre>void insert(int arr[], int n, int key) {<br/>arr[n] = key;<br/>}</pre>", "O(n)", "O(1)", "O(log n)", "O(n^2)"},
            {"What programming technique is used in the following function?<br/><pre>int factorial(int n) {<br/>if (n == 0) return 1;<br/>return n * factorial(n - 1);<br/>}</pre>", "Recursion", "Iteration", "Dynamic Programming", "Greedy"},
            {"What is the output of the following code snippet?<br/><pre>int main() {<br/>int a = 5;<br/>int b = a++ + ++a;<br/>cout &lt;&lt; b;<br/>return 0;<br/>}</pre>", "2", "10", "12", "Error"},
            {"In the context of multiple-choice questions, which option is considered correct?<br/><pre>Which of the following is a valid variable name?<br/>A. variable_name<br/>B. 1variable<br/>C. variable-name<br/>D. #variable</pre>", "A", "B", "C", "D"},
            {"Which statement is true regarding hash tables?<br/><pre>Hash tables provide constant time complexity for search operations in average cases.</pre>", "True", "False", "Depends on the implementation", "None of the above"}

    };

    private String[] answers = {
            "5",                     // Q1: Output of the given code snippet
            "O(log n)",             // Q2: Time complexity of binary search
            "12 11 13",             // Q3: Output of the given code snippet
            "Both A and B",         // Q4: Output of the given code snippet
            "1 5 7 8 9 10",         // Q5: Output of the given code snippet
            "Returns the sum of first n natural numbers", // Q6: Description of the function
            "O(n log n)",           // Q7: Time complexity of merge sort
            "20 10",                // Q8: Output of the given code snippet
            "O(1)",                 // Q9: Space complexity of the given function
            "X is 1",               // Q10: Value of X after executing the code
            "Merge Sort",           // Q11: Sorting algorithm with a stable sorting feature
            "O(1)",                 // Q12: Time complexity for accessing an element in an array
            "O(n)",                 // Q13: Time complexity of linear search
            "1 4 9 16 25",          // Q14: Output of the given code snippet
            "O(n)",                 // Q15: Time complexity of the given function
            "HashMap and Doubly Linked List", // Q16: Data structures used in the implementation
            "O(n)",                 // Q17: Time complexity of traversing a linked list
            "BFS",                  // Q18: Graph traversal method that uses a queue
            "O(n^2)",               // Q19: Time complexity of bubble sort
            "O(log n)",             // Q20: Time complexity of finding the square root
            "Fibonacci Sequence",   // Q21: Output of the recursive Fibonacci function
            "Complete Binary Tree", // Q22: Type of tree where every level is fully filled
            "O(n)",                 // Q23: Time complexity of adding an element in a linked list
            "Depth First Search",   // Q24: Graph traversal method that uses a stack
            "10",                   // Q25: Output of the given code snippet
            "O(n)",                 // Q26: Time complexity of inserting an element in an array
            "Recursion",            // Q27: Programming technique used in the given function
            "2",                    // Q28: Output of the given code snippet
            "A",                    // Q29: Correct answer for the multiple-choice question
            "True",                 // Q30: Correct statement regarding hash tables
    };



    public DSAQuizApp() {
        frame = new JFrame("DSA in C++ Hard Level Quiz");
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
        frame.setSize(800, 900);
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
