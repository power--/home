package com.goparty.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goparty.data.model.OAuthAccessToken;
import com.goparty.data.repository.IOAuthAccessTokenRepository;



@Controller
@RequestMapping(value="/accesstoken")
public class AccessTokenViewController {

	@Resource
	private IOAuthAccessTokenRepository accessTokenRepository;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView accessTokenListPage() {
		ModelAndView mav = new ModelAndView("accessToken-list");
		Iterable<OAuthAccessToken> accessTokenList = accessTokenRepository.findAll();
		mav.addObject("accessTokenList", accessTokenList);
		return mav;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteOAuthAccessToken(@PathVariable String id,
			final RedirectAttributes redirectAttributes)  {
		
		ModelAndView mav = new ModelAndView("redirect:/accessToken/list");		
		
		OAuthAccessToken accessToken = accessTokenRepository.findOne(id);
		accessTokenRepository.delete(id);
		String message = "The accessToken "+accessToken.getId()+" was successfully deleted.";
		
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}	
}
