<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bank cards details</title>
    </head>
    <body>
        <h2>Add cards from a CSV file</h2>
        <sf:form method="POST" enctype="multipart/form-data">
            <table cellspacing="6" cellspadding="4" style="text-align:left;">
                <tr>
                    <td>
                        <input type="file" name="uploadControl" value="uploadControl" enctype="multipart/form-data" />
                        <input type="submit" name="action" value="upload"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <br />
                        <input type="submit" name="action" value="back"/>
                    </td>
                </tr>
            </table>
        </sf:form>
    </body>
</html>
