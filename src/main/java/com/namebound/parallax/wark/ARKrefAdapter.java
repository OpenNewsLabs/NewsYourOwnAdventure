package com.namebound.parallax.wark;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jasypt.digest.StandardStringDigester;

@Path("arkref")
public class ARKrefAdapter {

    private File arkref;

    public ARKrefAdapter() {
        arkref = new File("/Users/laurian/Projects/Parallax/src/main/webapp/WEB-INF/ARK/arkref");
    }

    @GET
    @Path("resolve")
    @Produces(MediaType.TEXT_PLAIN)
    public String resolve(@QueryParam("text") String text) throws IOException, InterruptedException {
        
        String digest = save(text);

        StringBuffer log = new StringBuffer();
        Process p = Runtime.getRuntime().exec("./arkref.sh -input demo/" + digest + ".txt", null, arkref);
        p.waitFor();

        log.append(System.currentTimeMillis() + " : " + p.exitValue());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            log.append(line);
        }
        bufferedReader.close();
        
        save(digest, log.toString(), "log");

        return digest;
    }

    @GET
    @Path("tagged")
    @Produces(MediaType.TEXT_XML)
    public String tagged(@QueryParam("cid") String digest) throws FileNotFoundException, IOException {
        return "<?xml version='1.0'?><root>\n" + load(digest, "tagged") + "\n</root>";
    }

    @GET
    @Path("text")
    @Produces(MediaType.TEXT_PLAIN)
    public String text(@QueryParam("cid") String digest) throws FileNotFoundException, IOException {
        return load(digest, "txt");
    }
    
    @GET
    @Path("osent")
    @Produces(MediaType.TEXT_PLAIN)
    public String osent(@QueryParam("cid") String digest) throws FileNotFoundException, IOException {
        return load(digest, "osent");
    }
    
    @GET
    @Path("parse")
    @Produces(MediaType.TEXT_PLAIN)
    public String parse(@QueryParam("cid") String digest) throws FileNotFoundException, IOException {
        return load(digest, "parse");
    }
    
    @GET
    @Path("sst")
    @Produces(MediaType.TEXT_PLAIN)
    public String sst(@QueryParam("cid") String digest) throws FileNotFoundException, IOException {
        return load(digest, "sst");
    }
    
    @POST
    @Path("digest")
    @Produces(MediaType.TEXT_PLAIN)
    public String digest(@FormParam("text") String text) {
        StandardStringDigester digester = new StandardStringDigester();

        digester.setAlgorithm("SHA-1");
        digester.setSaltSizeBytes(0);
        digester.setIterations(1);

        return digester.digest(text).replace('/', '-').replace('+', '_');        
    }

    public File file(String digest, String extension) {
        return new File(arkref, "./demo/" + digest + "." + extension);
    }

    public String save(String text) throws IOException {
        String digest = digest(text);
        
        save(digest, text, "txt");
        
        return digest;
    }
        
    
    public String save(String digest, String text, String extension) throws IOException {

        BufferedWriter out = new BufferedWriter(new FileWriter(file(digest, extension)));
        out.write(text);
        out.close();

        return digest;
    }

    public String load(String digest, String extension) throws FileNotFoundException, IOException {
        StringBuffer buffer = new StringBuffer();
        BufferedReader in = new BufferedReader(new FileReader(file(digest, extension)));
        String line;
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        in.close();
        
        return buffer.toString();
    }
}
