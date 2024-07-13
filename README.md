# Link-Shortener-Development 
### Project: Link Shortener Development
**Duration: 7 Days**

### Project Overview
The "Link Shortener Development" project will give you hands-on experience in Java programming and web development. The primary goal is to create a simple Link Shortener application that converts long URLs into short, unique identifiers. This project will enhance your understanding of data structures, algorithms, and basic web application development.

### Project Objectives
- **Java Programming**: Reinforce your understanding of Java programming concepts.
- **URL Shortening and Expansion**: Develop functions to shorten long URLs and expand short URLs to their original form.
- **Unique Short URLs**: Ensure the uniqueness of short URLs and handle potential collisions.
- **Error Handling and User Feedback**: Explore basic error handling and provide user feedback.
- **Algorithmic Thinking**: Gain practical experience in data structures and algorithmic thinking.

### Requirements and Features
- **Java**: Use Java as the primary programming language.
- **Class Structure**: Create classes to manage URL shortening and expansion.
- **Hash Function**: Implement a basic hash function for generating short URLs.
- **Error Handling**: Address scenarios like duplicate long URLs and invalid short URLs.
- **Data Persistence**: Optionally, persist data to maintain link mappings between sessions.
- **User Interface**: Develop a simple command-line interface (CLI) or web-based interface for user interaction.

### Implementation Plan

#### Step 1: Create the URL Shortener Class
The `URLShortener` class will manage the shortening and expansion of URLs.

```java
import java.util.HashMap;
import java.util.Map;

public class URLShortener {
    private Map<String, String> shortToLongURLMap;
    private Map<String, String> longToShortURLMap;
    private static final String BASE_URL = "http://short.url/";
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;

    public URLShortener() {
        shortToLongURLMap = new HashMap<>();
        longToShortURLMap = new HashMap<>();
    }

    public String shortenURL(String longURL) {
        if (longToShortURLMap.containsKey(longURL)) {
            return BASE_URL + longToShortURLMap.get(longURL);
        }

        String shortURL = generateShortURL();
        while (shortToLongURLMap.containsKey(shortURL)) {
            shortURL = generateShortURL();
        }

        shortToLongURLMap.put(shortURL, longURL);
        longToShortURLMap.put(longURL, shortURL);

        return BASE_URL + shortURL;
    }

    public String expandURL(String shortURL) {
        String key = shortURL.replace(BASE_URL, "");
        return shortToLongURLMap.getOrDefault(key, "Invalid short URL");
    }

    private String generateShortURL() {
        StringBuilder shortURL = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = (int) (Math.random() * CHARACTERS.length());
            shortURL.append(CHARACTERS.charAt(index));
        }
        return shortURL.toString();
    }
}
```

#### Step 2: Create the Main Class
The `Main` class will provide a simple command-line interface for user interaction.

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the long URL: ");
                    String longURL = scanner.nextLine();
                    String shortURL = urlShortener.shortenURL(longURL);
                    System.out.println("Short URL: " + shortURL);
                    break;
                case 2:
                    System.out.print("Enter the short URL: ");
                    String inputShortURL = scanner.nextLine();
                    String originalURL = urlShortener.expandURL(inputShortURL);
                    System.out.println("Original URL: " + originalURL);
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
```

### Running the Application
1. **Compile the Code**:
   - Open a terminal or command prompt.
   - Navigate to the project folder where your Java files are located.
   - Compile the Java files using the following command:
     ```shell
     javac Main.java URLShortener.java
     ```

2. **Run the Application**:
   - Run the compiled `Main` class using the following command:
     ```shell
     java Main
     ```

### Summary
The Link Shortener Development project provides hands-on experience in Java programming, data structures, and basic web application development. By following the implementation plan and coding steps outlined above, you will create a functional link shortener application and gain valuable practical experience in Java development.
