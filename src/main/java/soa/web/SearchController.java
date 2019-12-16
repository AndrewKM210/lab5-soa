package soa.web;

import java.util.HashMap;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SearchController {

  private final ProducerTemplate producerTemplate;

  @Autowired
  public SearchController(ProducerTemplate producerTemplate) {
    this.producerTemplate = producerTemplate;
  }

  @RequestMapping("/")
  public String index() {
    return "index";
  }


  @RequestMapping(value = "/search")
  @ResponseBody
  public Object search(@RequestParam("q") String q, @RequestParam(name = "maxTweets", defaultValue = "10", required = false) int maxTweets) {
    // Instead of returning only the header CamelTwitterKeywords, return also
    // the CamelTwitterCount header that overrides the option of count which 
    // sets the max twitters that will be returned.
    HashMap<String, Object> headers = new HashMap<>();
    headers.put("CamelTwitterKeywords", q);
    headers.put("CamelTwitterCount", maxTweets);
    return producerTemplate.requestBodyAndHeaders("direct:search", "", headers);
  }
}