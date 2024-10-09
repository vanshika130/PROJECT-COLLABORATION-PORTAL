import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class dsapython3 {
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
            // 1. Recursion
            {"What is the output of the following code?<br/><pre>def factorial(n):<br/>    if n == 0:<br/>        return 1<br/>    return n * factorial(n - 1)<br/>print(factorial(5))</pre>", "120", "60", "20", "None of the above"},

            // 2. Fibonacci
            {"What will be printed by this code?<br/><pre>def fibonacci(n):<br/>    if n <= 1:<br/>        return n<br/>    return fibonacci(n-1) + fibonacci(n-2)<br/>print(fibonacci(6))</pre>", "8", "13", "5", "None of the above"},

            // 3. Binary Search
            {"What is the output of this code?<br/><pre>def binary_search(arr, x):<br/>    l, r = 0, len(arr) - 1<br/>    while l <= r:<br/>        mid = l + (r - l) // 2<br/>        if arr[mid] == x:<br/>            return mid<br/>        elif arr[mid] < x:<br/>            l = mid + 1<br/>        else:<br/>            r = mid - 1<br/>    return -1<br/>arr = [2, 3, 4, 10, 40]<br/>x = 10<br/>print(binary_search(arr, x))</pre>", "3", "2", "4", "-1"},

            // 4. Merge Sort
            {"What will be the output of the following code?<br/><pre>def merge_sort(arr):<br/>    if len(arr) > 1:<br/>        mid = len(arr) // 2<br/>        L = arr[:mid]<br/>        R = arr[mid:]<br/>        merge_sort(L)<br/>        merge_sort(R)<br/>        i = j = k = 0<br/>        while i < len(L) and j < len(R):<br/>            if L[i] < R[j]:<br/>                arr[k] = L[i]<br/>                i += 1<br/>            else:<br/>                arr[k] = R[j]<br/>                j += 1<br/>            k += 1<br/>        while i < len(L):<br/>            arr[k] = L[i]<br/>            i += 1<br/>            k += 1<br/>        while j < len(R):<br/>            arr[k] = R[j]<br/>            j += 1<br/>            k += 1<br/>arr = [38, 27, 43, 3, 9, 82, 10]<br/>merge_sort(arr)<br/>print(arr)</pre>", "[3, 9, 10, 27, 38, 43, 82]", "[38, 27, 43, 3, 9, 82, 10]", "[27, 38, 3, 9, 10, 43, 82]", "[None of the above]"},

            // 5. Depth First Search (DFS)
            {"What will be printed by this code?<br/><pre>def dfs(graph, node, visited=None):<br/>    if visited is None:<br/>        visited = set()<br/>    visited.add(node)<br/>    print(node, end=' ')<br/>    for neighbor in graph[node]:<br/>        if neighbor not in visited:<br/>            dfs(graph, neighbor, visited)<br/>graph = {0: [1, 2], 1: [2], 2: [0, 3], 3: []}<br/>dfs(graph, 2)</pre>", "2 0 1 3", "2 1 3 0", "0 1 2 3", "None of the above"},

            // 6. Breadth First Search (BFS)
            {"What will be the output of the following code?<br/><pre>def bfs(graph, start):<br/>    visited, queue = set(), [start]<br/>    while queue:<br/>        vertex = queue.pop(0)<br/>        if vertex not in visited:<br/>            print(vertex, end=' ')<br/>            visited.add(vertex)<br/>            queue.extend([neighbor for neighbor in graph[vertex] if neighbor not in visited])<br/>graph = {0: [1, 2], 1: [2], 2: [0, 3], 3: []}<br/>bfs(graph, 2)</pre>", "2 0 3 1", "2 1 3 0", "0 1 2 3", "None of the above"},

            // 7. Dynamic Programming (Knapsack)
            {"What is the output of the following code?<br/><pre>def knapsack(W, wt, val, n):<br/>    if n == 0 or W == 0:<br/>        return 0<br/>    if wt[n-1] > W:<br/>        return knapsack(W, wt, val, n-1)<br/>    else:<br/>        return max(val[n-1] + knapsack(W-wt[n-1], wt, val, n-1), knapsack(W, wt, val, n-1))<br/>val = [60, 100, 120]<br/>wt = [10, 20, 30]<br/>W = 50<br/>n = len(val)<br/>print(knapsack(W, wt, val, n))</pre>", "220", "180", "120", "100"},

            // 8. Dijkstra's Algorithm
            {"What is the output of the following code?<br/><pre>import heapq<br/>def dijkstra(graph, start):<br/>    queue = [(0, start)]<br/>    distances = {vertex: float('infinity') for vertex in graph}<br/>    distances[start] = 0<br/>    while queue:<br/>        current_distance, current_vertex = heapq.heappop(queue)<br/>        if current_distance > distances[current_vertex]:<br/>            continue<br/>        for neighbor, weight in graph[current_vertex].items():<br/>            distance = current_distance + weight<br/>            if distance < distances[neighbor]:<br/>                distances[neighbor] = distance<br/>                heapq.heappush(queue, (distance, neighbor))<br/>    return distances<br/>graph = {0: {1: 1, 2: 4}, 1: {2: 2, 3: 5}, 2: {3: 1}, 3: {}}<br/>print(dijkstra(graph, 0))</pre>", "{0: 0, 1: 1, 2: 3, 3: 4}", "{0: 0, 1: 4, 2: 2, 3: 5}", "{0: 1, 1: 2, 2: 3, 3: 4}", "{0: 0, 1: 2, 2: 3, 3: 4}"},

            // 9. Heap Operations
            {"What is the output of the following code?<br/><pre>import heapq<br/>nums = [3, 1, 4, 1, 5, 9]<br/>heapq.heapify(nums)<br/>heapq.heappush(nums, 2)<br/>print(heapq.heappop(nums))</pre>", "1", "2", "3", "4"},

            // 10. Set Operations
            {"What will be printed by this code?<br/><pre>s1 = {1, 2, 3}<br/>s2 = {2, 3, 4}<br/>print(s1 & s2)</pre>", "{1, 2, 3}", "{2, 3}", "{1, 4}", "None of the above"},

            // 11. Dictionary Comprehension
            {"What is the output of the following code?<br/><pre>dict = {x: x**2 for x in range(5)}<br/>print(dict)</pre>", "{0: 0, 1: 1, 2: 4, 3: 9, 4: 16}", "{0: 1, 1: 2, 2: 3, 3: 4}", "{1: 1, 2: 4, 3: 9, 4: 16}", "{0: 0, 1: 1, 2: 2, 3: 3}"},

            // 12. List Comprehension
            {"What is the output of the following code?<br/><pre>lst = [x for x in range(5) if x % 2 == 0]<br/>print(lst)</pre>", "[0, 1, 2, 3, 4]", "[0, 2, 4]", "[1, 3]", "[None of the above]"},

            // 13. String Manipulation
            {"What is the output of the following code?<br/><pre>s = 'Hello, World!'<br/>print(s[7:12])</pre>", "World", "World!", "Hello", "None of the above"},

            // 14. Tuple Unpacking
            {"What will be the output of the following code?<br/><pre>def func():<br/>    return (1, 2, 3)<br/>a, b, c = func()<br/>print(a, b, c)</pre>", "1 2 3", "1 2", "2 3", "None of the above"},

            // 15. Lambda Function
            {"What is the output of the following code?<br/><pre>add = lambda x, y: x + y<br/>print(add(5, 3))</pre>", "8", "53", "None of the above", "5 + 3"},

            // 16. Map Function
            {"What will be printed by this code?<br/><pre>nums = [1, 2, 3, 4]<br/>squared = list(map(lambda x: x ** 2, nums))<br/>print(squared)</pre>", "[1, 4, 9, 16]", "[1, 2, 3, 4]", "[1, 2, 3]", "[None of the above]"},

            // 17. Filter Function
            {"What is the output of the following code?<br/><pre>nums = [1, 2, 3, 4]<br/>even = list(filter(lambda x: x % 2 == 0, nums))<br/>print(even)</pre>", "[2, 4]", "[1, 3]", "[1, 2, 3, 4]", "[None of the above]"},

            // 18. Reduce Function
            {"What is the output of the following code?<br/><pre>from functools import reduce<br/>nums = [1, 2, 3, 4]<br/>product = reduce(lambda x, y: x * y, nums)<br/>print(product)</pre>", "24", "12", "20", "None of the above"},

            // 19. Exception Handling
            {"What will be the output of this code?<br/><pre>try:<br/>    x = 1 / 0<br/>except ZeroDivisionError:<br/>    print('Division by zero')<br/>else:<br/>    print('No errors')</pre>", "Division by zero", "No errors", "None of the above", "Error"},

            // 20. File Handling
            {"What is the output of the following code?<br/><pre>with open('test.txt', 'w') as f:<br/>    f.write('Hello, World!')<br/>with open('test.txt', 'r') as f:<br/>    print(f.read())</pre>", "Hello, World!", "Hello", "World!", "None of the above"},

            // 21. Sorting with Lambda
            {"What will be printed by this code?<br/><pre>pairs = [(1, 'one'), (2, 'two'), (3, 'three')]<br/>pairs.sort(key=lambda x: x[1])<br/>print(pairs)</pre>", "[(1, 'one'), (2, 'two'), (3, 'three')]", "[(3, 'three'), (1, 'one'), (2, 'two')]", "[(2, 'two'), (1, 'one'), (3, 'three')]", "None of the above"},

            // 22. List Sorting
            {"What is the output of the following code?<br/><pre>lst = [5, 2, 9, 1, 5, 6]<br/>lst.sort()<br/>print(lst)</pre>", "[1, 2, 5, 5, 6, 9]", "[5, 6, 2, 1, 9, 5]", "[9, 5, 6, 2, 1, 5]", "None of the above"},

            // 23. List Indexing
            {"What is the output of the following code?<br/><pre>lst = [10, 20, 30, 40]<br/>print(lst[-2])</pre>", "30", "20", "40", "None of the above"},

            // 24. String Join
            {"What is the output of the following code?<br/><pre>words = ['Hello', 'World']<br/>print(' '.join(words))</pre>", "Hello World", "HelloWorld", "None of the above", "World Hello"},

            // 25. Slicing
            {"What will be printed by the following code?<br/><pre>lst = [1, 2, 3, 4, 5]<br/>print(lst[1:4])</pre>", "[1, 2, 3]", "[2, 3, 4]", "[3, 4, 5]", "[None of the above]"},

            // 26. Tuple Packing
            {"What is the output of the following code?<br/><pre>t = (1, 2, 3)<br/>print(t[0])</pre>", "1", "2", "3", "None of the above"},

            // 27. Comprehensions
            {"What will be the output of the following code?<br/><pre>result = [x * 2 for x in range(5)]<br/>print(result)</pre>", "[0, 1, 2, 3, 4]", "[0, 2, 4, 6, 8]", "[0, 2, 4, 6, 8]", "[None of the above]"},

            // 28. Set Operations
            {"What is the output of the following code?<br/><pre>s = {1, 2, 2, 3}<br/>print(s)</pre>", "{1, 2, 3}", "{1, 2}", "{2, 3}", "None of the above"},

            // 29. Dictionary Access
            {"What is the output of the following code?<br/><pre>d = {'a': 1, 'b': 2}<br/>print(d.get('c', 0))</pre>", "0", "None", "Error", "2"},

            // 30. List Reverse
            {"What is the output of the following code?<br/><pre>lst = [1, 2, 3]<br/>lst.reverse()<br/>print(lst)</pre>", "[3, 2, 1]", "[1, 2, 3]", "None of the above", "[1, 3, 2]"}
    };
    private String[] answers = {
            // 1. Recursion
            "120",

            // 2. Fibonacci
            "8",

            // 3. Binary Search
            "3",

            // 4. Merge Sort
            "[3, 9, 10, 27, 38, 43, 82]",

            // 5. Depth First Search (DFS)
            "2 0 1 3",

            // 6. Breadth First Search (BFS)
            "2 0 3 1",

            // 7. Dynamic Programming (Knapsack)
            "220",

            // 8. Dijkstra's Algorithm
            "{0: 0, 1: 1, 2: 3, 3: 4}",

            // 9. Heap Operations
            "1",

            // 10. Set Operations
            "{2, 3}",

            // 11. Dictionary Comprehension
            "{0: 0, 1: 1, 2: 4, 3: 9, 4: 16}",

            // 12. List Comprehension
            "[0, 2, 4]",

            // 13. String Manipulation
            "World",

            // 14. Tuple Unpacking
            "1 2 3",

            // 15. Lambda Function
            "8",

            // 16. Map Function
            "[1, 4, 9, 16]",

            // 17. Filter Function
            "[2, 4]",

            // 18. Reduce Function
            "24",

            // 19. Exception Handling
            "Division by zero",

            // 20. File Handling
            "Hello, World!",

            // 21. Sorting with Lambda
            "[(3, 'three'), (1, 'one'), (2, 'two')]",

            // 22. List Sorting
            "[1, 2, 5, 5, 6, 9]",

            // 23. List Indexing
            "30",

            // 24. String Join
            "Hello World",

            // 25. Slicing
            "[2, 3, 4]",

            // 26. Tuple Packing
            "1",

            // 27. Comprehensions
            "[0, 2, 4, 6, 8]",

            // 28. Set Operations
            "{1, 2, 3}",

            // 29. Dictionary Access
            "0",

            // 30. List Reverse
            "[3, 2, 1]"
    };



    public dsapython3() {
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
