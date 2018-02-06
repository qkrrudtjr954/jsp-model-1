<%@page import="java.util.Arrays"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="dto.CustUserBean"%>
<%@page import="java.util.List"%>
<%@page import="dao.CustUserManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


</head>
<body>
<%!
public boolean isNull(String str){
	// null이면 true return
	return (str==null || str.trim().equals(""))? true : false;
}
%>


<%
String command = request.getParameter("command");

CustUserManager manager = new CustUserManager();

List<CustUserBean> list = null;

if(command.equals("list")){
	list = manager.getCustUserList();
	
	request.setAttribute("custlist", list);
	
	pageContext.forward("custuserlist.jsp");
	
}else if(command.equals("add")){
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	
	CustUserBean user = new CustUserBean(id, name, address);
	
	boolean result = manager.addCustUser(user);
	
	if(result){
		System.out.println("insert success.");
	}
	
	response.sendRedirect("custusercontrol.jsp?command=list");
	
}else if(command.equals("muldel")){
	// 삭제 
	String ids[] = request.getParameterValues("delcheck");
	
	for(int i=0; i<ids.length; i++){
		System.out.println(ids[i]);
	}
	
	if(ids==null || ids.length == 0){
		JOptionPane.showMessageDialog(null, "id를 한개 이상 체크 하세요.");
	}else{
		boolean isSuccess = manager.deleteCustuser(ids);
		
		/* if(!isSuccess){
			JOptionPane.showMessageDialog(null, "삭제할 수 없습니다.");
		}else{
			JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
		} */
	}
	
	response.sendRedirect("custusercontrol.jsp?command=list");
}else if(command.equals("detail")){
	String id = request.getParameter("id");
	
	CustUserBean user = manager.getUserById(id);
	
	if(user!=null){
		request.setAttribute("custuser", user);		
	}
	
	pageContext.forward("custuserdetail.jsp");
	
}else if(command.equals("updateBf")){
	String id = request.getParameter("id");
	
	CustUserBean user = manager.getUserById(id);
	System.out.println("updateBf : "+user);
	request.setAttribute("custuser", user);
	
	pageContext.forward("custuserupdate.jsp");
	
}else if(command.equals("update")){
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	
	CustUserBean user = new CustUserBean(id, name, address);
	
	boolean result = manager.updateCustUser(user);
	
	if(result){
		System.out.println("update success.");
	}
	
	response.sendRedirect("custusercontrol.jsp?command=detail&id="+id);
}
%>



</body>
</html>