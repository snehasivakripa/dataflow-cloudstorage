package com.functions;
import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
public class CopyToAnotherBucket {
    public static void main(String args[]){
        Storage storage = StorageOptions.getDefaultInstance().getService();
        String sourceBucketName="bucket-test-demo";
        String targetBucketName="bucket-new-bucket";
        Bucket bucket = storage.get(sourceBucketName);
        Page<Blob> blobs = bucket.list();
        for (Blob blob : blobs.iterateAll()) {
            blob.copyTo(targetBucketName);
        }
        System.out.println(
                "Copied object "
                        + " from bucket "
                        + sourceBucketName
                        + " to "
                        + targetBucketName);
    }
}
