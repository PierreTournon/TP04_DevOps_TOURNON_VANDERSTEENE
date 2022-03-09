
import datastruct.EmptyListException;
import datastruct.MyUnsortedList;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Nos tests ont une couverture de :
 *  - Class : 100% (3/3)
 *  - Method : 100% (14/14)
 *  - Line : 100% (74/74)
 *  - Branch : 90% (18/20)
 *
 * Les deux branchements qui nous manquent sont aux lignes 140 et 147 et sont tous deux de la forme "unMaillon == null".
 * La class Link et la tête de la liste étant privés, nous n'avons pas pu créer une situation où un maillon est null.
 */
public class MyUnsortedListTest {

    @Test
    public void isEmpty() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of();
        assertTrue(myUnsortedList.isEmpty());
        MyUnsortedList<String> myUnsortedList2 = MyUnsortedList.of("Un");
        assertFalse(myUnsortedList2.isEmpty());
    }

    @Test
    public void size() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of("Un", "Deux");
        assertEquals(myUnsortedList.size(), 2);
        MyUnsortedList<String> myUnsortedList2 = MyUnsortedList.of();
        assertEquals(myUnsortedList2.size(), 0);
    }

    @Test
    public void prepend() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of("Un");
        myUnsortedList.prepend("Deux");
        assertEquals(myUnsortedList, MyUnsortedList.of("Deux", "Un"));
    }

    @Test
    public void append() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of("Un");
        myUnsortedList.append("Deux");
        assertEquals("Test ajout d'un élément sur une liste non vide", myUnsortedList, MyUnsortedList.of("Un", "Deux"));
    }

    @Test
    public void insert() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of("Un");
        myUnsortedList.insert("Zéro", 0);
        assertEquals(myUnsortedList, MyUnsortedList.of("Zéro", "Un"));
        myUnsortedList.insert("Zéro", 2);
        assertEquals(myUnsortedList, MyUnsortedList.of("Zéro", "Un", "Zéro"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void insertOutLower() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of();
        myUnsortedList.insert("Zéro", -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void insertOutUpper() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of("Un");
        myUnsortedList.insert("Zéro", 2);
    }

    @Test
    public void pop() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of("Un");
        myUnsortedList.pop();
        assertEquals(myUnsortedList, MyUnsortedList.of());
        myUnsortedList.append("Zéro");
        myUnsortedList.append("Un");
        myUnsortedList.pop();
        assertEquals(myUnsortedList, MyUnsortedList.of("Un"));
    }

    @Test(expected = EmptyListException.class)
    public void popException() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of();
        myUnsortedList.pop();
    }

    @Test
    public void popLast() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of("Un");
        myUnsortedList.popLast();
        assertEquals(myUnsortedList, MyUnsortedList.of());
    }

    @Test(expected = EmptyListException.class)
    //Erreur pas d'exception levée => corrigé
    public void popLastException() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of();
        myUnsortedList.popLast();
    }

    @Test
    //Ici on aura une erreur de calcul de size avec un remove != 0
    public void remove() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of("Zéro", "Un", "Zéro", "Zéro");
        myUnsortedList.remove(2);
        assertEquals(myUnsortedList.size(), 3);
        assertEquals(myUnsortedList, MyUnsortedList.of("Zéro", "Un", "Zéro"));

        myUnsortedList.remove(0);
        assertEquals(myUnsortedList.size(), 2);
        assertEquals(myUnsortedList, MyUnsortedList.of("Un", "Zéro"));

        myUnsortedList.remove(1);
        assertEquals(myUnsortedList.size(), 1);
        assertEquals(myUnsortedList, MyUnsortedList.of("Un"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeOutLower() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of("Zéro", "Un", "Zéro");
        myUnsortedList.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeOutUpper() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of("Zéro", "Un", "Zéro");
        myUnsortedList.remove(myUnsortedList.size());
    }

    @Test
    public void equals() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of("Zéro", "Un", "Zéro", "Zéro");

        assertNotEquals(myUnsortedList, 2);
        assertNotEquals(myUnsortedList, MyUnsortedList.of("Zéro", "Un", "Zéro"));
        assertEquals(myUnsortedList, MyUnsortedList.of("Zéro", "Un", "Zéro", "Zéro"));
        assertNotEquals(myUnsortedList, MyUnsortedList.of("Zéro", "Un", "Deux", "Zéro"));
        assertNotEquals(myUnsortedList, MyUnsortedList.of("Zéro", "Un", null, "Zéro"));
    }

    @Test
    public void to_string() {
        MyUnsortedList<String> myUnsortedList = MyUnsortedList.of("Zéro", "Un", "Zéro", "Zéro");
        assertEquals(myUnsortedList.toString(), "MyUnsortedList { size = 4, [Zéro, Un, Zéro, Zéro] }");
    }
}