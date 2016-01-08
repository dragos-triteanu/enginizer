package com.startup.enginizer.services;

import com.startup.enginizer.model.entities.User;
import com.startup.enginizer.repository.LandingPageRepository;
import com.startup.enginizer.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dragos.triteanu on 11/8/15.
 */
@Service
public class LandingPageService {

    @Autowired
    private LandingPageRepository landingPageRepository;

    public void updateWywywigForUser(final String wysiwygText , final int whatUsersSee){
        landingPageRepository.updateWysiwygHtmlForConsultants(wysiwygText, whatUsersSee);
    }

    public String getHTMLForWysiwyg() {
        User user = SessionUtils.GetCurrentUser();
        String htmlString = "";
        htmlString = landingPageRepository.getHTMLForWysiwyg(1);
        return htmlString;
    }

    public String getHtmlForUserByType(int userType) {
        return landingPageRepository.getHTMLForWysiwyg(userType);
    }
}
