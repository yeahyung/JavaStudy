package Algorithm.Programmers;

import java.util.*;

public class BestAlbum {

    public static void main(String[] args){
        int[] anw = solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500});
        for(int i : anw){
            System.out.println(i);
        }
    }

    public static int[] solution(String[] genres, int[] plays) {
        List<Integer> anw = new ArrayList<>();

        Map<String, Integer> genresCount = new HashMap<>();
        int index = 0;
        for(String genre : genres){
            genresCount.put(genre, genresCount.getOrDefault(genre, 0) + plays[index]);
            index++;
        }

        // map value 기준으로 내림차순 sort
        List<String> keySetList = new ArrayList<>(genresCount.keySet());
        Collections.sort(keySetList, (o1, o2) ->
                genresCount.get(o2).compareTo(genresCount.get(o1)));

        keySetList.forEach(key ->
        {
            // 장르별 top 2 번호 선택
            TreeMap<Integer, Integer> countPerPlay = new TreeMap<>();
            int i=0,highest=-1,highestIndex=-1;
            for(String genre : genres){
                if(key.equals(genre)){
                    if(countPerPlay.containsKey(plays[i])){
                        if(highest < plays[i]){
                            highest = plays[i];
                            highestIndex = i;
                        }
                    }
                    else{
                        countPerPlay.put(plays[i], i);
                    }
                }
                i++;
            }

            int top2Count = 0;
            for(Integer keyValue : countPerPlay.descendingKeySet()){
                anw.add(countPerPlay.get(keyValue));
                top2Count++;

                if(top2Count >= 2){
                    break;
                }

                if (keyValue.equals(highest)) {
                    anw.add(highestIndex);
                    break;
                }
            }

        });

        // Integer List to int array
        return anw.stream().mapToInt(i->i).toArray();
    }
}
