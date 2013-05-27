<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!--=== Content part ===-->
<div id="body">

    <!-- Purchase Block -->
    <div class="row-fluid purchase margin-bottom-30">
        <div class="container">
			<div class="span9">
                <span><small>야동말고 축동과 함께하는</small><br><strong>2013 Summer 전국 클럽 축구 리그</strong></span>
                <p>
                	지금까지 이런 리그는 없었다. 진정한 축구 클럽 강자는 누구인가. <strong>2013년 8월 여름 </strong>새로운 축구 리그가 시작됩니다.
                	${sessionScope.MEMBER.memail}
                </p>
            </div>
            <a href="https://wrapbootstrap.com/theme/unify-responsive-website-template-WB0412697" class="btn-buy hover-effect">참가 등록하기</a>
        </div>
    </div><!--/row-fluid-->
    <!--//End Purchase Block -->

	<div class="container">	
		<!-- Service Blocks -->
		<div class="row-fluid">
	    	<div class="span4">
	    		<div class="service clearfix">
                    <i class="icon-resize-small"></i>
	    			<div class="desc">
	    				<h4>Fully Responsive</h4>
                        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus etiam sem...</p>
	    			</div>
	    		</div>	
	    	</div>
	    	<div class="span4">
	    		<div class="service clearfix">
                    <i class="icon-cogs"></i>
	    			<div class="desc">
	    				<h4>HTML5 + CSS3</h4>
                        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus etiam sem...</p>
	    			</div>
	    		</div>	
	    	</div>
	    	<div class="span4">
	    		<div class="service clearfix">
                    <i class="icon-plane"></i>
	    			<div class="desc">
	    				<h4>Launch Ready</h4>
                        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus etiam sem...</p>
	    			</div>
	    		</div>	
	    	</div>			    
		</div><!--/row-fluid-->
		<!-- //End Service Blokcs -->

		<!-- Our Clients -->
		<div id="clients-flexslider" class="flexslider home clients">
	        <div class="headline"><h3>Sponsers</h3></div>	
			<ul class="slides">
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div><!--/flexslider-->
		<!-- //End Our Clients -->

	</div><!--/container-->		
</div><!--/body-->

