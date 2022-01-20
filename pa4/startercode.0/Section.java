import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Section {

    // create a section with no students in it
    private Map<Integer, String> section;
    private int studentID;
    public Section() {
        section = new TreeMap<Integer, String>();
        studentID = 1;
    }

    // add: adds a student to this section.
    // The given student is assigned the next sequential section ID,
    // which is returned: i.e., first student gets ID 1, second
    // gets ID 2, etc.  But if a student with this name already exists
    // in this section, does not add student, and returns -1
    // Note: does not do a defensive copy of the student object.
    // Also, this method does not modify the student object.
    public int add(String s) {
        int thisStuID = studentID;
        boolean exist = false;

        for (Map.Entry<Integer, String> item : section.entrySet()){
            if (item.getValue().equals(s)){
                exist = true;
            }
        }

        if (!exist){
            section.put(thisStuID, s);
            studentID++;
        }

        return thisStuID;
    }

    // returns the number of students in the section
    public int size() {
        return section.size();
    }

    // lookupByID: get student that has the given section ID
    // PRE: 1 <= id <= size()
    String lookupByID(int id) {
        return section.get(id);
    }

    // lookupIDByName: get ID of student with given name
    // If this student is not in the section, returns -1
    public int lookupIDByName(String name) {
        for (Map.Entry<Integer, String> item : section.entrySet()){
            if (item.getValue().equals(name)){
                return item.getKey();
            }
        }
        return -1;
    }

    // printStudents: prints all the info about all the students in
    // the section to System.out in order by section ID, one per
    // line: section ID followed by student info.
    // Sample output (the part starting with < is standing in
    // for what would be printed by toString for that student):
    //    1  <info about student 1>
    //    2  <info about student 2>
    //    . . .
    void printAll() {
        for (Map.Entry<Integer, String> item : section.entrySet()){
            System.out.println(item.getKey() + " " + item.getValue());
        }
    }

    public static void main(String[] args){
        Section asec = new Section();
        asec.add("xiangrx");
        asec.add("sdd");
        System.out.println(asec.lookupByID(1));
        System.out.println(asec.lookupIDByName("xiangrx"));
        asec.printAll();
    }

}