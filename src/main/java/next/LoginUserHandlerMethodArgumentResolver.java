package next;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import next.controller.LoginUser;
import next.model.User;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver  {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
//		return false;
//		return parameter.getParameterType().equals(User.class);
		return parameter.hasParameterAnnotation(LoginUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
        for(Iterator<String> iterator = webRequest.getParameterNames(); iterator.hasNext();){
            String key = iterator.next();
            paramMap.put(key, webRequest.getParameter(key));
        }
		
//		String userId = paramMap.get("userId");
//		String password = paramMap.get("password");
//		String name = paramMap.get("name");
//		String email = paramMap.get("email");
        
		String userId = "1userId";
		String password = "1password";
		String name = "1name";
		String email = "1email";
		
		User loginUser = new User(userId, password, name, email);
		
		return loginUser;
	}

	@Override
	public String toString() {
		return "LoginUser [getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
