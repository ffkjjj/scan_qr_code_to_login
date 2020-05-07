package cf.zhul.scanqrcodetologin.filter;

import cf.zhul.scanqrcodetologin.common.util.JsonUtils;
import cf.zhul.scanqrcodetologin.exception.HttpBaseException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class ResultExceptionTranslationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        try {
            fc.doFilter(request, response);
        } catch (HttpBaseException ex) {
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(createHttpBaseExceptionInfo(ex));
            response.getWriter().flush();
        }
    }

    private String createHttpBaseExceptionInfo(HttpBaseException ex) {
        ObjectNode objectNode = JsonUtils.getObjectNode();
        objectNode.put("code", ex.getCode());
        objectNode.put("msg", ex.getMsg());
        return objectNode.toString();
    }
}