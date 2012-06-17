package com.namebound.parallax.wark;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("QuestionGeneration")
public class QuestionGenerationAdapter {
    
    private File questionGeneration;
    ARKrefAdapter arkref;
   
    public QuestionGenerationAdapter() {
        questionGeneration = new File("/opt/ARK/QuestionGeneration");
        arkref = new ARKrefAdapter();
    }
    
    @POST
    @Path("questions")
    @Produces(MediaType.TEXT_PLAIN)
    public String process(@FormParam("text") String text) throws IOException, InterruptedException {
         
        String digest = arkref.save(text);
        
        
        StringBuffer log = new StringBuffer();
        Process p = Runtime.getRuntime().exec("./run.sh --full-npc", null, questionGeneration);
        //p.waitFor();
        
        OutputStream stdin = p.getOutputStream();
        InputStream stdout = p.getInputStream();
        
        stdin.write(text.getBytes());
        stdin.flush();
        stdin.close();
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdout));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            log.append(line + '\n');
            System.out.println(line);
        }
        bufferedReader.close();

        return log.toString();
    }
    
}
