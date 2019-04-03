package com.ccnu.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;

public class JDKSortTest {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("1");
        arrayList.add("");
        arrayList.add("");
        Collections.sort(arrayList, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(arrayList);

        ArrayList<Test> lists = new ArrayList<Test>();
        lists.add(new Test("1", "1"));
        lists.add(new Test("2", "2"));
        lists.add(new Test("3", "3"));
        lists.add(new Test("1", "2"));
        lists.add(new Test("", ""));

        Collections.sort(lists, new Comparator<Test>() {
            public int compare(Test o1, Test o2) {
                if (o1 == null || o1.getName() == null) {
                    return -1;
                }
                if (o2 == null || o2.getName() == null) {
                    return 1;
                }
                if (o1 == o2/* || o1.getName().equals(o2.getName())*/) {
                    return 0;
                }
                return o1.getName().compareTo(o2.getName());
            }
        });

        System.out.println(lists);


        TreeMap<String, Test> treeMap = new TreeMap<String, Test>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        treeMap.put("1", new Test("1", "1"));
        treeMap.put("a", new Test("a", "a"));
        treeMap.put("F", new Test("F", "F"));
        treeMap.put("3", new Test("3", "3"));
        treeMap.put("0", new Test("0", "0"));
        treeMap.put("1", new Test("1", "1第二次"));
        treeMap.put("", new Test("1", "1第二次"));
        treeMap.put("", new Test("1", "1第二次"));
//        treeMap.put(null,new Test("1","1第二次"));
        System.out.println(treeMap);
        System.out.println(treeMap.values());

        Iterator<String> it = treeMap.keySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }


    }
}


class Test {
    private String name;
    private String str;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Test(String name, String str) {
        super();
        this.name = name;
        this.str = str;
    }

    @Override
    public String toString() {
        return "Test [name=" + name + ", str=" + str + "]";
    }
}