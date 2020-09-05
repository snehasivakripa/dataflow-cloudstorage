package com.functions;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.redis.RedisIO;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * A starter example for writing Beam programs.
 *
 * <p>The example takes two strings, converts them to their upper-case
 * representation and logs them.
 *
 * <p>To run this starter example locally using DirectRunner, just
 * execute it without any additional parameters from your favorite development
 * environment.
 *
 * <p>To run this starter example using managed resource in Google Cloud
 * Platform, you should specify the following command-line options:
 *   --project=<YOUR_PROJECT_ID>
 *   --stagingLocation=<STAGING_LOCATION_IN_CLOUD_STORAGE>
 *   --runner=DataflowRunner
 */
public class Manipulation {
    private static final Logger LOG = LoggerFactory.getLogger(StarterPipeline.class);

    public static interface WordCountOptions extends PipelineOptions {
        @Description("Path of the file to read from")
        @Default.String("gs://cloud-function-gcsbucket/NewRedis.txt")
        String getInputFile();
        void setInputFile(String value);

    }

    public static void main(String[] args) {

        WordCountOptions options = PipelineOptionsFactory.fromArgs(args).withValidation()
                .as(WordCountOptions.class);

        Pipeline p = Pipeline.create(options);

        p.apply(
                "ReadLines", TextIO.read().from(options.getInputFile()))
                .apply(
                        "Transform Record",                     // the transform name
                        ParDo.of(new DoFn<String, String[]>() {    // a DoFn as an anonymous inner class instance
                            @ProcessElement
                            public void processElement(@Element String line, OutputReceiver<String[]> out) {
                                LOG.info("line content: "+line);
                                String[] fields = line.split("\\|");
                                out.output(fields);
                            }
                        })).apply(
                "Transform Record",                     // the transform name
                ParDo.of(new DoFn<String[], KV<String, String>>() {    // a DoFn as an anonymous inner class instance
                    @ProcessElement
                    public void processElement(@Element String[] fields, OutputReceiver<KV<String, String>> out) {
                        String guid = null;
                        String firstName = null;
                        String middleName=null;
                        String lastName = null;
                        String dob = null;
                        String postalCode = null;
                        String gender=null;
                        String pid=null;
                        int i=0;
                        String[] hashvalue={"hash11","hash1","hash2","hash3","hash4","hash5","hash6","hash12"};
                        for(String field : fields) {


                                String key = hashvalue[i];
                                String value = field;
                                LOG.info("key: " + key);
                                LOG.info("value:" + value);
                                i = i + 1;


                                if(key.equals("hash11")) {
                                    guid = value;
                                    LOG.info("found hash11: "+guid);
                                } else if(key.equals("hash1")) {
                                    firstName = value;
                                    LOG.info("found hash1: "+firstName);
                                } else if(key.equals("hash2")) {
                                    middleName = value;
                                    LOG.info("found hash2: "+middleName);
                                } else if(key.equals("hash3")) {
                                    lastName = value;
                                    LOG.info("found hash3: "+lastName);
                                } else if(key.equals("hash4")) {
                                    dob = value;
                                    LOG.info("found hash4: "+dob);
                                }else if(key.equals("hash5")) {
                                    postalCode = value;
                                    LOG.info("found hash5: "+postalCode);
                                }else if(key.equals("hash6")) {
                                    gender = value;
                                    LOG.info("found hash6: "+gender);
                                }else if(key.equals("hash12")) {
                                    pid = value;
                                    LOG.info("found hash12: "+pid);
                                }

                        }


                        if(fields[1]!=null) {
                            out.output(KV.of("hash1:".concat(firstName), guid));
                            out.output(KV.of("hash2:".concat(middleName), guid));
                            out.output(KV.of("hash3:".concat(lastName), guid));
                            out.output(KV.of("hash4:".concat(dob), guid));
                            out.output(KV.of("hash5:".concat(postalCode), guid));
                            out.output(KV.of("hash6:".concat(gender), guid));
                           out.output(KV.of("hash11:".concat(guid), "hash12:".concat(pid )));
                        }
                    }
                 })).apply(RedisIO.write().withMethod(RedisIO.Write.Method.SADD).withEndpoint("localhost", 6379));
        p.run();
    }
}