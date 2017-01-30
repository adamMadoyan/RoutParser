package com.crawler.demo.web;

import com.crawler.demo.config.BasicCrawler;
import com.crawler.demo.model.TreePage;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class IndexController {

    private CrawlController controller;

    public IndexController(@Value("${storage.path}") String storagePath) {
        try {
            File dataOutput = new File(storagePath);
            if (!dataOutput.exists() || !dataOutput.isDirectory()) {
                dataOutput.mkdirs();
            }

            CrawlConfig config = new CrawlConfig();
            config.setCrawlStorageFolder(storagePath);
            config.setPolitenessDelay(1000);
            config.setMaxDepthOfCrawling(20);
            config.setMaxPagesToFetch(1000);
            config.setIncludeBinaryContentInCrawling(false);
            config.setResumableCrawling(false);
            config.setPolitenessDelay(10);
            PageFetcher pageFetcher = new PageFetcher(config);
            RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
            RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            e.printStackTrace();
//            Do nothing.
        }
    }

    @PostMapping()
    public List<TreePage> test(@RequestBody String url) {
        // TODO check later
        if (controller == null) {
            return null;
        }
        controller.addSeed(url);
        controller.start(BasicCrawler.class, 1);

        controller.waitUntilFinish();

        List<TreePage> result = new ArrayList<>(BasicCrawler.result);

        // TODO replace with interface implementation
        BasicCrawler.result.clear();
        return result;
    }

}
