<!DOCTYPE html>
<html lang="en">
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <#import "../common/common.macro.ftl" as netCommon>
    <@netCommon.commonStyle />
</head>
<body>


<h1>${I18n.auth}</h1>
<h1>${I18n.config_auth}</h1>
<#if error??>
    <span style="color: red; ">${error}</span>
</#if>
<@netCommon.commonFooter />

</body>
</html>
