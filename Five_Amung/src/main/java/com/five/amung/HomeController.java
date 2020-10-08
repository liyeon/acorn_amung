package com.five.amung;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.five.amung.popup.dao.PopupDao;
import com.five.amung.popup.dto.PopupDto;

@Controller
public class HomeController {
	
	@Autowired
	private PopupDao popupDao;
	
	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		
		PopupDto dto =popupDao.getData();
		request.setAttribute("dto", dto);
		 
		//==== 팝업창을 띄울지 여부 =======
		boolean canPopup=true;
		Cookie[] cooks=request.getCookies();
		if(cooks!=null){
			for(Cookie tmp:cooks){
				//canPopup 이라는 키값으로 저장된 쿠키가 존재하면 
				if(tmp.getName().equals("canPopup")){
					//팝업을 띄우지 않도록 한다.
					canPopup=false;
				}
			}
		}
		request.setAttribute("canPopup", canPopup);
		
		return "home";
	}
}