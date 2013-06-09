<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script>

	$(document).ready(function(){
		
		var tabMembers = {
			tabDiv : $('#clubMenuContents #members'),
			init : function(){
				this.getMemberList();
			},
			getMemberList : function(){
				
			}	
		};

		$('a[data-toggle="tab"]').on('shown', function (e) {
			//activeTab = e.target;
			var currentTab = $('#clubMenuContents .tab-pane.active');
			if(currentTab.attr('id') == 'members'){
				tabMembers.init();
			}else if(currentTab.attr('id') == 'games'){
				tabMembers.init();
			}else if(currentTab.attr('id') == 'bbs'){
				tabMembers.init();
			}else if(currentTab.attr('id') == 'guestbook'){
				tabMembers.init();
			}else 
			console.log($('#clubMenuContents .tab-pane.active').attr('id'));
			
		});
		
		$('#clubMenus a[href="#members"]').tab('show');
		
	});
</script>