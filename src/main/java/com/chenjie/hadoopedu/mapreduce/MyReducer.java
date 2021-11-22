package com.chenjie.hadoopedu.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer extends Reducer {

    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable <IntWritable> values,
                       Context context) throws Exception {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        result.set(sum);
        context.write(key, result);
    }
}
