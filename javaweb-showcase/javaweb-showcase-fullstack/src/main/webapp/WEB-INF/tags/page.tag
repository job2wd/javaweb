<%@ include file="/common/taglibs.jsp"%>
<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>

<%
int viewPages = 5;
viewPages = (viewPages%2) == 0 ? viewPages + 1 : viewPages;
int roundPage = viewPages / 2;

int current =  page.getNumber() + 1;
int totalPages = page.getTotalPages();

//int begin = Math.max(1, current - paginationSize/2);
//int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

int start = page.getNumber() * page.getSize() + 1;
int finish = page.getNumber() * page.getSize() + page.getNumberOfElements();

int begin = (current - 1) - roundPage;
int end = current + roundPage;

if(begin <= 0) {
  begin = 1;
  end = viewPages > totalPages ? totalPages : viewPages;
} else {
  if(end >= totalPages) {    
    begin = totalPages - viewPages + 1;
    end = totalPages;
  } else {
    begin +=1;
  }  
}

request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
request.setAttribute("start", start);
request.setAttribute("finish", finish);
%>

<%if(page.getTotalElements()>0){%>
<div class="page" style="margin-top:5px; padding-bottom: 5px; ">
		 <% if (page.hasPreviousPage()){%>
               	<!-- <a href="?page=1&sortType=${sortType}&${searchParams}" title="第一页">&lt;&lt;</a> -->
                <a href="?page=${current-1}&sortType=${sortType}&${searchParams}" title="上一页" class="zuosanjiao">&nbsp;</a>
         <%}else{%>
                <!-- <a href="javascript:void(0);" title="已是第一页">&lt;&lt;</a> -->
                <a href="javascript:void(0);" title="已是第一页" class="zuosanjiao">&nbsp;</a>
         <%} %>
 
		<c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">                
                    <a href="javascript:void(0);" style="background-color:#FA6D3C; color:#FFF;" title="当前页 [${start}-${finish}/${page.totalElements}]" >${i}</a>
                </c:when>
                <c:otherwise>
                    <a href="?page=${i}&sortType=${sortType}&${searchParams}" title="跳转到该页">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	  
	  	 <% if (page.hasNextPage()){%>
               	<a href="?page=${current+1}&sortType=${sortType}&${searchParams}" title="下一页" class="yousanjiao">&nbsp;</a>
                <!-- <a href="?page=${page.totalPages}&sortType=${sortType}&${searchParams}" title="最后一页[总共${page.totalPages}页，${page.totalElements}条记录]">&gt;&gt;</a> -->
         <%}else{%>
                <a href="javascript:void(0);" title="已是最后一页[总共${page.totalPages}页，${page.totalElements}条记录]" class="yousanjiao">&nbsp;</a>
                <!-- <a href="javascript:void(0);" title="已是最后一页[总共${page.totalPages}页，${page.totalElements}条记录]">&gt;&gt;</a> -->
         <%} %>
</div>
<%} else{%>
        <div style="margin: 10px;" class="list_second">暂时没有数据!!</div>
         <%} %>

