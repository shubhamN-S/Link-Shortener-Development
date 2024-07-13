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
