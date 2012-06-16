package com.namebound.parallax.wark;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("QuestionGeneration")
public class QuestionGenerationAdapter {
    
    private File questionGeneration;
    
   
    public QuestionGenerationAdapter() {
        questionGeneration = new File("/opt/ARK/QuestionGeneration");
    }
    
    @GET
    @Path("questions")
    @Produces(MediaType.TEXT_PLAIN)
    public String process(@QueryParam("text") String text) throws IOException, InterruptedException {
        
        System.out.println("processing...");
        StringBuffer log = new StringBuffer();
        Process p = Runtime.getRuntime().exec("./run.sh --full-npc", null, questionGeneration);
        p.waitFor();
        
        OutputStream pipe = p.getOutputStream();
        pipe.write(text.getBytes());
        pipe.flush();
        pipe.close();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            log.append(line + '\n');
            System.out.println(line);
        }
        bufferedReader.close();

        return log.toString();
    }
    
}
