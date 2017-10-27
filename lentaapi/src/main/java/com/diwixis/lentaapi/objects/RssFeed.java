package com.diwixis.lentaapi.objects;

import com.diwixis.lentaapi.objects.News;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Diwixis on 25.10.2017.
 */
@Root(name = "rss", strict = false)
public class RssFeed {

    @ElementList(name="item", inline=true)
    @Path("channel")
    private List<News> news;

    public List<News> getNews(){
        return news;
    }
}
