package org.webonise.concurrentmodificationexception.exceptionresolution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/*To avoid Concurrent modification exception, one should use CopyOnWriteArrayList(for ArrayList) or ConcurrentHashMap(for hashmap) classes*/
@Component
public class Application {

    @Autowired
    @Qualifier("copyOnWriteArrayList")
    private List<Integer> list;

    private final int INDEX = 2;
    private final int ELEMENT = 3;

    public void start() {

        System.out.println("\n=======Concurrent Modification Exception After Resolving=======\n");
        System.out.println("=====Using CopyOnWriteArrayList=====\n");
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

      /*CopyOnWriteArrayList provide fresh copy of array list to each block asking for it. And any changes made to the list while
      * block is working on it's copy of list will not be reflected to block's copy.But if after changes, some other block ask for
      * list , that copy will reflects the changes.Thus It will avoid conurrent modification exception as specific block's copy
      * will not be effected by changes made to the main list*/
        System.out.println("Given a copy to first FOR loop ");
        System.out.println("Print list while removing " + ELEMENT + " ELEMENT from the list");
        for (Integer num : list) {
            System.out.println(num);

            if (num.equals(ELEMENT)) {
                list.remove(INDEX);
            }
        }
        System.out.println("No changes are reflected on FOR\'s list. But Main list is changed\n");

        System.out.println("Given updated copy to second FOR loop");
        System.out.println("Printing list with recent update to Main list");
        for (int num : list) {
            System.out.println(num);
        }
    }
}
