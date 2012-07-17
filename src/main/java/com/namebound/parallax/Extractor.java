package com.namebound.parallax;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import com.gravity.goose.*;

@Path("article")
@XmlRootElement 
public class Extractor {

    public String title, text, image, domain, link, shortLink;
    
    public Extractor() {
    }
    
    @Path("/info")
    @GET
    @Produces(MediaType.TEXT_XML)
    public Extractor getArticleData(@QueryParam("url") String url) {
        Goose goose = new Goose(new Configuration());
        Article a = goose.extractContent(url);
        
        title = a.title();
        text = a.cleanedArticleText();
        domain = a.domain();
        link = a.canonicalLink();

        if (a.topImage() != null) {
            image = a.topImage().getImageSrc();
        }
        
        
        text = text.replaceAll("\r\n", "\n");
        text = text.replaceAll("\r", "\n");
        text = text.replaceAll("\n", "<br />");
        
        return this;
    }

}
