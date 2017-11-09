package josefprolemobjektorientiert;

public class JosefProlemObjektorientiert {

    public static void main(String[] args) {
        Person erster = new Person(1);
        int personenCount = 41;
        for (int i = personenCount; i > 1; i--) {
            erster.addNext(new Person(i));
        }
        Person current = erster;
        int counter = 0;
        Ausgabe(current);
        counter = 0;
        do {
            counter ++;
            if (counter % 3 == 0) {
                current = current.remove();
                Ausgabe(current);
            } else {
                current = current.next;
            }
        } while (current != current.next);


    }

    public static void Ausgabe(Person current) {
        Person erster = current;
        do {
            System.out.print(current + " ");
            current = current.next;

        } while (current != erster);

        System.out.println();
    }
}

class Person {

    private int id = 0;
    public Person next, previous;

    public Person(int nr) {
        this.id = nr;
        next = this;
        previous = this;
    }

    public void addNext(Person p) {
        p.next = this.next;
        p.previous = this;

        this.next.previous = p;
        this.next = p;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }

    public Person remove() {
        this.next.previous = this.previous;
        this.previous.next = this.next;
        return this.next;
    }
}
