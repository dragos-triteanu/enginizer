<#include "*/userDistinctMappings.ftl"/>
<#macro renderNavbar userRole activePage userHasProjects="">
	<nav id="mainNav" class="navbar navbar-default navbar-inverse" role="navigation">
	     <div class="container-fluid" id="navfluid">
		       <div class="navbar-header">
			       <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigationbar">
			       <span class="sr-only">Toggle navigation</span>
			       <span class="icon-bar"></span>
			       <span class="icon-bar"></span>
			       <span class="icon-bar"></span>
			      </button>
			      <img src="./resources/images/logo.png" class="navbar-brand"/>
		      </div>
			<div class="collapse navbar-collapse" id="navigationbar">
				<ul class="nav navbar-nav navbar-float">
				    
				   <#assign sections = userTypes[userRole] />

				    <#list sections as section>
					    <li id="${section.id}" class="${(section.id == activePage)?string("active","")}"><a href="${section.url}">${section.name}</a></li>
				    </#list>
				    <li><a href="./logout">Logout</a></li>
				</ul>
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
</nav>
</#macro>

