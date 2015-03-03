<%@page import="javax.smartcardio.Card"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cnm" uri="/WEB-INF/taglib/cardnummask.tld" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bank cards details</title>
    </head>
    <body>
        <h2>Listing of all cards</h2>
        <sf:form method="POST">
            <c:if test="${not empty cards}">
                <table cellspacing="6" cellspadding="4" style="text-align:left;">
                    <thead>
                        <tr>
                            <th>Bank</th>
                            <th>Card Number</th>
                            <th>Date Expire</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cards}" var="card">
                            <tr>
                                <td>${card.bankName}</td>
                                <td>
                                    <cnm:cardnummask value='${card.cardNumber}'></cnm:cardnummask></td>
                                <td>
                                    <fmt:formatDate pattern="MMM-yyyy" value="${card.dateExpire}" />
                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <br />
            <input type="submit"  name="action" value="manually"/>
            <input type="submit" name="action" value="upload" />
        </sf:form>
    </body>
</html>