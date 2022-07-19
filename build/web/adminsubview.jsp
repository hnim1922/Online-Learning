<%-- 
    Document   : adminsubview
    Created on : Jun 18, 2021, 9:51:51 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');

            .tab {
                overflow: hidden;
                border: 1px solid #ccc;
                background-color: #f1f1f1;
            }

            .modal_fix {
                display: none;
                position: fixed;
                z-index: 1;                
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;               
                background-color: rgb(0, 0, 0);                
                background-color: rgba(0, 0, 0, 0.4);
                padding-top: 60px;
            }
            
            .modal_fix table{
                padding: 20px;
            }
            /* Style the buttons inside the tab */
            .tab button {
                background-color: inherit;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 14px 16px;
                transition: 0.3s;
                font-size: 17px;
            }

            /* Change background color of buttons on hover */
            .tab button:hover {
                background-color: #ddd;
            }

            /* Create an active/current tablink class */
            .tab button.active {
                background-color: #ccc;
            }

            /* Style the tab content */
            .tabcontent {
                display: none;
                padding: 6px 12px;
                border: 1px solid #ccc;
                border-top: none;
            }

            /* Style the close button */
            .topright {
                float: right;
                cursor: pointer;
                font-size: 28px;
            }

            .topright:hover {color: red;}

            .button {
                font: bold 15px Arial;
                text-decoration: none;
                background-color: #EEEEEE;
                color: #333333;
                padding: 2px 6px 2px 6px;
                border-top: 1px solid #CCCCCC;
                border-right: 1px solid #333333;
                border-bottom: 1px solid #333333;
                border-left: 1px solid #CCCCCC;
            }
        </style>
        <link rel="stylesheet" href="style.css">

        <script>
            function openTab(evt, cityName) {
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablinks");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" active", "");
                }
                document.getElementById(cityName).style.display = "block";
                evt.currentTarget.className += " active";
            }

            function autoClickdefaultOpen() {
                document.getElementById("defaultOpen").click();
            }
            function autoClickChange() {
                document.getElementById("change").click();
            }
            function autoClickAddPrice() {
                document.getElementById("pricebox").click();
            }
            function autoClickAddDimen() {
                document.getElementById("dimenbox").click();
            }
            function confirmDelete() {
                var r = confirm("Are you sure you want to delete this?")
                return r;
            }
        </script>
    </head>
    <body>
        <h1>Subject Details</h1>
        <br>
        <div class="tab">

            <!--            id="defaultOpen" onfocus="autoClickdefaultOpen()" autofocus-->

            <c:if test="${!empty requestScope.INVALID}">
                <button class="tablinks" onclick="openTab(event, 'Overview'); document.getElementById('change').focus()" 
                        id="defaultOpen" onfocus="autoClickdefaultOpen()" autofocus>Overview</button>
            </c:if>
            <c:if test="${empty requestScope.INVALID}">
                <button class="tablinks" onclick="openTab(event, 'Overview')" >Overview</button>
            </c:if> 

            <!-- comment -->    

            <c:if test="${!empty requestScope.INVALIDPRICE}">
                <button class="tablinks" onclick="openTab(event, 'Price'); document.getElementById('pricebox').focus()" 
                        id="defaultOpen" onfocus="autoClickdefaultOpen()" autofocus>Price Package</button>
            </c:if>
            <c:if test="${empty requestScope.INVALIDPRICE}">
                <button class="tablinks" onclick="openTab(event, 'Price')">Price Package</button>
            </c:if>

            <!-- comment -->

            <c:if test="${!empty requestScope.INVALIDDIMEN}">
                <button class="tablinks" onclick="openTab(event, 'Dimension'); document.getElementById('dimenbox').focus()" 
                        id="defaultOpen" onfocus="autoClickdefaultOpen()" autofocus>Dimension</button>
            </c:if>
            <c:if test="${empty requestScope.INVALIDDIMEN}">
                <button class="tablinks" onclick="openTab(event, 'Dimension')">Dimension</button>
            </c:if>


        </div>

        <br>
        <!-- OVERVIEW BOX -->
        <div id="Overview" class="tabcontent">
            <span onclick="this.parentElement.style.display = 'none'" class="topright">&times</span>
            <c:if test="${!empty requestScope.SUBJECT}">
                <form  id="view" method="POST" >
                    <table>
                        <tr>
                            <td>Subject Name </td>
                            <td>${requestScope.SUBJECT.name}</td>
                        </tr>  
                        <tr> 
                            <td>Category </td>
                            <td>${requestScope.SUBJECT.category.categoryName}</td>
                        </tr>  
                        <tr>
                            <td>Feature Flag</td>
                            <td>${requestScope.SUBJECT.featureFlag}</td> 
                        </tr>
                        <tr>
                            <td>Publish Status</td>
                            <td>${requestScope.SUBJECT.status}</td> 
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td>
                                <textarea readonly>${requestScope.SUBJECT.information}</textarea> 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <c:if test="${empty requestScope.INVALID}">
                                    <button form="x" class="login-btn"
                                            onclick="document.getElementById('change-box').style.display = 'block'">Change</button>
                                </c:if>
                                <c:if test="${!empty requestScope.INVALID}">
                                    <button form="x" class="login-btn" onfocus="autoClickChange()" id="change" autofocus
                                            onclick="document.getElementById('change-box').style.display = 'block'">Change</button>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </form>
            </c:if>
            <c:if test="${empty requestScope.SUBJECT}">
                <h2>There is none information here!</h2>
            </c:if>
        </div>

        <div id="change-box" class="modal_fix"> 
            <form class="modal-content animate" action="AdminChangeSubject">
                <h1 class="modal-content animate" >Subject information</h1>                
                <input type="hidden" name="id" value="${requestScope.SUBJECT.subjectID}"/>
                <table>
                    <tr>
                        <td>Subject Name: </td>
                        <td>
                            <input type="text" name="name" value="${requestScope.SUBJECT.name}"/>
                            <font color="red">${requestScope.INVALID.nameError}</font>
                        </td>
                    </tr>
                    <tr>
                        <td>Category: </td>
                        <td>
                            <select name="category">
                                <c:forEach items="${requestScope.CATESLIST}" var="cate">
                                    <option value="${cate.categoryID}">${cate.categoryID} - ${cate.categoryName}</option>
                                </c:forEach>  
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>${requestScope.SUBJECTSLIST.featureFlag}</td>                        
                        <td>
                            <input type="checkbox" name="feature" value="feature" checked>
                            <label for="feature">Feature Subject</label>
                        </td>
                        <td>
                            Status:    
                            <select name="status">
                                <option value="true">Published</option>
                                <option value="false">Unpublished</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> Description:</td>
                        <td>
                            <textarea name="information" value="${requestScope.SUBJECT.information}" rows="5" cols="40">${requestScope.SUBJECT.information}</textarea>
                            <font color="red">${requestScope.INVALID.informationError}</font></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input class="login-btn" type="submit" value="submit"></td>
                        <td>                        
                            <button class="login-btn" type="reset" onclick="document.getElementById('change-box').style.display = 'none'">Cancel</button></td>
                    </tr>
                </table>
            </form>
        </div>
        <!-- END OVERVIEW BOX -->

        <!-- PRICE PACKAGE -->
        <div id="Price" class="tabcontent">
            <span onclick="this.parentElement.style.display = 'none'" class="topright">&times</span>
            <span>
                <c:if test="${empty requestScope.INVALIDPRICE}">
                    <button form="x" class="login-btn"
                            onclick="document.getElementById('add-pricebox').style.display = 'block'">Add new</button>
                </c:if>
                <c:if test="${!empty requestScope.INVALIDPRICE}">
                    <button form="x" onfocus="autoClickAddPrice()" id="pricebox" autofocus class="login-btn"
                            onclick="document.getElementById('add-pricebox').style.display = 'block'">Add new</button>
                </c:if>
            </span>
            <br>
            <c:if test="${!empty requestScope.PRICELIST}">
                <table border="1">
                    <thead>
                        <tr>
                            <td>ID</td>
                            <td>Name</td>
                            <td>Access Duration</td>
                            <td>Status</td>
                            <td>List Price</td>
                            <td>Sale Price</td>
                            <td>Description</td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.PRICELIST}" var="dto">
                            <tr>
                                <td>${dto.pricePackageID}</td>
                                <td>${dto.name}</td>
                                <td>${dto.accessDuration} month(s)</td>
                                <td>${dto.status}</td>
                                <td>${dto.listPrice}</td>
                                <td>${dto.salePrice}</td>
                                <td>${dto.description}</td>
                                <td> 
                                    <c:url value="AdminViewPrice" var="AdminViewPriceLink">
                                        <c:param name="priceid" value ="${dto.pricePackageID}"/>
                                        <c:param name="id" value ="${requestScope.SUBJECT.subjectID}"/>
                                    </c:url>
                                    <a href="${AdminViewPriceLink}" class="button">edit</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty requestScope.PRICELIST}">
                <h2>There is none information here!</h2>
            </c:if>
        </div>

        <div id="add-pricebox" class="modal_fix">
            <form action="AdminAddPrice" method="POST" class="modal-content animate">
                <input type="hidden" name="id" value="${requestScope.SUBJECT.subjectID}"/>
                <table>
                    <tr>
                        <td> Name:</td>
                        <td><input type="text" name="pricename" value="${param.pricename}"/>
                            <font color="red">${requestScope.INVALIDPRICE.nameError}</font>
                        </td>                            
                    </tr>
                    <tr>
                        <td> Access Duration: (MONTH)</td>
                        <td>
                            <select name="duration">
                                <option value="3">3 Months</option>
                                <option value="6">6 Months</option>
                                <option value="9">9 Months</option>
                                <option value="12">12 Months</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> Status:</td>
                        <td> 
                            <select name="status">
                                <option value="Active">Active</option>
                                <option value="Inactive">Inactive</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> List Price:</td>
                        <td><input type="number" name="listprice" value="${param.listprice}"/>
                            <font color="red">${requestScope.INVALIDPRICE.listpriceError}</font>
                        </td>
                    </tr>
                    <tr>
                        <td> Sale Price:</td>
                        <td><input type="number" name="saleprice" value="${param.saleprice}"/>
                            <font color="red">${requestScope.INVALIDPRICE.salepriceError}</font>
                        </td>
                    </tr>
                    <tr>
                        <td> Description:</td>
                        <td>
                            <textarea name="desc" value="${param.desc}" rows="5" cols="40"></textarea>
                            <font color="red">${requestScope.INVALIDPRICE.informationError}</font>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><input class="login-btn" type="submit" value="Add"></td>
                        <td>                        
                            <button class="login-btn" type="reset" onclick="document.getElementById('add-pricebox').style.display = 'none'">Cancel</button></td>
                    </tr>
                </table>
            </form>         
        </div>

        <c:if test="${!empty requestScope.requestPRICE}">
            <div id="change-pricebox">
                <form action="AdminChangePrice" method="POST" class="modal-content animate">
                    <input type="hidden" name="id" value="${requestScope.SUBJECT.subjectID}"/>
                    <input type="hidden" name="priceid" value="${requestScope.requestPRICE.pricePackageID}"/>

                    <table>
                        <tr>
                            <td> Name:</td>
                            <td><input type="text" name="updatepricename" value="${requestScope.requestPRICE.name}"/>
                                <font color="red">${requestScope.INVALIDUPDATEPRICE.nameError}</font>
                            </td>                            
                        </tr>
                        <tr>
                            <td> Access Duration: (MONTH)</td>
                            <td>
                                <select name="updateduration">
                                    <option value="3">3 Months</option>
                                    <option value="6">6 Months</option>
                                    <option value="9">9 Months</option>
                                    <option value="12">12 Months</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td> Status:</td>
                            <td> 
                                <select name="updatestatus">
                                    <option value="active">Active</option>
                                    <option value="inactive">Inactive</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td> List Price:</td>
                            <td><input type="number" name="updatelistprice" value="${requestScope.requestPRICE.listPrice}"/>
                                <font color="red">${requestScope.INVALIDUPDATEPRICE.listpriceError}</font>
                            </td>
                        </tr>
                        <tr>
                            <td> Sale Price:</td>
                            <td><input type="number" name="updatesaleprice" value="${requestScope.requestPRICE.salePrice}"/>
                                <font color="red">${requestScope.INVALIDUPDATEPRICE.salepriceError}</font>
                            </td>
                        </tr>
                        <tr>
                            <td> Description:</td>
                            <td>
                                <textarea name="updatedesc" rows="5" cols="40">${requestScope.requestPRICE.description}</textarea>
                                <font color="red">${requestScope.INVALIDUPDATEPRICE.informationError}</font>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><input class="login-btn" type="submit" value="Change"></td>
                            <td>                        
                                <button class="login-btn" type="reset" onclick="document.getElementById('change-pricebox').style.display = 'none'">Cancel</button></td>
                        </tr>
                    </table>
                </form>         
            </div>
        </c:if>
        <!-- END PRICE PACKAGE -->

        <!-- SUBJECT DIMENSION -->
        <div id="Dimension" class="tabcontent">
            <span onclick="this.parentElement.style.display = 'none'" class="topright">&times</span>
            <span>
                <c:if test="${empty requestScope.INVALIDDIMEN}">
                    <button form="x" class="login-btn"
                            onclick="document.getElementById('add-dimenbox').style.display = 'block'">Add new</button>
                </c:if>
                <c:if test="${!empty requestScope.INVALIDDIMEN}">
                    <button form="x" onfocus="autoClickAddDimen()" id="dimenbox" autofocus class="login-btn"
                            onclick="document.getElementById('add-dimenbox').style.display = 'block'">Add new</button>
                </c:if>
            </span>
            <br>
            <c:if test="${!empty requestScope.DIMENLIST}">
                <table border="1">
                    <thead>
                        <tr>
                            <td>Dimension ID</td>
                            <td>Name</td>
                            <td>Type</td>
                            <td>Description</td>
                            <td></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.DIMENLIST}" var="dto">
                            <tr>
                                <td>${dto.dimensionID}</td>
                                <td>${dto.name}</td>
                                <td>${dto.type}</td>
                                <td>${dto.description}</td>
                                <td> 
                                    <c:url value="AdminViewDimen" var="AdminViewDimenLink">
                                        <c:param name="dimenid" value ="${dto.dimensionID}"/>
                                        <c:param name="id" value ="${requestScope.SUBJECT.subjectID}"/>
                                    </c:url>
                                    <a href="${AdminViewDimenLink}" class="button">edit</a>
                                </td>
                                <td>
                                    <c:url value="AdminDeleteDimen" var="AdminDeleteDimenLink">
                                        <c:param name="dimenid" value ="${dto.dimensionID}"/>
                                        <c:param name="id" value ="${requestScope.SUBJECT.subjectID}"/>
                                    </c:url>
                                    <a href="${AdminDeleteDimenLink}" onclick="return confirmDelete()" class="button">delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty requestScope.DIMENLIST}">
                <h2>There is none information here!</h2>
            </c:if>
        </div>

        <div id="add-dimenbox" class="modal_fix">
            <form action="AdminAddDimension" method="POST" class="modal-content animate">
                <input type="hidden" name="id" value="${requestScope.SUBJECT.subjectID}"/>
                <table>
                    <tr>
                        <td> Name:</td>
                        <td><input type="text" name="dimenname" value="${param.dimenname}"/>
                            <font color="red">${requestScope.INVALIDDIMEN.nameError}</font>
                        </td>                            
                    </tr>
                    <tr>
                        <td> Type: </td>
                        <td><input type="text" name="type" value="${param.type}"/>
                            <font color="red">${requestScope.INVALIDDIMEN.typeError}</font> 
                        </td>
                    </tr>
                    <tr>
                        <td> Description:</td>
                        <td><input type="text" name="desc" value="${param.desc}"/>
                            <font color="red">${requestScope.INVALIDDIMEN.descriptionError}</font>
                        </td>                            
                    </tr>
                    <tr>
                        <td colspan="2"><input class="login-btn" type="submit" value="Add"></td>
                        <td>                        
                            <button class="login-btn" type="reset" onclick="document.getElementById('add-dimenbox').style.display = 'none'">Cancel</button></td>
                    </tr>
                </table>
            </form>         
        </div>

        <c:if test="${!empty requestScope.requestDIMEN}">
            <div id="change-dimenbox">
                <form action="AdminChangeDimension" method="POST" class="modal-content animate">
                    <input type="hidden" name="id" value="${requestScope.SUBJECT.subjectID}"/>
                    <input type="hidden" name="dimenid" value="${requestScope.requestDIMEN.dimensionID}"/>
                    <table>
                        <tr>
                            <td> Name:</td>
                            <td><input type="text" name="updatedimenname" value="${requestDIMEN.name}"/>
                                <font color="red">${requestScope.INVALIDUPDATEDIMEN.nameError}</font>
                            </td>                            
                        </tr>
                        <tr>
                            <td> Type: </td>
                            <td><input type="text" name="updatedimentype" value="${requestDIMEN.type}"/>
                                <font color="red">${requestScope.INVALIDUPDATEDIMEN.typeError}</font> 
                            </td>
                        </tr>
                        <tr>
                            <td> Description:</td>
                            <td><input type="text" name="updatedimendesc" value="${requestDIMEN.description}"/>
                                <font color="red">${requestScope.INVALIDUPDATEDIMEN.descriptionError}</font>
                            </td>                            
                        </tr>
                        <tr>
                            <td colspan="2"><input class="login-btn" type="submit" value="Change"></td>
                            <td>                        
                                <button class="login-btn" type="reset" onclick="document.getElementById('change-dimenbox').style.display = 'none'">Cancel</button></td>
                        </tr>
                    </table>
                </form>         
            </div>
        </c:if>
        <!-- END SUBJECT DIMENSION -->


        <br>

        <div>
            <c:url value="AdminSubjectList" var="AdminSubjectListLink"/>
            <a href="${AdminSubjectListLink}">Back to the Subject List page</a> 
        </div>

    </body>
</html>
