package com.functions;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dataflow.Dataflow;
import com.google.api.services.dataflow.model.CreateJobFromTemplateRequest;
import com.google.api.services.dataflow.model.RuntimeEnvironment;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.logging.Logger;

public class CloudStorageFunction implements BackgroundFunction<CloudStorageFunction.GCSEvent> {
    private static final Logger logger = Logger.getLogger(CloudStorageFunction.class.getName());

    @Override
    public void accept(GCSEvent event, Context context) throws GeneralSecurityException, IOException {

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter (credentials);
        logger.info ( "Http"+httpTransport+jsonFactory+requestInitializer );

        Dataflow dataflowService = new Dataflow.Builder(httpTransport, jsonFactory, (HttpRequestInitializer) requestInitializer)
                .setApplicationName("Google Cloud Platform Sample")
                .build();


        String projectId = "my-new-267017";



        RuntimeEnvironment runtimeEnvironment = new RuntimeEnvironment();
        runtimeEnvironment.setTempLocation("gs://dataflow-wordcount-stagging/tmp");
        CreateJobFromTemplateRequest createJobFromTemplateRequest = new CreateJobFromTemplateRequest();
        createJobFromTemplateRequest.setEnvironment(runtimeEnvironment);
        createJobFromTemplateRequest.setLocation("us-central1");
        createJobFromTemplateRequest.setGcsPath("gs://dataflow-wordcount-stagging/templates/dataflow-template-test");
        createJobFromTemplateRequest.getParameters().put("inputFile","gs://bucket-test-demo/document.txt");
        createJobFromTemplateRequest.getParameters ().put ("output","gs://dataflow-pipelines-outputs");
        createJobFromTemplateRequest.getParameters ().put ( "runner", "DataflowRunner");
        dataflowService.projects().templates().create(projectId,createJobFromTemplateRequest);

    }

    public static class GCSEvent {
        String bucket;
        String name;
        String metageneration;
    }
}

