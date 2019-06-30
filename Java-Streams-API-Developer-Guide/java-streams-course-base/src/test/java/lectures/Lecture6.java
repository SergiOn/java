package lectures;


import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Lecture6 {

  final Predicate<Integer> numbersLessThan10 = n -> n > 5 && n < 10;

  @Test
  public void findAny() throws Exception {
    Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    int integer = Arrays.stream(numbers)
//            .parallel()
            .filter(i -> i < 10)
            .findAny()
            .get();

    assertThat(integer).isEqualTo(1);
  }

  @Test
  public void findFirst() throws Exception {
    Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    int integer = Arrays.stream(numbers)
//            .parallel()
            .filter(i -> i < 10)
            .findFirst()
            .get();

    assertThat(integer).isEqualTo(1);
  }
}

