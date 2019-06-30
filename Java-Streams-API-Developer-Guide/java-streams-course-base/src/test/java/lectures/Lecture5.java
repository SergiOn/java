package lectures;


import static org.assertj.core.api.Assertions.assertThat;

import beans.Car;
import beans.Person;
import beans.PersonDTO;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import mockdata.MockData;
import org.junit.Test;

public class Lecture5 {

  final Predicate<Car> carPredicate = car -> car.getPrice() < 10000;

  @Test
  public void understandingFilter() throws Exception {
    ImmutableList<Car> cars = MockData.getCars();

    List<Car> carsFiltered = cars.stream()
//            .filter(car -> car.getPrice() < 10000)
            .filter(carPredicate)
            .collect(Collectors.toList());

    assertThat(carsFiltered).isNotEmpty();
  }

  @Test
  public void ourFirstMapping() throws Exception {
    // transform from one data type to another
    List<Person> people = MockData.getPeople();

    List<PersonDTO> personDTOs = people.stream()
//            .map(person -> new PersonDTO(person.getId(), person.getFirstName(), person.getAge()))
            .map(PersonDTO::map)
            .collect(Collectors.toList());

    assertThat(people).hasSize(personDTOs.size());
    assertThat(personDTOs).hasSize(1000);


    Function<Person, PersonDTO> personToPersonDTO = person -> new PersonDTO(person.getId(), person.getFirstName(), person.getAge());

    List<PersonDTO> dtos = people.stream()
            .map(personToPersonDTO)
            .collect(Collectors.toList());

    assertThat(people).hasSize(dtos.size());
    assertThat(dtos).hasSize(1000);
  }

  @Test
  public void averageCarPrice() throws Exception {
    // calculate average of car prices
    ImmutableList<Car> cars = MockData.getCars();

    double average = cars.stream()
            .mapToDouble(Car::getPrice)
            .average()
            .orElse(0);

    System.out.println(average);
  }

  @Test
  public void test() throws Exception {

  }
}



