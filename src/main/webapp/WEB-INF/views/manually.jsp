<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bank cards details</title>
        <style>
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <h2>Add a new card manually</h2>
        <sf:form method="POST" modelAttribute="card">
            <table cellspacing="6" cellspadding="4" style="text-align:left;">
                <tr>
                    <th><label for="bank">Bank:</label></th>
                    <td>
                        <sf:select path="bankName">
                            <sf:option value="American Express" label="American Express" />
                            <sf:option value="HSBC Canada" label="HSBC Canada" />
                            <sf:option value="Royal Bank of Canada" label="Royal Bank of Canada" />
                        </sf:select>
                    </td>
                </tr>
                <tr>
                    <th><label for="number">Number:</label></th>
                    <td>
                        <sf:input path="cardNumber" maxlength="16" id="cardNumber" />
                        <sf:errors path="cardNumber" cssClass="error">*</sf:errors>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="date">Date expire:</label></th>
                        <td>
                        <sf:input path="dateExpire" maxlength="10" size="10" id="dateExpire" placeholder="mm/dd/yyyy" />
                        <sf:errors path="dateExpire" cssClass="error">*</sf:errors> 
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <br />
                            <input type="submit" name="action" value="back"/>
                            <input type="submit" name="action" value="save"/>
                        </td>
                    </tr>
                </table>
        </sf:form>
    </body>
</html>