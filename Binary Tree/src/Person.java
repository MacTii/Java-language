import java.util.Comparator;

public class Person implements Comparable<Person>, Comparator<Person> {
    private String name;
    private String surname;
    private int year;
    private double wage;

    Person(String name, String surname, int year, double wage) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.wage = wage;
    }

    public String getName() {
        return name;
    }

    public int getNameLength()
    {
        return name.length();
    }

    public String getSurname() {
        return surname;
    }

    public int getSurnameLength()
    {
        return surname.length();
    }

    public int getYear() {
        return year;
    }

    public double getWage() {
        return wage;
    }

    // public int compareTo(Person person) {
    //     int compare = name.compareTo(person.name);
    //     if (compare == 0) {
    //         compare = surname.compareTo(person.surname);
    //         if (compare == 0) {
    //             compare = Integer.compare(year, person.year);
    //             if (compare == 0)
    //                 compare = Double.compare(wage, person.wage);
    //         }
    //     }
    //     return compare;

    // }

    // public int compare(Person firstPerson, Person secondPerson)
    // {
    //     int compare = firstPerson.name.compareTo(secondPerson.name);
    //     if (compare == 0) {
    //         compare = firstPerson.surname.compareTo(secondPerson.surname);
    //         if (compare == 0) {
    //             compare = Integer.compare(firstPerson.year, secondPerson.year);
    //             if (compare == 0)
    //                 compare = Double.compare(firstPerson.wage,secondPerson.wage);
    //         }
    //     }
    //     return compare;
    // }

    public int compareTo(Person person) {
        int result = (this.getNameLength() + this.getSurnameLength()) - (person.getNameLength() + person.getSurnameLength());
        return result < 0 ? -1 : result == 0 ? 0 : 1;
    }

    public int compare(Person person1, Person person2) {
        int result = (person1.getNameLength() + person1.getSurnameLength()) - (person2.getNameLength() + person2.getSurnameLength());
        return result < 0 ? -1 : result == 0 ? 0 : 1;
    }

}
