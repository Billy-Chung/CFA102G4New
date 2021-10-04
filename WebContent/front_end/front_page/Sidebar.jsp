<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="deznav">
	<div class="deznav-scroll">
		<ul class="metismenu" id="menu">
			<li><a class="has-arrow ai-icon" href="javascript:void()"
				aria-expanded="false"> <i class="flaticon-086-star"></i> <span
					class="nav-text">寵物資料管理</span>
			</a>
				<ul aria-expanded="false">
					<li><a
						href="<%=request.getContextPath()%>/front_end/GeneralMemberPet/GeneralMemberPet.jsp">寵物清單</a></li>
					<li><a
						href="<%=request.getContextPath()%>/front_end/GeneralMemberPet/addGeneralMemberPet.jsp">新增寵物</a></li>

				</ul></li>
			
		</ul>
	</div>
</div>