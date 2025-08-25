<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Guard>
<jsp:forward page='/LoginForm.jsp'/>
</tm:Guard>
<tm:Module name='HOME'></tm:Module>

<jsp:include page='/MasterPageTopSection.jsp' />

<h1>Welcome to style three</h1>

<jsp:include page='/MasterPageBottomSection.jsp' />
