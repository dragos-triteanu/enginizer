package com.startup.enginizer.controller;

import com.startup.enginizer.model.dto.HtmlForWysiwyg;
import com.startup.enginizer.services.LandingPageService;
import com.startup.enginizer.utils.MessageTranslator;
import com.startup.enginizer.utils.SessionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/home")
public class LandingPageController {
	private static final Logger LOG = LoggerFactory.getLogger(LandingPageController.class);
	
    @Autowired
    private LandingPageService landingPageService;

	@Autowired
	private MessageTranslator messageTranslator;

    @RequestMapping(method = RequestMethod.GET)
	public String howItWorks(ModelMap modelMap){
		SessionUtils.populateModelWithAuthenticatedRole(modelMap);

		String htmlForWysiwyg = landingPageService.getHTMLForWysiwyg();
		String htmlForWysiwygEscaped = StringEscapeUtils.escapeHtml4(htmlForWysiwyg);
		LOG.debug("Found HTML=\n"+ htmlForWysiwygEscaped);
		modelMap.addAttribute("htmlForWysiwyg",htmlForWysiwyg);
		modelMap.addAttribute("htmlForWysiwygEscaped",htmlForWysiwygEscaped);
		return "homePage";
	}

	@RequestMapping(value="/updatePage", method = RequestMethod.POST)
	public String updatePage(@Valid HtmlForWysiwyg htmlForWysiwyg,
							 BindingResult bindingResult,
                             ModelMap modelMap){

		if(bindingResult.hasErrors()){
			SessionUtils.populateModelWithAuthenticatedRole(modelMap);
			List<String> errors = messageTranslator.getErrors(bindingResult);
			modelMap.addAttribute("errors", errors);
			return "errors";
		}
		LOG.debug("Received following HTML file :\n "+htmlForWysiwyg.getNewHtml());
        landingPageService.updateWywywigForUser(htmlForWysiwyg.getNewHtml(),htmlForWysiwyg.getWhatUsersSee());
		return "redirect:/home";
	}

    @RequestMapping(value = "/getHTMLForUserType" , method = RequestMethod.POST)
    public ResponseEntity<String> getHtmlForUserType(@RequestParam("userType") int userType,BindingResult result){

        String htmlForUserByType = landingPageService.getHtmlForUserByType(userType);
        ResponseEntity<String> entity = new ResponseEntity<String>(htmlForUserByType, HttpStatus.OK);
        return entity;
    }
}
