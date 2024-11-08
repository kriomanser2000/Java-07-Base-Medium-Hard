import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapLines
{
    public static void main(String[] args)
    {
        List<String> sentences = Arrays.asList(
                "first line",
                "second line, also have words",
                "third line, also have word, bla bla bla, car, ship."
        );
        Map<String, Long> wordFrequency = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.toLowerCase().split("\\s+")))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        wordFrequency.forEach((word, count) -> System.out.println(word + ": " + count));
    }
}
