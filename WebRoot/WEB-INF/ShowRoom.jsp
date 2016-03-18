<%@ page language="java" import="java.util.*,com.ffx.domain.Room,com.ffx.domain.Messsage" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ShowRoom.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  <% Room room = (Room)application.getAttribute("al");
  	ArrayList<Messsage> messsage_al = (ArrayList<Messsage>)request.getAttribute("messsage");
  	int pageNow = (Integer)request.getAttribute("pageNow");
	int pageCount = (Integer)request.getAttribute("pageCount");
  %>
  <table align ="center" border="1px" cellpadding="1px">
   <tr>
		<td>录取通知书编号</td>
		<td>学生姓名</td>
		<td>性别</td>
		<td>楼栋</td>
		<td>寝室号</td>
		<td>宿舍费用</td>
		<td>学号</td>
		<td>籍贯</td>
		<td>班级代号</td>
		<td>专业</td>
		<td>学院</td>
		<td>统一识别码</td>
	</tr>
	<tr>
		<td><%=room.getBookid()%></td>
		<td><%=room.getStudentname()%></td>
		<td><%=room.getMale()%></td>
		<td><%=room.getDnumber()%></td>
		<td><%=room.getRoomnumber()%></td>
		<td><%=room.getPrice()%></td>
		<td><%=room.getStudentid()%></td>
		<td><%=room.getPlace()%></td>
		<td><%=room.getClassname()%></td>
		<td><%=room.getMajor()%></td>
		<td><%=room.getCollage()%></td>
		<td><%=room.getUniqueid()%></td>
	</tr>
	</table>
	<h1>留言板</h1>
	<% for(int i=0;i<messsage_al.size();i++){
		Messsage messsage = (Messsage)messsage_al.get(i);
	%>
	第<%=messsage.getMesssageid()%>条留言<br/>
	楼栋号:<%=messsage.getDnumber() %><br/>
	宿舍号:<%=messsage.getRoomnumber() %><br/>
	留言者:<%=messsage.getStudentnumber() %><br/>
	内容:<div style="width:200px; height:100px;border:solid 1px black"><%=messsage.getContent()%></div><br/>
	<%}%>
	<br/>
	   <h1>请在下面留言</h1>
	<form name ="messsage" action="MesssageCrud" method="post">
		第<input type="text" name="messsageid"/>条留言<br/>
		楼栋号:<input type="text" name="dnumber"/><br/>
		寝室号:<input type="text" name="roomnumber"/><br/>
		留言者:<input type="text" name="studentnumber"/><br/>
		留言内容:<br/><textarea name="content" style="width:200px; height:100px"></textarea><br/><br/>
		<input type="submit" value="提交"/>
	</form>
    	<a href = "GetMesssage?pageNow=1">首页</a>
    	
    	<%
    	if(pageNow != 1) {
    		%>
    		
    		<a href = "GetMesssage?pageNow=<%=(pageNow - 1)%>">上一页</a>
    		<% 
    	}
    	for(int i = pageNow+1;i<=(pageCount<(pageNow + 5)?pageCount:(pageCount + 5));i++) {
    		%>
    		<a href="GetMesssage?pageNow=<%=i%>">[<%=i%>]</a>
    		<% 
    	}
    	if(pageNow != pageCount) {
    		%>
    		<a href ="GetMesssage?pageNow=<%=pageCount%>">尾页</a>
    		<%
    	}
     %>
    
  </body>
</html>
