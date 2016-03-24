package pers.lwm.fm.web.Search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.lwm.fm.user.model.User;
import pers.lwm.fm.user.service.UserService;

import java.util.List;


@Controller
public class TestController {
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String printWelcome() {
		List<User> userList = userService.findAll();
		return "userCount:-->" + userList.size();
	}
}