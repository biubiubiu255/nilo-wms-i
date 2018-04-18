<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<head>
    <link rel="stylesheet" href="/css/api.css">
</head>

<div id="docs-api-content" class="width1200 clearfix">
    <div id="docs-api-main">
        <div class="tabDiv">
            <!--api-->
            <div id="docs-api-area" class="docs-api-show clearfix" style="display: block;">
                <ul class="api-sub-list">
                    <li class="api-aid active ">
                        <a class="selected " href="/docs/api/id/54" title="发送短信" onclick="return false">发送短信</a>
                    </li>
                    <li class="api-aid ">
                        <a class="" href="/docs/api/id/54/aid/602" title="屏蔽词检查测" onclick="return false">屏蔽词检查测</a>
                    </li>
                </ul>
                <div class="api-sub-content" style="display: none;">
                    <div class="simpleline"><strong>接口地址：</strong><span class="url">http://v.juhe.cn/sms/send</span>
                    </div>
                    <div class="simpleline"><strong>返回格式：</strong><span class="url">json/xml</span>
                    </div>
                    <div class="simpleline"><strong>请求方式：</strong><span class="url">http get</span></div>
                    <div class="simpleline"><strong>请求示例：</strong><span class="url">http://v.juhe.cn/sms/send?mobile=手机号码&amp;tpl_id=短信模板ID&amp;tpl_value=%23code%23%3D654654&amp;key=</span>
                    </div>
                    <div class="simpleline"><strong>接口备注：</strong><font color="">接口返回成功不代表接收成功，具体接收状态只能由运营商查询；运营商限制同1个号码同1个签名的内容1分钟内只能接收1条，1小时内只能接收3条，一天最多接收10条，否则可能会被运营商屏蔽，短信api接口本身不限制发送频率，具体发送频率需要用户自行设置。</font>
                    </div>
                    <div class="simpleTable">
                        <p class="linep">请求参数说明：</p>
                        <table class="api-table" border="0" cellspacing="0" cellpadding="0">
                            <tbody>
                            <tr class="title">
                                <th width="20"></th>
                                <th width="100">名称</th>
                                <th width="60">必填</th>
                                <th width="80">类型</th>
                                <th>说明</th>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td class="url">mobile</td>
                                <td class="url">是</td>
                                <td class="url">string</td>
                                <td>接收短信的手机号码</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td class="url">tpl_id</td>
                                <td class="url">是</td>
                                <td class="url">int</td>
                                <td>短信模板ID，请参考个人中心短信模板设置</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td class="url">tpl_value</td>
                                <td class="url">是</td>
                                <td class="url">string</td>
                                <td>变量名和变量值对，如：#code#=431515，整串值需要urlencode，比如正确结果为：%23code%23%3d431515。如果你的变量名或者变量值中带有#&amp;=中的任意一个特殊符号，请先分别进行utf-8
                                    urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明&gt;</a>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td class="url">key</td>
                                <td class="url">是</td>
                                <td class="url">string</td>
                                <td>应用APPKEY(应用详细页查询)</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td class="url">dtype</td>
                                <td class="url">否</td>
                                <td class="url">string</td>
                                <td>返回数据的格式,xml或json，默认json</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="simpleTable">
                        <p class="linep">返回参数说明：</p>
                        <table class="api-table" border="0" cellspacing="0" cellpadding="0">
                            <tbody>
                            <tr class="title">
                                <th width="20"></th>
                                <th width="100">名称</th>
                                <th width="80">类型</th>
                                <th>说明</th>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td class="url">error_code</td>
                                <td class="url">int</td>
                                <td class="url">返回码</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td class="url">reason</td>
                                <td class="url">string</td>
                                <td class="url">返回说明</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>