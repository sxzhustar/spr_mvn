package org.bluedon.lightweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mapController")
public class MapController {

	@RequestMapping("/toMap")
	public ModelAndView toMap() {
		return new ModelAndView("map/map");
	}
}
