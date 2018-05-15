package com.nilo.wms.service;

import com.nilo.wms.common.util.HttpUtil;
import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/5/15.
 */
public class HttpUtilTest {
    public static void main(String[] args) {


        Map<String, String> param = new HashMap<>();
        //client_customerid
        param.put("client_customerid", "FLUXWMS");
        //client_db
        param.put("client_db", "WH01");
        //Token 号
        param.put("apptoken", "80AC1A3F-F949-492C-A024-7044B28C8025");
        //验签KEY
        param.put("appkey", "test");
        // 时间戳
        param.put("timestamp", "2018-05-15 08:35:01");
        // 请求格式
        param.put("format", "xml");
        param.put("method", "putSOData");
        param.put("messageid", "SO");

        String data = "<xmldata><header><H_EDI_04>1359</H_EDI_04><CarrierId>STANDARD</CarrierId><CarrierName>STANDARD</CarrierName><OrderTime>2018-05-10 15:29:21</OrderTime><CustomerID>KILIMALL</CustomerID><H_EDI_10>200</H_EDI_10><H_EDI_05>Y</H_EDI_05><Channel>N</Channel><UserDefine1>FBK</UserDefine1><OrderNo>10000000567748</OrderNo><ConsigneeID>XN</ConsigneeID><OrderType>SELL</OrderType><H_EDI_02>1359.0</H_EDI_02><detailsItem><height>0.0</height><invoice_price>0.0</invoice_price><LineNo>1</LineNo><SKU>188514</SKU><orderNum>1</orderNum><D_EDI_04>1159.0</D_EDI_04><QtyOrdered>1</QtyOrdered><D_EDI_03>1159.0</D_EDI_03><weight>0.0</weight><width>0.0</width><CustomerID>KILIMALL</CustomerID></detailsItem><H_EDI_06>0.0</H_EDI_06><C_Address1>Nairobi,Kilimani IBSA Offices, Elgeyo Marakwet Road, off Agwings Kodhek Road, Nairobi Kenya</C_Address1><C_City>Kilimani</C_City><ConsigneeName>Kaitan Mbuya</ConsigneeName><C_Tel1>254743156598</C_Tel1><Stop></Stop><UserDefine2></UserDefine2><UserDefine3></UserDefine3><UserDefine4>KE10113436</UserDefine4><H_EDI_03>0</H_EDI_03><WarehouseID>KE01</WarehouseID></header></xmldata>";

        try {
            param.put("data", URLEncoder.encode(data, "utf-8"));
            //加签值
            param.put("sign", createFluxSign(data));
            String response = HttpUtil.post("http://52.192.6.22:8081/datahubWeb/FLUXWMSAPI", param);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static String createFluxSign(String data) {
        //appSecret + data +appSecret 进行MD5加密
        String md5Str = "";
        try {
            md5Str = DigestUtils.md5Hex(("test" + data + "test").getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e1) {
        }
        //md5Str加密串进行base64加密
        String base64Str;
        String sign = "";
        try {
            base64Str = new BASE64Encoder().encode(md5Str.getBytes("utf-8"));
            //转换成大写，utf-8 urlEncoding
            sign = URLEncoder.encode(base64Str.toUpperCase(), "utf-8");
        } catch (UnsupportedEncodingException e) {
        }

        return sign;
    }

}
