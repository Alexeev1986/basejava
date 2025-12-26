<%@ page import="com.urise.webapp.model.TextSection" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationsSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request" />
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/edit.png"></a> </h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
            <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    </p>
    <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.Section>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
            <p style="font-size: 22px; font-weight: bold;">
                <%=sectionEntry.getKey().getTitle()%>
            </p>
            <c:choose>
                <c:when test="${type=='PERSONAL' || type =='OBJECTIVE'}">
                    <p>
                        <%=((TextSection) section).getContent()%>
                    </p>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                    <c:forEach var="item" items="<%=((ListSection) section).getItems()%>">
                        <li>${item}</li>
                    </c:forEach>
                </c:when>
            </c:choose>
        <c:choose>
            <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                <c:forEach var="organization" items="<%=((OrganizationsSection) section).getOrganizations()%>">
                    <jsp:useBean id="organization" type="com.urise.webapp.model.Organization"/>
                    <c:choose>
                        <c:when test="${empty organization.link.url}">
                            <h4>
                                <%=organization.getLink().getName()%>
                            </h4>
                        </c:when>
                        <c:otherwise>
                            <h4>
                                ${organization.link.name}&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="${organization.link.url}"><%=organization.getLink().getUrl()%></a>
                            </h4>
                        </c:otherwise>
                    </c:choose>
                    <div style="max-width: 800px; margin: 0;">
                    <table style="width: 100%; border-collapse: collapse;">
                    <c:forEach var="position" items="${organization.positions}">
                        <jsp:useBean id="position" type="com.urise.webapp.model.Position"/>
                        <tr>
                            <td style="padding: 8px; font-size: 14px; color: #b3b3b3; text-align: left; vertical-align: top;">
                                    ${position.fullDate}
                            </td>
                            <td style="padding: 8px; font-size: 16px; font-weight: bold; color: #ffffff; text-align: left; vertical-align: top;">
                                    ${position.title}
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="padding: 12px 8px 16px; font-size: 15px; font-style: italic; color: #cccccc; text-align: right; border-top: 1px solid #444;">
                                    ${position.description}
                            </td>
                        </tr>
                    </c:forEach>
                    </table>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
