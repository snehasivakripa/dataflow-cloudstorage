package com.functions;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dataflow.Dataflow;
import com.google.api.services.dataflow.DataflowScopes;
import com.google.api.services.dataflow.model.CreateJobFromTemplateRequest;
import com.google.api.services.dataflow.model.RuntimeEnvironment;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.auth.http.HttpCredentialsAdapter;

import java.util.HashMap;

public class CloudFun implements HttpFunction {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);


        Dataflow dataflowService = new Dataflow.Builder(httpTransport, jsonFactory, (HttpRequestInitializer) requestInitializer)
                .setApplicationName("Google Cloud Platform Sample")
                .build();


        String projectId = "my-dataflow-project-284008";


        RuntimeEnvironment runtimeEnvironment = new RuntimeEnvironment();
        runtimeEnvironment.setBypassTempDirValidation(false);
        runtimeEnvironment.setTempLocation("gs://dataflow-pipeline-staging/tmp");
        CreateJobFromTemplateRequest createJobFromTemplateRequest = new CreateJobFromTemplateRequest();
        createJobFromTemplateRequest.setEnvironment(runtimeEnvironment);
        createJobFromTemplateRequest.setLocation("us-central1");
        createJobFromTemplateRequest.setGcsPath("gs://dataflow-pipeline-staging/templates/dataflow-memorystore-template");
        createJobFromTemplateRequest.setJobName("Dataflow-Cloud");
        createJobFromTemplateRequest.setParameters(new HashMap<String,String>());
        createJobFromTemplateRequest.getParameters().put("inputFile","gs://cloud-function-gcsbucket/RedisFile.txt");
        dataflowService.projects().templates().create(projectId,createJobFromTemplateRequest);
    }


}