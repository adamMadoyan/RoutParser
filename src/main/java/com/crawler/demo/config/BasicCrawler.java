/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.crawler.demo.config;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.crawler.demo.model.TreePage;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author Yasser Ganjisaffar
 */
public class BasicCrawler extends WebCrawler {

    private static final Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav" +
            "|avi|mov|mpeg|ram|m4v|pdf|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    public static List<TreePage> result = new ArrayList<>();

    /**
     * You should implement this function to specify whether the given url
     * should be crawled or not (based on your crawling logic).
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches() && url.getURL().startsWith(referringPage.getWebURL().getURL());
    }

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    @Override
    public void visit(Page page) {
        WebURL webURL = page.getWebURL();
        isPage(String.valueOf(webURL.getParentDocid()));
        String parentDocId = String.valueOf(webURL.getParentDocid());
        if (webURL.getParentDocid() == 0) {
            parentDocId = "#";
        }
        TreePage treePage = new TreePage(String.valueOf(webURL.getDocid()), parentDocId, webURL.getURL(), true);
        result.add(treePage);
    }

    private void isPage(String docId) {
        result.stream().filter(treePage -> treePage.getId().equals(docId)).findFirst().ifPresent(treePage -> treePage.setType("folder"));
    }
}