import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class java3 {
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
            {"What is the output of the following code?<br/><pre>public class Main {<br/>    public static void main(String[] args) {<br/>        System.out.println(factorial(5));<br/>    }<br/>    public static int factorial(int n) {<br/>        if (n == 0) return 1;<br/>        return n * factorial(n - 1);<br/>    }<br/>}</pre>", "120", "60", "20", "None of the above"},

            // 2. Fibonacci
            {"What will be printed by this code?<br/><pre>public class Main {<br/>    public static void main(String[] args) {<br/>        System.out.println(fibonacci(6));<br/>    }<br/>    public static int fibonacci(int n) {<br/>        if (n <= 1) return n;<br/>        return fibonacci(n - 1) + fibonacci(n - 2);<br/>    }<br/>}</pre>", "8", "13", "5", "None of the above"},

            // 3. Binary Search
            {"What is the output of this code?<br/><pre>import java.util.Arrays;<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        int[] arr = {2, 3, 4, 10, 40};<br/>        System.out.println(binarySearch(arr, 10));<br/>    }<br/>    public static int binarySearch(int[] arr, int x) {<br/>        int l = 0, r = arr.length - 1;<br/>        while (l <= r) {<br/>            int mid = l + (r - l) / 2;<br/>            if (arr[mid] == x) return mid;<br/>            if (arr[mid] < x) l = mid + 1;<br/>            else r = mid - 1;<br/>        }<br/>        return -1;<br/>    }<br/>}</pre>", "3", "2", "4", "-1"},

            // 4. Merge Sort
            {"What will be the output of the following code?<br/><pre>import java.util.Arrays;<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        int[] arr = {38, 27, 43, 3, 9, 82, 10};<br/>        mergeSort(arr);<br/>        System.out.println(Arrays.toString(arr));<br/>    }<br/>    public static void mergeSort(int[] arr) {<br/>        if (arr.length > 1) {<br/>            int mid = arr.length / 2;<br/>            int[] L = Arrays.copyOfRange(arr, 0, mid);<br/>            int[] R = Arrays.copyOfRange(arr, mid, arr.length);<br/>            mergeSort(L);<br/>            mergeSort(R);<br/>            merge(arr, L, R);<br/>        }<br/>    }<br/>    public static void merge(int[] arr, int[] L, int[] R) {<br/>        int i = 0, j = 0, k = 0;<br/>        while (i < L.length && j < R.length) {<br/>            if (L[i] <= R[j]) arr[k++] = L[i++];<br/>            else arr[k++] = R[j++];<br/>        }<br/>        while (i < L.length) arr[k++] = L[i++];<br/>        while (j < R.length) arr[k++] = R[j++];<br/>    }<br/>}</pre>", "[3, 9, 10, 27, 38, 43, 82]", "[38, 27, 43, 3, 9, 82, 10]", "[27, 38, 3, 9, 10, 43, 82]", "[None of the above]"},

            // 5. Depth First Search (DFS)
            {"What will be printed by this code?<br/><pre>import java.util.HashMap;<br/>import java.util.HashSet;<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        HashMap<Integer, int[]> graph = new HashMap<>();<br/>        graph.put(0, new int[]{1, 2});<br/>        graph.put(1, new int[]{2});<br/>        graph.put(2, new int[]{0, 3});<br/>        graph.put(3, new int[]{});<br/>        dfs(graph, 2, new HashSet<>());<br/>    }<br/>    public static void dfs(HashMap<Integer, int[]> graph, int node, HashSet<Integer> visited) {<br/>        visited.add(node);<br/>        System.out.print(node + );<br/>        for (int neighbor : graph.get(node)) {<br/>            if (!visited.contains(neighbor)) dfs(graph, neighbor, visited);<br/>        }<br/>    }<br/>}</pre>", "2 0 1 3", "2 1 3 0", "0 1 2 3", "None of the above"},

            // 6. Breadth First Search (BFS)
            {"What will be the output of the following code?<br/><pre>import java.util.*;<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        HashMap<Integer, List<Integer>> graph = new HashMap<>();<br/>        graph.put(0, Arrays.asList(1, 2));<br/>        graph.put(1, Arrays.asList(2));<br/>        graph.put(2, Arrays.asList(0, 3));<br/>        graph.put(3, new ArrayList<>());<br/>        bfs(graph, 2);<br/>    }<br/>    public static void bfs(HashMap<Integer, List<Integer>> graph, int start) {<br/>        Set<Integer> visited = new HashSet<>();<br/>        Queue<Integer> queue = new LinkedList<>();<br/>        queue.add(start);<br/>        while (!queue.isEmpty()) {<br/>            int vertex = queue.poll();<br/>            if (!visited.contains(vertex)) {<br/>                System.out.print(vertex +  );<br/>                visited.add(vertex);<br/>                queue.addAll(graph.get(vertex));<br/>            }<br/>        }<br/>    }<br/>}</pre>", "2 0 3 1", "2 1 3 0", "0 1 2 3", "None of the above"},

            // 7. Dynamic Programming (Knapsack)
            {"What is the output of the following code?<br/><pre>public class Main {<br/>    public static void main(String[] args) {<br/>        int[] val = {60, 100, 120};<br/>        int[] wt = {10, 20, 30};<br/>        int W = 50;<br/>        System.out.println(knapsack(W, wt, val, val.length));<br/>    }<br/>    public static int knapsack(int W, int[] wt, int[] val, int n) {<br/>        if (n == 0 || W == 0) return 0;<br/>        if (wt[n - 1] > W) return knapsack(W, wt, val, n - 1);<br/>        else return Math.max(val[n - 1] + knapsack(W - wt[n - 1], wt, val, n - 1), knapsack(W, wt, val, n - 1));<br/>    }<br/>}</pre>", "220", "180", "120", "100"},

            // 8. Dijkstra's Algorithm
            {"What is the output of the following code?<br/><pre>import java.util.*;<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();<br/>        graph.put(0, Map.of(1, 1, 2, 4));<br/>        graph.put(1, Map.of(2, 2, 3, 5));<br/>        graph.put(2, Map.of(3, 1));<br/>        graph.put(3, new HashMap<>());<br/>        System.out.println(dijkstra(graph, 0));<br/>    }<br/>    public static Map<Integer, Integer> dijkstra(Map<Integer, Map<Integer, Integer>> graph, int start) {<br/>        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));<br/>        Map<Integer, Integer> distances = new HashMap<>();<br/>        for (Integer vertex : graph.keySet()) distances.put(vertex, Integer.MAX_VALUE);<br/>        distances.put(start, 0);<br/>        queue.offer(new int[]{0, start});<br/>        while (!queue.isEmpty()) {<br/>            int[] curr = queue.poll();<br/>            for (Map.Entry<Integer, Integer> entry : graph.get(curr[1]).entrySet()) {<br/>                int neighbor = entry.getKey();<br/>                int weight = entry.getValue();<br/>                if (curr[0] + weight < distances.get(neighbor)) {<br/>                    distances.put(neighbor, curr[0] + weight);<br/>                    queue.offer(new int[]{distances.get(neighbor), neighbor});<br/>                }<br/>            }<br/>        }<br/>        return distances;<br/>    }<br/>}</pre>", "{0=0, 1=1, 2=3, 3=4}", "{0=0, 1=4, 2=1, 3=5}", "{0=0, 1=2, 2=4, 3=6}", "None of the above"},

            // 9. Heap Operations
            {"What will be the output of the following code?<br/><pre>import java.util.*;<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        PriorityQueue<Integer> minHeap = new PriorityQueue<>();<br/>        minHeap.add(3);<br/>        minHeap.add(1);<br/>        minHeap.add(2);<br/>        System.out.println(minHeap.poll());<br/>    }<br/>}</pre>", "1", "2", "3", "None of the above"},

            // 10. Set Operations
            {"What will be printed by this code?<br/><pre>import java.util.*;<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));<br/>        set.add(4);<br/>        set.add(1);<br/>        System.out.println(set);<br/>    }<br/>}</pre>", "{1, 2, 3, 4}", "{1, 2, 3}", "{1, 4}", "None of the above"},

            // 11. Dictionary Comprehension
            {"What will be the output of the following code?<br/><pre>public class Main {<br/>    public static void main(String[] args) {<br/>        Map<Integer, Integer> squareMap = new HashMap<>();<br/>        for (int i = 0; i < 5; i++) {<br/>            squareMap.put(i, i * i);<br/>        }<br/>        System.out.println(squareMap);<br/>    }<br/>}</pre>", "{0=0, 1=1, 2=4, 3=9, 4=16}", "{0=1, 1=4, 2=9, 3=16}", "{0=0, 1=1, 2=2, 3=3, 4=4}", "None of the above"},

            // 12. List Comprehension
            {"What is the output of the following code?<br/><pre>public class Main {<br/>    public static void main(String[] args) {<br/>        List<Integer> evenNumbers = new ArrayList<>();<br/>        for (int i = 0; i < 5; i++) {<br/>            if (i % 2 == 0) evenNumbers.add(i);<br/>        }<br/>        System.out.println(evenNumbers);<br/>    }<br/>}</pre>", "[0, 2, 4]", "[1, 3]", "[0, 1, 2]", "None of the above"},

            // 13. Ternary Operator
            {"What will be printed by this code?<br/><pre>public class Main {<br/>    public static void main(String[] args) {<br/>        int a = 5, b = 10;<br/>        System.out.println(a > b ? \"a is greater\" : \"b is greater\");<br/>    }<br/>}</pre>", "b is greater", "a is greater", "5 is greater", "None of the above"},

            // 14. Lambda Function
            {"What will be printed by this code?<br/><pre>import java.util.*;<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);<br/>        numbers.forEach(n -> System.out.print(n * 2 + \" \"));<br/>    }<br/>}</pre>", "1 2 3 4 5", "2 4 6 8 10", "1 3 5", "None of the above"},

            // 15. Exception Handling
            {"What will be printed by this code?<br/><pre>public class Main {<br/>    public static void main(String[] args) {<br/>        try {<br/>            System.out.println(10 / 0);<br/>        } catch (ArithmeticException e) {<br/>            System.out.println(\"Division by zero!\");<br/>        }<br/>    }<br/>}</pre>", "Division by zero!", "0", "10", "None of the above"},

            // 16. String Formatting
            {"What will be printed by this code?<br/><pre>public class Main {<br/>    public static void main(String[] args) {<br/>        String name = \"John\";<br/>        int age = 30;<br/>        System.out.printf(\"%s is %d years old.\", name, age);<br/>    }<br/>}</pre>", "John is 30 years old.", "30 is John years old.", "John 30", "None of the above"},

            // 17. String Methods
            {"What will be printed by this code?<br/><pre>public class Main {<br/>    public static void main(String[] args) {<br/>        String str = \"hello\";<br/>        System.out.println(str.toUpperCase());<br/>    }<br/>}</pre>", "HELLO", "Hello", "hello", "None of the above"},

            // 18. List Methods
            {"What is the output of the following code?<br/><pre>import java.util.*;<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));<br/>        numbers.remove(2);<br/>        System.out.println(numbers);<br/>    }<br/>}</pre>", "[1, 2, 4, 5]", "[1, 2, 3, 4]", "[1, 2, 3, 5]", "None of the above"},

            // 19. Tuple Packing and Unpacking
            {"What will be printed by this code?<br/><pre>public class Main {<br/>    public static void main(String[] args) {<br/>        int[] nums = {1, 2, 3};<br/>        System.out.println(nums[0] + \" \" + nums[1] + \" \" + nums[2]);<br/>    }<br/>}</pre>", "1 2 3", "1, 2, 3", "3 2 1", "None of the above"},

            // 20. File Handling
            {"What will be printed by this code?<br/><pre>import java.io.*;<br/>public class Main {<br/>    public static void main(String[] args) throws IOException {<br/>        FileWriter writer = new FileWriter(\"test.txt\");<br/>        writer.write(\"Hello World\");<br/>        writer.close();<br/>        BufferedReader reader = new BufferedReader(new FileReader(\"test.txt\"));<br/>        System.out.println(reader.readLine());<br/>        reader.close();<br/>    }<br/>}</pre>", "Hello World", "Hello", "World", "None of the above"},

            // 21. List Slicing
            {"What is the output of the following code?<br/><pre>import java.util.*;<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);<br/>        List<Integer> subList = numbers.subList(0, 3);<br/>        System.out.println(subList);<br/>    }<br/>}</pre>", "[1, 2, 3]", "[2, 3, 4]", "[1, 2]", "None of the above"},

            // 22. Set Comprehension
            {"What will be the output of the following code?<br/><pre>import java.util.*;<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        Set<Integer> set = new HashSet<>();<br/>        for (int i = 0; i < 5; i++) {<br/>            set.add(i);<br/>        }<br/>        System.out.println(set);<br/>    }<br/>}</pre>", "[0, 1, 2, 3, 4]", "[1, 2, 3, 4]", "[0, 2, 4]", "None of the above"},

            // 23. Static Method
            {"What will be printed by this code?<br/><pre>public class Main {<br/>    public static void greet() {<br/>        System.out.println(\"Hello, World!\");<br/>    }<br/>    public static void main(String[] args) {<br/>        greet();<br/>    }<br/>}</pre>", "Hello, World!", "Greeting!", "Hello!", "None of the above"},

            // 24. Final Keyword
            {"What will be printed by this code?<br/><pre>public class Main {<br/>    public static void main(String[] args) {<br/>        final int x = 10;<br/>        x = 20; // This line will cause an error<br/>    }<br/>}</pre>", "Compilation error", "10", "20", "None of the above"},

            // 25. Enum
            {"What is the output of the following code?<br/><pre>enum Color { RED, GREEN, BLUE }<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        System.out.println(Color.RED);<br/>    }<br/>}</pre>", "RED", "GREEN", "BLUE", "None of the above"},

            // 26. Inheritance
            {"What will be printed by this code?<br/><pre>class Animal {<br/>    void sound() {<br/>        System.out.println(\"Animal sound\");<br/>    }<br/>}<br/>class Dog extends Animal {<br/>    void sound() {<br/>        System.out.println(\"Bark\");<br/>    }<br/>}<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        Animal myDog = new Dog();<br/>        myDog.sound();<br/>    }<br/>}</pre>", "Animal sound", "Bark", "Dog", "None of the above"},

            // 27. Interface
            {"What will be printed by this code?<br/><pre>interface Animal {<br/>    void sound();<br/>}<br/>class Cat implements Animal {<br/>    public void sound() {<br/>        System.out.println(\"Meow\");<br/>    }<br/>}<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        Animal myCat = new Cat();<br/>        myCat.sound();<br/>    }<br/>}</pre>", "Meow", "Cat sound", "Animal sound", "None of the above"},

            // 28. Collections Framework
            {"What will be printed by this code?<br/><pre>import java.util.*;<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        List<String> list = new ArrayList<>();<br/>        list.add(\"Apple\");<br/>        list.add(\"Banana\");<br/>        System.out.println(list.size());<br/>    }<br/>}</pre>", "2", "1", "0", "None of the above"},

            // 29. Streams API
            {"What is the output of the following code?<br/><pre>import java.util.*;<br/>import java.util.stream.*;<br/>public class Main {<br/>    public static void main(String[] args) {<br/>        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);<br/>        int sum = numbers.stream().mapToInt(Integer::intValue).sum();<br/>        System.out.println(sum);<br/>    }<br/>}</pre>", "15", "10", "5", "None of the above"},

            // 30. Multithreading
            {"What will be printed by this code?<br/><pre>public class Main {<br/>    public static void main(String[] args) {<br/>        Thread thread = new Thread(() -> System.out.println(\"Thread running\"));<br/>        thread.start();<br/>    }<br/>}</pre>", "Thread running", "Thread not started", "No output", "None of the above"}
    };
    private String[] answers = {
            "3",          // 1. What will be the output of the following code?
            "5",          // 2. What will be the output of the following code?
            "1",          // 3. What will be printed by this code?
            "[3, 9, 10, 27, 38, 43, 82]", // 4. What will be printed by this code?
            "2",          // 5. What will be printed by this code?
            "4",          // 6. What will be printed by this code?
            "true",       // 7. What will be the output of the following code?
            "0",          // 8. What will be printed by this code?
            "1",          // 9. What will be the output of the following code?
            "{1, 2, 3, 4}", // 10. What will be printed by this code?
            "{0=0, 1=1, 2=4, 3=9, 4=16}", // 11. What will be the output of the following code?
            "[0, 2, 4]",  // 12. What is the output of the following code?
            "b is greater", // 13. What will be printed by this code?
            "2 4 6 8 10", // 14. What will be printed by this code?
            "Division by zero!", // 15. What will be printed by this code?
            "John is 30 years old.", // 16. What will be printed by this code?
            "HELLO",      // 17. What is the output of the following code?
            "[1, 2, 4, 5]", // 18. What will be printed by this code?
            "1 2 3",      // 19. What will be printed by this code?
            "Hello World", // 20. What is the output of the following code?
            "[1, 2, 3]",  // 21. What will be the output of the following code?
            "[0, 1, 2, 3, 4]", // 22. What will be printed by this code?
            "Hello, World!", // 23. What will be printed by this code?
            "Compilation error", // 24. What will be printed by this code?
            "RED",        // 25. What will be printed by this code?
            "Bark",       // 26. What will be printed by this code?
            "Meow",       // 27. What will be printed by this code?
            "2",          // 28. What will be printed by this code?
            "15",         // 29. What is the output of the following code?
            "Thread running" // 30. What will be printed by this code?
    };


    public java3() {
        frame = new JFrame("Java Hard Level Quiz");
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
