<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <script src="//cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
</head>
<body class="">
sku:<input type="text" id="sku" lenght="20"/>
<input type="button" value="search" onclick="querySkuStorage()"/>
<br/>
<br/>
WMS Storage : <label id="wmsStorage"></label>
<br/>
<br/>
Storage : <label id="Storage"></label>
<br/>
<br/>
LockStorage : <label id="LockStorage"></label>
<br/>
<br/>
OrderLockList : <label id="Lock_Order_List"></label>
<br/>
<script>
    function querySkuStorage() {
        $.ajax({
            type: "POST",
            url: "storageInfo.html",
            dataType: "json",
            data: {
                sku: $("#sku").val()
            },
            success: function (data) {

                $("#wmsStorage").html(data.wmsStorage);
                $("#Storage").html(data.Storage);
                $("#LockStorage").html(data.LockStorage);
                $("#Lock_Order_List").html("");
                var lockInfo = "<br/>";
                for (var i = 0; i < data.Lock_Order_List.length; i++) {
                    lockInfo = lockInfo + data.Lock_Order_List[i] + '<br>'
                }
                $("#Lock_Order_List").html(lockInfo);
            }
        });
    }
</script>
</body>
</html>