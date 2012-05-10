package org.tute.sure.utils;

import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoggerInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -5128719719294661592L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		String userName=ServletActionContext.getRequest().getRemoteUser();
		MDC.put("user", userName);		
		return arg0.invoke();
	}

}
