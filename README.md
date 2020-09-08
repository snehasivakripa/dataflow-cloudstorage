# Google Cloud Storage and Dataflow Examples

## Quickstart

For authentication settings in google sdk:
```xml
gcloud auth application-default login
```

## Includes:

## Cloud Storage:
1. Create cloud storage bucket.
2. Copy files from one bucket to another.
3. List out contents in bucket.

## Dataflow Batch

1. Read a file, manipulate and store in redis. (Starterpipeline)
   Output in redis: ```firstname:name```
2. Read a file, manipulate and store in redis. (Manipulation)
   Output in redis: ```hash1:firstname:name```
3. Read a file, manipulate using hashmap and store in redis. (ManipulationHashMap)
   Output in redis: ```hash1:firstname:name```

## Dataflow Streaming
1. Read a file, manipulate using hashmap and store in redis. (Streaming)
     Output in redis: ```hash1:firstname:name```
     
## CloudFunction
1. Http(trigger) to trigger dataflow. (CloudFun)
2. CloudStorage to trigger dataflow. (CloudStorageFunction)

## Command to create Dataflow Batch Job

Download the WordCount project from google provided sample code.

## Prerequistics:
1. Create 3 buckets:
  a. where inputfile is present
  b. for stagging details. Inside which 3 folders: temp, template and stagging
  c. for output folder.

## Steps:

1. Create a directory say Wordcount
2. cd WordCount
3. Clone the project using following command:

```Groovy
mvn archetype:generate -DarchetypeArtifactId=google-cloud-dataflow-java-archetypes-examples \
   -DarchetypeGroupId=com.google.cloud.dataflow  \
   -DarchetypeVersion=2.5.0 \
   -DgroupId=com.demo \
   -DartifactId=dataflow-demo \
   -Dversion="0.1" \
   -DinteractiveMode=false \
   -Dpackage=com.demo
```

4. Navigate inside the folder where pom is present and execute the following command to create dataflow job:

```Groovy
mvn compile exec:java -Dexec.mainClass=com.demo.WordCount -Dexec.args="--project=my-new-267017 \
--stagingLocation=gs://dataflow-wordcount-stagging/staging \
--dataflowJobFile=gs://dataflow-wordcount-stagging/templates/dataflow-template-test \
--gcpTempLocation=gs://dataflow-wordcount-stagging/tmp \
--output=gs://dataflow-pipelines-outputs \
--runner=DataflowRunner"

```
5. Check template is created in stagging/template folder and job is created in dataflow.

## Command to create Dataflow Batch Streaming

```Groovy
 mvn compile exec:java -Dexec.mainClass=com.function.Manipulation -Dexec.args="--project=my-dataflow-project-284008 \
--stagingLocation=gs://dataflow-pipeline-staging/staging \
--dataflowJobFile=gs://dataflow-pipeline-staging/templates/dataflow-redis-template2 \
--gcpTempLocation=gs://dataflow-pipeline-staging/tmp \
--streaming=true \
--runner=DataflowRunner"

```
