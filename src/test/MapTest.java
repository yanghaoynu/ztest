package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author YANGHAO
 */
public class MapTest {

	public MapTest() {
	}

	public static void main(String[] args) {
		HashMap<String, String>  testmap = new HashMap<String, String>();
		testmap.put("name", "杨昊");
		testmap.put("sex", "男");
		testmap.put("idcode", "123");
		//1
        for(Entry<String, String> entry: testmap.entrySet()){ 
        	System.out.println("1:"+entry.getKey()+":"+entry.getValue());
        }
     
        Set<Entry<String, String>> set = testmap.entrySet();
        Iterator<Entry<String, String>>  iterator = set.iterator();
        while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println("2:"+entry.getKey()+":"+entry.getValue());
		}
        System.out.println("================================");
        //2
        for (String key : testmap.keySet()) {
			System.out.println("3:"+"key:"+testmap.get(key));
		}
        System.err.println("================================");
        Set set2 = testmap.keySet();
        Iterator<String> iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
			String string = iterator2.next();
			System.out.println(testmap.get(string));
		}
	}

}
