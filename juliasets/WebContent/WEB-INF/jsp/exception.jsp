<%@ page import = "java.io.*,java.util.*" %>
<%@ page import = "javax.servlet.*,java.text.*" %>
<%@ include file="includeTags.jsp" %>

<c:set var="currentTime">
	<%
        Date dNow = new Date( );
        SimpleDateFormat ft =
        new SimpleDateFormat ("MM/dd/yyyy 'at' hh:mm:ss a zzz");
        out.print( ft.format(dNow));
   %>
</c:set>

<p>Error Message: ${errorMessage}</p>
<p>Error time: ${currentTime}</p>
<p>Please reach out to 
	<a href="mailto:dssievewright@gmail.com?subject=Julia%20Sets%20Exception&body=%0D%0A%0D%0AThe%20error%20message%20was%20'${errorMessage}'%20and%20the%20time%20was%20${currentTime}">
		dssievewright@gmail.com
	</a> 
   to provide feedback or ask questions.  Please include the error message, time, and a description of what happened prior to the error.</p>