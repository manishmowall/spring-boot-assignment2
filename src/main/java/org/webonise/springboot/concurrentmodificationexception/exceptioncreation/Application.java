package org.webonise.springboot.concurrentmodificationexception.exceptioncreation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/*concurrent modification exception occurs when thread is traversing the list(or hashmap)
and some structural modification to list(or hashmap) takes place.*/

//By default Singleton Class
@Component
public class Application {
   @Autowired
   @Qualifier("arrayList")
   private List<Integer> list;

   private final int INDEX = 2;
   private final int ELEMENT = 3;

   public void start() {

      System.out.println("\n=======Concurrent Modification Exception=======\n");
      list.add(1);
      list.add(2);
      list.add(3);
      list.add(4);
      list.add(5);

      System.out.println("Print list while removing " + ELEMENT + " ELEMENT from the list");
      for (Integer num : list) {
         System.out.println(num);

         if (num.equals(ELEMENT)) {
            list.remove(INDEX);
         }
      }
   }
}
