package lectures;


import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Test;

public class Lecture4 {

  @Test
  public void distinct() throws Exception {
    final List<Integer> numbers = ImmutableList.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9);

    List<Integer> collect = numbers.stream()
            .distinct()
            .collect(Collectors.toList());

    assertThat(collect).isEqualTo(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
    assertThat(collect).hasSize(9);
  }

  @Test
  public void distinctWithSet() throws Exception {
    final List<Integer> numbers = ImmutableList.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 9, 9, 9);

    Set<Integer> collect = numbers.stream().collect(Collectors.toSet());

    assertThat(collect).isEqualTo(Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
  }
}
