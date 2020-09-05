package com.functions;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
public class ListBucketContents {
    public static void main(String args[]){
        Storage storage = StorageOptions.getDefaultInstance().getService();
        String bucketName="bucket-test-demo";
        Bucket bucket = storage.get(bucketName);
        Page<Blob> blobs = bucket.list();
        for (Blob blob : blobs.iterateAll()) {
        System.out.println(blob.getName());
        }
}
}
