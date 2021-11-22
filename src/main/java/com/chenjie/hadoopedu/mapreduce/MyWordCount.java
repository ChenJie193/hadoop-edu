package com.chenjie.hadoopedu.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class MyWordCount {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration(true);
        System.setProperty("HADOOP_USER_NAME","root");
        conf.set("mapreduce.app-submission.cross-platform", "true");

        Job job = Job.getInstance(conf);
        job.setJar("E:\\code2\\hadoop-edu\\target\\hadoop-edu-1.0-SNAPSHOT.jar");
        job.setJarByClass(MyWordCount.class);
        // Specify various job-specific parameters
        job.setJobName("myjob");

        Path infile = new Path("/data/wc/input");
        TextInputFormat.addInputPath(job,infile);

        Path outfile = new Path("/data/wc/output");
        if(outfile.getFileSystem(conf).exists(outfile)){
            outfile.getFileSystem(conf).delete(outfile,true);
        }

        TextOutputFormat.setOutputPath(job,outfile);

        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setReducerClass(MyReducer.class);

        job.waitForCompletion(true);
    }
}
