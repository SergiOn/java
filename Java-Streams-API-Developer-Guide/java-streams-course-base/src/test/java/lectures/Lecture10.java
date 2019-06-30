package lectures;


import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

public class Lecture10 {

  private static final List<ArrayList<String>> arrayListOfNames = Lists.newArrayList(
      Lists.newArrayList("Mariam", "Alex", "Ismail"),
      Lists.newArrayList("John", "Alesha", "Andre"),
      Lists.newArrayList("Susy", "Ali")
  );

  @Before
  public void setUp() {
    System.out.println(arrayListOfNames);
  }

  @Test
  public void withoutFlatMap() throws Exception {
//    [Mariam, Alex, Ismail, John, Alesha, Andre, Susy, Ali]

    List<String> listOfNames = Lists.newArrayList();

    arrayListOfNames.forEach(arrList -> {
      arrList.forEach(listOfNames::add);
    });

    System.out.println("listOfNames: " + listOfNames);
  }

  @Test
  public void withFlatMap() throws Exception {
//   [Mariam, Alex, Ismail, John, Alesha, Andre, Susy, Ali]

    List<String> listOfNames = arrayListOfNames.stream()
//            .flatMap(item -> item.stream())
            .flatMap(Collection::stream)
//            .flatMap(List::stream)
            .collect(Collectors.toList());

    System.out.println("listOfNames: " + listOfNames);
  }

}

