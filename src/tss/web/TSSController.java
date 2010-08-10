package tss.web;

/**
 *
 * @author Dong Won Kim
 */
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

public class TSSController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());
   
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String view = "tss";
        
        logger.info("Returning " + view + " view");

        return new ModelAndView(view);
    }
}