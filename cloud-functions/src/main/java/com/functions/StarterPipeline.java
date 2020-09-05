package com.functions;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.*;
import org.apache.beam.sdk.io.redis.RedisIO;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

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
public class StarterPipeline {
    private static final Logger LOG = LoggerFactory.getLogger(StarterPipeline.class);

    public static interface WordCountOptions extends PipelineOptions {
        @Description("Path of the file to read from")
        @Default.String("gs://bucket-new-bucket/RedisFile.txt")
        String getInputFile();
        void setInputFile(String value);

    }

    public static void main(String[] args) {

        WordCountOptions options = PipelineOptionsFactory.fromArgs(args).withValidation()
                .as(WordCountOptions.class);

        Pipeline p = Pipeline.create(options);
//        PipelineOptionsFactory.fromArgs(args).withValidation().create());

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
                        String lastName = null;
                        String dob = null;
                        String postalCode = null;
                        for(String field : fields) {
                            LOG.info("field content: "+field.toString());
                            String[] fieldKeyValue = field.split(":");
                            if(fieldKeyValue.length == 2) {
                                String key = fieldKeyValue[0].trim().toLowerCase();
                                String value = fieldKeyValue[1].trim().toLowerCase();
                                if(key.equals("guid")) {
                                    guid = value;
                                    LOG.info("found guid: "+guid);
                                } else if(key.equals("firstname")) {
                                    firstName = value;
                                    LOG.info("found firstName: "+firstName);
                                } else if(key.equals("lastname")) {
                                    lastName = value;
                                    LOG.info("found lastName: "+lastName);
                                } else if(key.equals("dob")) {
                                    dob = value;
                                    LOG.info("found dob: "+dob);
                                } else if(key.equals("postalcode")) {
                                    postalCode = value;
                                    LOG.info("found postalCode: "+postalCode);
                                }
                            }
                        }


                        if(guid!=null) {
//                    out.output("sadd firstname:".concat(firstName).concat(" ").concat(guid));
//                    out.output("sadd lastname:".concat(lastName).concat(" ").concat(guid));
//                    out.output("sadd dob:".concat(dob).concat(" ").concat(guid));
//                    out.output("sadd postalcode:".concat(postalCode).concat(" ").concat(guid));
                            out.output(KV.of("firstname:".concat(firstName), guid));
                            out.output(KV.of("lastname:".concat(lastName), guid));
                            out.output(KV.of("dob:".concat(dob), guid));
                            out.output(KV.of("postalcode:".concat(postalCode), guid));
                        }
                    }
                })).apply(RedisIO.write().withMethod(RedisIO.Write.Method.SADD).withEndpoint("10.161.69.171", 6379));
//            .apply(TextIO.write().to(options.getOutput()));;---->for writing in test file


// command to execute
//      mvn compile exec:java -e -Dexec.mainClass=com.click.example.StarterPipeline -Dexec.args="--project=stack-driver-testing-282612 --stagingLocation=gs://data-flow-temp-bkt/staging/ --tempLocation=gs://data-flow-temp-bkt/tmp/ --runner=DataflowRunner --output=gs://data-flow-temp-bkt/"

        // p.apply(Create.of("Hello", "World"))
        // .apply(MapElements.via(new SimpleFunction<String, String>() {
        //   @Override
        //   public String apply(String input) {
        //     return input.toUpperCase();
        //   }
        // }))
        // .apply(ParDo.of(new DoFn<String, Void>() {
        //   @ProcessElement
        //   public void processElement(ProcessContext c)  {
        //     LOG.info(c.element());
        //   }
        // }));

        p.run();
    }
}