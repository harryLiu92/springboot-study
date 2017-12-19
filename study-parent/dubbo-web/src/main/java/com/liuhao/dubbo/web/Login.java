package com.liuhao.dubbo.web;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.liuhao.api.LoginService;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage(value = "struts-default")
@Namespace("/")
@Results({ @Result(name="success",location="/login_ok.html")})
public class Login extends ActionSupport {
	private static final long serialVersionUID = 1L;

	// dubbo注入交给spring管理了autowired,resource就可以了
	@Autowired
	private LoginService loginService;

	@Action(value="login")
	public String execute() throws Exception {
	    System.out.println("---start----");
	    String result = loginService.login("liuhao", String.valueOf(System.currentTimeMillis() / 1000));
	    System.out.println("---end----"+result);
		return "success";
	}
	
	@Action(value="activemq")
	public String activemq() throws Exception {
	    System.out.println("---comming----");
	    loginService.login("liuhao", String.valueOf(System.currentTimeMillis() / 1000));
	    System.out.println("---going----");
	    return "success";
	}
	   
//    @Autowired
//    private JmsTemplate activeMqJmsTemplate;
//
//    /**
//     * 发送消息.
//     * @param mail 
//     */
//    public void sendMessage(final MailParam mail) {
//        activeMqJmsTemplate.send(new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                return session.createTextMessage(JSONObject.toJSONString(mail));
//            }
//        });
//        
//    }
	
}
