package ch.hcuge.demo.tenant;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TenantNameFilter extends HandlerInterceptorAdapter {

//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String authToken = request.getHeader(this.tokenHeader);
//        String tenantId = jwtTokenUtil.getTenantIdFromToken(authToken);
//        TenantContext.setCurrentTenant(tenantId);
        String tenant = request.getHeader("x-tenant");
        ThreadLocalStorage.setTenantName(tenant);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ThreadLocalStorage.clear();
    }
}