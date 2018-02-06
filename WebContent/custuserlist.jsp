<%@page import="dto.CustUserBean"%>
<%@page import="java.util.List"%>

<%
List<CustUserBean> list = (List<CustUserBean>)request.getAttribute("custlist");
%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
function deletechecks(e){
	var x = document.getElementsByName('delcheck');
	var len = x.length;
	
	for(var i=0; i<len; i++){
		x[i].checked = e;
	}
}
</script>

</head>
<body>

<div align="center">

	<form action="custusercontrol.jsp" method="get">
		<!-- multiple delete -->
		<input type="hidden" name="command" value="muldel">
		
		<table style="width:700px;">
			<col width="100px">
			<col width="300px">
			<col width="300px">
			
			<tr>
				<td height="2" bgcolor="#0000ff" colspan="3"></td>
			</tr>
			
			<tr bgcolor="skyblue">
				<td bgcolor="yellow" align="center">
					<input type="checkbox" name="alldel" onclick="deletechecks(this.checked)">
				</td>
				
				<td bgcolor="pink" align="center">
					id
				</td>
				
				<td bgcolor="lightgreen" align="center">
					name
				</td>
			</tr>
			
			<tr>
				<td height="2" bgcolor="#0000ff" colspan="3"></td>
			</tr>
			
			<%
			if(list.size()==0){
			%>
			<tr bgcolor="skyblue">
				<td colspan="3" align="center">No List</td>
			</tr>
			<%
			}else{
				for(CustUserBean cust : list){
			%>
			<tr bgcolor="skyblue" onclick="moveToDetail('<%=cust.getId()%>')">
				<td bgcolor="yellow" align="center">
					<input type="checkbox" name="delcheck" value="<%=cust.getId()%>">
				</td>
				
				<td bgcolor="pink" align="center">
					<%=cust.getId() %>
				</td>
				
				<td bgcolor="lightgreen" align="center">
					<a href="custusercontrol.jsp?command=detail&id=<%=cust.getId() %>" style="text-decoration:none;">
						<%=cust.getName() %>
					</a>
				</td>
			</tr>
			<%
				}
			}
			%>
			
			<tr>
				<td align="center" height="1" bgcolor="lightred" colspan="3">
					<input type="submit" value="Delete">
				</td>
			</tr>
			
			<tr>
				<td align="center" height="1" bgcolor="lightblue" colspan="3">
					<a href="custadd.jsp">Add</a>
				</td>
			</tr>
		
		</table>
	</form>

</div>

<script type="text/javascript">
function moveToDetail(id) {
	location.href="custusercontrol.jsp?command=detail&id="+id;
}
</script>

</body>
</html>