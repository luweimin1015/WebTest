package pers.lwm.fm.web.Search;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.lwm.base.util.HttpUtil;
import pers.lwm.base.util.StringUtil;
import pers.lwm.fm.spider.service.SpiderService;
import pers.lwm.fm.spider.service.impl.SpiderServiceImpl;
import pers.lwm.fm.user.model.User;
import pers.lwm.fm.user.service.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class TestController {
	@Autowired
	private UserService userService;

	@Autowired
	private SpiderService spiderServiceImpl;

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String printWelcome() {
		List<User> userList = userService.findAll();
		return JSON.toJSONString(userList);
	}

	@ResponseBody
	@RequestMapping(value = "/mv", method = RequestMethod.GET)
	public String getMovie() {
		String result = "";
		try {
			result = spiderServiceImpl.getMv(2);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return JSON.toJSONString(result);
	}

	/**
	 * 爬取XXX的中国电影DEMO
	 * @param args
     */
	public static void main(String[] args) {
		String result = "";
		SpiderService spiderServiceImpl = new SpiderServiceImpl();
		try {
			result = spiderServiceImpl.getMv(3);
			result = result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1);
			JSONObject json = JSON.parseObject(result);
			String html = json.getJSONObject("value").getString("listHTML");
			System.out.println(html);
			Document doc = Jsoup.parse(html);
			Elements links = doc.getElementsByTag("a");
			System.out.println(links.size());
			int i = 0;
			String text;
			for (Element link : links
				 ) {
				text = link.text().trim();
				if (!StringUtil.isNullOrEmpty(text)){
					System.out.println(i + "-->" + link.text());
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}