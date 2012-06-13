package com.namebound.parallax;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() throws IOException, InterruptedException {

        Process p = Runtime.getRuntime().exec("./arkref.sh -input demo/test.txt", null, new File("/Users/laurian/Projects/Parallax/src/main/webapp/WEB-INF/arkref"));
        p.waitFor();
        System.out.println(p.exitValue());
        BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = bri.readLine()) != null) {
            System.out.println(line);
        }
        bri.close();


        return "ok?";
    }
}
