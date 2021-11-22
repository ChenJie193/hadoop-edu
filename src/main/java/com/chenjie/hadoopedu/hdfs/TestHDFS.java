package com.chenjie.hadoopedu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;

import java.io.*;
import java.net.URI;


public class TestHDFS {

    public Configuration conf = null;
    public FileSystem fs = null;

    @Before
    public void conn() throws Exception {
        conf = new Configuration(true);
        fs = FileSystem.get(URI.create("hdfs://node01:9000"), conf, "root");
    }

    @Test
    public void mkdir() throws IOException {
        Path dir = new Path("/mockingbird");
        if(fs.exists(dir)){
            fs.delete(dir ,true);
        }
        fs.mkdirs(dir);
    }

    @Test
    public void upload() throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File("./data/data.txt")));
        Path path = new Path("/data/wc/input/data.txt");
        FSDataOutputStream fsDataOutputStream = fs.create(path);
        IOUtils.copyBytes(bufferedInputStream, fsDataOutputStream, conf,true);
    }

    /***
     *<P>@author: 陈杰
     *<P>描述:获取文件的详细信息
     *<P>2021/11/17 0017 15:11
     *
     */
    @Test
    public void blocks() throws Exception {
        Path file = new Path("/mockingbird/out-pdf.pdf");
        FileStatus fss = fs.getFileStatus(file);
        BlockLocation[] fileBlockLocations = fs.getFileBlockLocations(fss, 0, fss.getLen());
        for (BlockLocation b:fileBlockLocations){
            System.out.println(b);
        }
        FSDataInputStream open = fs.open(file);
        System.out.println((char)open.readByte());
        System.out.println((char)open.readByte());
        System.out.println((char)open.readByte());
        System.out.println((char)open.readByte());
    }

    @After
    public void close() throws Exception {
        fs.close();
    }
}
