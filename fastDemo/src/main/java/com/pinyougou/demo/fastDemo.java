package com.pinyougou.demo;

import org.csource.fastdfs.*;

public class fastDemo {
    public static void main(String[] args) throws Exception {
        ClientGlobal.init("D:\\workspace\\pinyougou-parent\\fastDemo\\src\\main\\resources\\fdfs_client.conf");

        TrackerClient trackerClient = new TrackerClient();

        TrackerServer trackerServer = trackerClient.getConnection();

        StorageServer storageServer = null;

        StorageClient storageClient = new StorageClient(trackerServer,storageServer);

        String[] uploadFile = storageClient.upload_file("E:\\private space\\wjh.jpg", "jpg", null);

        for (String s : uploadFile) {
            System.out.println(s);
        }

        //group1/M00/00/00/wKgZhV4828SAVFxFAAAeRhVZcs4061.jpg
    }
}
