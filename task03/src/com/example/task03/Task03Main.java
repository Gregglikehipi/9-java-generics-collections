package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, Set<String>> map = new TreeMap<>();
        List<Set<String>> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))){
            List<String> words = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
            for (String word : words ){
                if (word.length() >= 3 && word.toLowerCase().matches("[а-яё]+")){
                    char[] b = word.toLowerCase().toCharArray();
                    Arrays.sort(b);
                    String key = new String(b);
                    if (map.containsKey(key)){
                        map.get(key).add(word.toLowerCase());
                    }else{
                        map.put(key, new TreeSet<>());
                        map.get(key).add(word.toLowerCase());
                    }
                }
            }

            for (String key : map.keySet()){
                if(map.get(key).size() >= 2){
                    result.add(map.get(key));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
