package com.diwixis.lentaapi.objects;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Diwixis on 25.10.2017.
 */
@Root(name = "item", strict = false)
public class News {
        @Element(name = "title")
        private String title;

        @Element(name = "link")
        private String link;

        @Element(name = "description")
        private String description;

        @Element(name = "enclosure", required = false)
        private Enclosure enclosure;

        @Element(name = "guid")
        private String guid;

        @Element(name = "pubDate")
        private String pubDate;

        @Element(name = "category")
        private String category;

        private static class Enclosure {

                @Attribute(name = "url")
                private String url;

                @Attribute(name = "length")
                private long length;

                @Attribute(name = "type")
                private String type;
        }

        public String getUrl(){
                return enclosure.url;
        }

        public Enclosure getEnclosure() {
                return enclosure;
        }

        public String getTitle() {
                return title;
        }

        public String getLink() {
                return link;
        }

        public String getDescription() {
                return description;
        }

        public String getGuid() {
                return guid;
        }

        public String getPubDate() {
                return pubDate;
        }

        public String getCategory() {
                return category;
        }
}
