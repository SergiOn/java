package lectures;

import static org.assertj.core.api.Assertions.assertThat;

import beans.Person;

import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import mockdata.MockData;
import org.assertj.core.util.Lists;
import org.junit.Test;


public class Lecture1 {

  @Test
  public void imperativeApproach() throws IOException {
    List<Person> people = MockData.getPeople();
    // 1. Find people aged less or equal 18
    // 2. Then change implementation to find first 10 people

    // 1
    List<Person> people1 = new ArrayList<>();
    for (Person person : people) {
      if (person.getAge() <= 18) {
        people1.add(person);
      }
    }
    System.out.println("people: " + people.size());
    System.out.println("people1: " + people1.size());

    // 2
    List<Person> people2 = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      people2.add(people.get(i));
    }
    System.out.println("people: " + people.size());
    System.out.println("people2: " + people2.size());

    // 1 + 2
    List<Person> people3 = Lists.newArrayList();
    for (int i = 0, j = 0, size = people.size(); i < size && j < 10; i++) {
      Person person = people.get(i);
      if (person.getAge() <= 18) {
        people3.add(person);
        j++;
      }
    }
    System.out.println("people: " + people.size());
    System.out.println("people3: " + people3.size());
  }

  @Test
  public void declarativeApproachUsingStreams() throws Exception {
    ImmutableList<Person> people = MockData.getPeople();

    List<Person> youngPeople = people.stream()
            .filter(person -> person.getAge() <= 18)
            .limit(10)
            .collect(Collectors.toList());

    ImmutableList<Person> youngPeople2 = people.stream()
            .filter(person -> person.getAge() <= 18)
            .limit(10)
            .collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));

    System.out.println("youngPeople: " + youngPeople.size());

  }
}
