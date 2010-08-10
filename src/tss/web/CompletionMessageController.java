/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tss.web;

/**
 *
 * @author Dong Won Kim
 */
import tss.web.*;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import tss.service.CompletionMessage;

public class CompletionMessageController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());

    private CompletionMessage completionMessage;
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        String view = "completionMessage";
        logger.info("Returning " + view + " view");
        
        return new ModelAndView(view, "completionMessage", completionMessage.getMessage());
    }

    public CompletionMessage getCompletionMessage() {
        return completionMessage;
    }

    public void setCompletionMessage(CompletionMessage completionMessage) {
        this.completionMessage = completionMessage;
    }

}