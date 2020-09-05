package com.functions;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class CreateBucket {
    public static void main(String args[]) {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Bucket bucket = storage.create(BucketInfo.of("bucket-new-bucket"));

    }
}