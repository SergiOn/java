package lectures;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.stream.Collectors;
import mockdata.MockData;
import org.junit.Test;

public class Lecture11 {

  @Test
  public void joiningStrings() throws Exception {
    List<String> names = ImmutableList.of("anna", "john", "marcos", "helena", "yasmin");

    StringBuilder join = new StringBuilder();
//    String join = "";
    String separator = "";

    for (String name : names) {

      join.append(separator).append(name);
//      join += separator + name;
      separator = ", ";
    }

    System.out.println("join: " + join);
  }

  @Test
  public void joiningStringsWithStream() throws Exception {
    List<String> names = ImmutableList.of("anna", "john", "marcos", "helena", "yasmin");

    String join = names.stream()
            .collect(Collectors.joining(", "));

    String join2 = String.join(", ", names);

    String join3 = names.stream()
            .map(String::toUpperCase)
            .collect(Collectors.joining(" | "));

    System.out.println("join: " + join);
    System.out.println("join2: " + join2);
    System.out.println("join3: " + join3);
  }
}
