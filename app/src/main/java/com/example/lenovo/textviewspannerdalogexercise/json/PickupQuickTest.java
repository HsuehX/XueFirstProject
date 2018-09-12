package com.example.lenovo.textviewspannerdalogexercise.json;

/**
 * Created by Wucn on 2017/11/22.
 * 快速收寄测试数据类
 */

public class PickupQuickTest {
    public PickupQuickTest(){

    }

    public final String FIND_PERSON_BY_ORG_QUICK_REQUEST = "{\"orgCode\":\"23803100\"}";

    public final String FIND_PERSON_BY_ORG_QUICK_RESP = "{\n" +
            "    \"resCode\": \"A00001\",\n" +
            "    \"msg\": \"获取揽投员信息成功！\",\n" +
            "    \"obj\": \"{\\\"person\\\":[{\\\"teamwkPickupPersonId\\\":806,\\\"teamwkPickupPersonNo\\\":\\\"01100537\\\",\\\"teamwkPickupPersonName\\\":\\\"余青雯\\\"},{\\\"teamwkPickupPersonId\\\":22942,\\\"teamwkPickupPersonNo\\\":\\\"23003900ADMIN\\\",\\\"teamwkPickupPersonName\\\":\\\"机构管理员\\\"},{\\\"teamwkPickupPersonId\\\":1051359,\\\"teamwkPickupPersonNo\\\":\\\"CESHIUAT\\\",\\\"teamwkPickupPersonName\\\":\\\"替班测试\\\"},{\\\"teamwkPickupPersonId\\\":473,\\\"teamwkPickupPersonNo\\\":\\\"03126459\\\",\\\"teamwkPickupPersonName\\\":\\\"刘伟\\\"},{\\\"teamwkPickupPersonId\\\":636,\\\"teamwkPickupPersonNo\\\":\\\"03464016\\\",\\\"teamwkPickupPersonName\\\":\\\"叶亮\\\"},{\\\"teamwkPickupPersonId\\\":692,\\\"teamwkPickupPersonNo\\\":\\\"03565687\\\",\\\"teamwkPickupPersonName\\\":\\\"张黎娟\\\"},{\\\"teamwkPickupPersonId\\\":1464,\\\"teamwkPickupPersonNo\\\":\\\"01117191\\\",\\\"teamwkPickupPersonName\\\":\\\"裴兵\\\"},{\\\"teamwkPickupPersonId\\\":1472,\\\"teamwkPickupPersonNo\\\":\\\"01117296\\\",\\\"teamwkPickupPersonName\\\":\\\"黄圣旭\\\"},{\\\"teamwkPickupPersonId\\\":1478,\\\"teamwkPickupPersonNo\\\":\\\"01117352\\\",\\\"teamwkPickupPersonName\\\":\\\"张淑娟\\\"},{\\\"teamwkPickupPersonId\\\":1491,\\\"teamwkPickupPersonNo\\\":\\\"01117565\\\",\\\"teamwkPickupPersonName\\\":\\\"李翔\\\"},{\\\"teamwkPickupPersonId\\\":22020,\\\"teamwkPickupPersonNo\\\":\\\"342601198706250614\\\",\\\"teamwkPickupPersonName\\\":\\\"司明\\\"},{\\\"teamwkPickupPersonId\\\":22025,\\\"teamwkPickupPersonNo\\\":\\\"342601198804120039\\\",\\\"teamwkPickupPersonName\\\":\\\"唐春广\\\"},{\\\"teamwkPickupPersonId\\\":22027,\\\"teamwkPickupPersonNo\\\":\\\"342601199304210630\\\",\\\"teamwkPickupPersonName\\\":\\\"王磊\\\"},{\\\"teamwkPickupPersonId\\\":22029,\\\"teamwkPickupPersonNo\\\":\\\"34260119891128125X\\\",\\\"teamwkPickupPersonName\\\":\\\"孙家伟\\\"},{\\\"teamwkPickupPersonId\\\":22030,\\\"teamwkPickupPersonNo\\\":\\\"342601198411297132\\\",\\\"teamwkPickupPersonName\\\":\\\"李定军\\\"},{\\\"teamwkPickupPersonId\\\":22033,\\\"teamwkPickupPersonNo\\\":\\\"342601199301010019\\\",\\\"teamwkPickupPersonName\\\":\\\"李帅\\\"},{\\\"teamwkPickupPersonId\\\":22035,\\\"teamwkPickupPersonNo\\\":\\\"340825198402201039\\\",\\\"teamwkPickupPersonName\\\":\\\"徐鹏\\\"},{\\\"teamwkPickupPersonId\\\":22997,\\\"teamwkPickupPersonNo\\\":\\\"23803100ADMIN\\\",\\\"teamwkPickupPersonName\\\":\\\"机构管理员\\\"},{\\\"teamwkPickupPersonId\\\":1005233,\\\"teamwkPickupPersonNo\\\":\\\"31002311\\\",\\\"teamwkPickupPersonName\\\":\\\"张刘\\\"},{\\\"teamwkPickupPersonId\\\":1005234,\\\"teamwkPickupPersonNo\\\":\\\"31001010\\\",\\\"teamwkPickupPersonName\\\":\\\"孙超超\\\"},{\\\"teamwkPickupPersonId\\\":1013644,\\\"teamwkPickupPersonNo\\\":\\\"31001217\\\",\\\"teamwkPickupPersonName\\\":\\\"翟林\\\"},{\\\"teamwkPickupPersonId\\\":1013800,\\\"teamwkPickupPersonNo\\\":\\\"LM05\\\",\\\"teamwkPickupPersonName\\\":\\\"小新\\\"},{\\\"teamwkPickupPersonId\\\":1013802,\\\"teamwkPickupPersonNo\\\":\\\"2380310099\\\",\\\"teamwkPickupPersonName\\\":\\\"老路\\\"},{\\\"teamwkPickupPersonId\\\":1013804,\\\"teamwkPickupPersonNo\\\":\\\"2380310003\\\",\\\"teamwkPickupPersonName\\\":\\\"测试三\\\"},{\\\"teamwkPickupPersonId\\\":1013808,\\\"teamwkPickupPersonNo\\\":\\\"1234567\\\",\\\"teamwkPickupPersonName\\\":\\\"张三\\\"},{\\\"teamwkPickupPersonId\\\":1030266,\\\"teamwkPickupPersonNo\\\":\\\"123456789\\\",\\\"teamwkPickupPersonName\\\":\\\"流量测试\\\"},{\\\"teamwkPickupPersonId\\\":1030270,\\\"teamwkPickupPersonNo\\\":\\\"2380310001\\\",\\\"teamwkPickupPersonName\\\":\\\"机构测试\\\"},{\\\"teamwkPickupPersonId\\\":1030271,\\\"teamwkPickupPersonNo\\\":\\\"2380310002\\\",\\\"teamwkPickupPersonName\\\":\\\"熊二\\\"},{\\\"teamwkPickupPersonId\\\":1051378,\\\"teamwkPickupPersonNo\\\":\\\"LM06\\\",\\\"teamwkPickupPersonName\\\":\\\"小新2\\\"},{\\\"teamwkPickupPersonId\\\":1051384,\\\"teamwkPickupPersonNo\\\":\\\"2380310011\\\",\\\"teamwkPickupPersonName\\\":\\\"测试11\\\"},{\\\"teamwkPickupPersonId\\\":1051387,\\\"teamwkPickupPersonNo\\\":\\\"SFG\\\",\\\"teamwkPickupPersonName\\\":\\\"sfg\\\"},{\\\"teamwkPickupPersonId\\\":1051388,\\\"teamwkPickupPersonNo\\\":\\\"MHK\\\",\\\"teamwkPickupPersonName\\\":\\\"mhk\\\"},{\\\"teamwkPickupPersonId\\\":1051389,\\\"teamwkPickupPersonNo\\\":\\\"HZ\\\",\\\"teamwkPickupPersonName\\\":\\\"hz\\\"},{\\\"teamwkPickupPersonId\\\":1051390,\\\"teamwkPickupPersonNo\\\":\\\"LY\\\",\\\"teamwkPickupPersonName\\\":\\\"ly\\\"},{\\\"teamwkPickupPersonId\\\":1051391,\\\"teamwkPickupPersonNo\\\":\\\"WSB\\\",\\\"teamwkPickupPersonName\\\":\\\"wsb\\\"},{\\\"teamwkPickupPersonId\\\":1051392,\\\"teamwkPickupPersonNo\\\":\\\"LK\\\",\\\"teamwkPickupPersonName\\\":\\\"lk\\\"},{\\\"teamwkPickupPersonId\\\":1051394,\\\"teamwkPickupPersonNo\\\":\\\"JYT\\\",\\\"teamwkPickupPersonName\\\":\\\"jyt\\\"},{\\\"teamwkPickupPersonId\\\":1051397,\\\"teamwkPickupPersonNo\\\":\\\"YHB\\\",\\\"teamwkPickupPersonName\\\":\\\"yhb\\\"}]}\"\n" +
            "}";

    public final String FIND_PDA_PICK_UP_POST_INFO_QUICK_REQUEST = "{\"baseProductId\":\"180\",\"baseProductName\":\"国内标准快递\",\"bizProductId\":\"163\",\"bizProductName\":\"标快物品\",\"bizProductNo\":\"112104300000991\",\"ecommerceNo\":\"\",\"sender\":\"海虹集团巢湖今辰药业有限公司（医药类）-0\",\"senderId\":\"756654\",\"senderNo\":\"90000001914128\",\"waybillNo\":\"1018992590927\"}";

    public final String FIND_PDA_PICK_UP_POST_INFO_QUICK_RESP = "{\"resCode\":\"A00001\",\"msg\":\"查询成功\",\"obj\":\"{\\\"receiverAddr\\\":\\\"安徽省六安市皋城东路9号皖西卫生职业学院实习就业指导办\\\",\\\"exportGridName\\\":\\\"*\\\",\\\"orderWeight\\\":\\\"0.0\\\"}\"}";

    public final String SAVE_PDA_PICK_UP_POST_INFO_QUICK_REQUEST = "{\n" +
            "    \"baseProductId\": \"180\",\n" +
            "    \"baseProductName\": \"国内标准快递\",\n" +
            "    \"baseProductNo\": \"11210\",\n" +
            "    \"bizProductId\": \"163\",\n" +
            "    \"bizProductName\": \"标快物品\",\n" +
            "    \"bizProductNo\": \"112104300000991\",\n" +
            "    \"exportGridName\": \"*\",\n" +
            "    \"height\": \"0\",\n" +
            "    \"length\": \"0\",\n" +
            "    \"orderWeightFlag\": \"0\",\n" +
            "    \"realweight\": \"1000\",\n" +
            "    \"receiverAddr\": \"安徽省六安市皋城东路9号皖西卫生职业学院实习就业指导办\",\n" +
            "    \"sender\": \"海虹集团巢湖今辰药业有限公司（医药类）\",\n" +
            "    \"senderId\": \"756654\",\n" +
            "    \"senderNo\": \"90000001914128\",\n" +
            "    \"senderWarehouseId\": \"352575\",\n" +
            "    \"senderWarehouseName\": \"0\",\n" +
            "    \"teamwkPickupPersonNo\": \"\",\n" +
            "    \"transferType\": \"1\",\n" +
            "    \"volumetricRatio\": \"1\",\n" +
            "    \"waybillNo\": \"1018992590927\",\n" +
            "    \"width\": \"0\"\n" +
            "}";

    public final String SAVE_PDA_PICK_UP_POST_INFO_QUICK_RESP = "{\"resCode\":\"A00001\",\"msg\":\"\",\"obj\":\"{\\\"result\\\":\\\"保存成功！\\\"}\"}";



}
