//package com.chenjie.hadoopedu.hdfs;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//
//
//public class TestHDFS {
//
//    public Configuration conf = null;
//    public FileSystem fs = null;
//
//    @Before
//    public void conn() throws IOException {
//        conf = new Configuration(true);
//        fs = FileSystem.get(conf);
//    }
//
//    @Test
//    public void mkdir() throws IOException {
//        Path dir = new Path("/mockingbird");
//        if(fs.exists(dir)){
//            fs.delete(dir ,true);
//        }
//        fs.mkdirs(dir);
//    }
//
//    @After
//    public void close(){
//
//    }
//}
