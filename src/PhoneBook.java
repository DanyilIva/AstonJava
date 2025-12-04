import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String, List<String>> directory = new HashMap<>();

    public void add(String surname, String number) {
        directory.computeIfAbsent(surname, k -> new ArrayList<>()).add(number);
    }

    public List<String> get(String surname) {
        return directory.getOrDefault(surname, new ArrayList<>());
    }

    public void showDirectory() {
        for (Map.Entry<String, List<String>> entry : directory.entrySet()) {
            System.out.print(entry.getKey() + ": "); // Фамилия
            System.out.println(entry.getValue());    // Список номеров
        }
    }
}
