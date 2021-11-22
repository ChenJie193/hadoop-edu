package com.chenjie.hadoopedu.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class MyMapper extends Mapper<Object, Text, Text, IntWritable> {
    //在hadoop框架中，他是一个分布式  数据：序列化、反序列化

    //hadoop有自己一套可以序列化、反序列化

    //或者自己开发类型必须实现序列化，反序列化接口

    //排序-》比较 这个世界有两种顺序：字典序、数值序

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    //Text
    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        word.set(itr.nextToken());
        context.write(word, one);
      }
    }
}
