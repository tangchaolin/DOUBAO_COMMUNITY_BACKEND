package com.tangchaolin.doubao.utils;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.tangchaolin.doubao.model.entity.BmsFollow;
import com.tangchaolin.doubao.model.entity.BmsPost;
import com.tangchaolin.doubao.model.entity.BmsTopicTag;
import org.apache.commons.collections4.SetUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SimilarityHelper {
    private Map<Integer, Double> map ;
    private List<Entry<Integer, Double>> infoIds ;
    private List<BmsPost> newlist;

    private BmsPost[] ls;



    public SimilarityHelper() {
        this.map = new HashMap<>();
        this.newlist = new ArrayList<BmsPost>(10);
        this.ls = new BmsPost[10];

    }

    public static Double cos(String a, String b) {
        if (a == null || b == null) {
            return null;
        }
        Set<Integer> aChar = a.chars().boxed().collect(Collectors.toSet());
        Set<Integer> bChar = b.chars().boxed().collect(Collectors.toSet());

        // 统计字频
        Map<Integer, Integer> aMap = new HashMap<>();
        Map<Integer, Integer> bMap = new HashMap<>();
        for (Integer a1 : aChar) {
            aMap.put(a1, aMap.getOrDefault(a1, 0) + 1);
        }
        for (Integer b1 : bChar) {
            bMap.put(b1, bMap.getOrDefault(b1, 0) + 1);
        }

        // 向量化
        Set<Integer> union = SetUtils.union(aChar, bChar);
        int[] aVec = new int[union.size()];
        int[] bVec = new int[union.size()];
        List<Integer> collect = new ArrayList<>(union);
        for (int i = 0; i < collect.size(); i++) {
            aVec[i] = aMap.getOrDefault(collect.get(i), 0);
            bVec[i] = bMap.getOrDefault(collect.get(i), 0);
        }

        // 分别计算三个参数
        int p1 = 0;
        for (int i = 0; i < aVec.length; i++) {
            p1 += (aVec[i] * bVec[i]);
        }

        float p2 = 0f;
        for (int i : aVec) {
            p2 += (i * i);
        }
        p2 = (float) Math.sqrt(p2);

        float p3 = 0f;
        for (int i : bVec) {
            p3 += (i * i);
        }
        p3 = (float) Math.sqrt(p3);

        return ((double) p1) / (p2 * p3);
    }
    public  List<BmsPost> getRecommendList(List<BmsPost> list,BmsPost post){
        for (int i = 0; i < list.size(); i++) {
//            cos(post,list.get(i));
            map.put(i, cos(post.getContent(), list.get(i).getContent()));
        }
        infoIds=new ArrayList<Map.Entry<Integer, Double>>(map.entrySet());

        Collections.sort(infoIds, new Comparator<Map.Entry<Integer, Double>>() {
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2)
            {
                if ((o2.getValue() - o1.getValue())>0)
                    return 1;
                else if((o2.getValue() - o1.getValue())==0)
                    return 0;
                else
                    return -1;
            }
        });
//        for (int i = 0; i < 3; i++) {
//            System.out.println(list.get(infoIds.get(i).getKey()).getContent());
//
//        }

        for (int i = 0; i < infoIds.size(); i++) {
//            newlist.add(list.get(infoIds.get(i).getKey()));
            ls[i] = list.get(infoIds.get(i).getKey());
        }
        newlist = Arrays.asList(ls);
        for (int i = 0; i < 3; i++) {
            System.out.println(newlist.get(i).getContent());
        }
//        System.out.println("-------------------------------------");
//
//        for (int i = 0; i < 3; i++) {
//            System.out.println(newlist.get(i).getContent());
//        }
//        System.out.println("--------------------------------------");
//        System.out.println(newlist.size());

        return newlist;

    }



}
