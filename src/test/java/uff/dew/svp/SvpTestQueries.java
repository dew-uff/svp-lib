package uff.dew.svp;

public class SvpTestQueries {

    public final static String query_sd_regular = "<results> {"
            + "    for $p in doc('auction.xml')/site/people/person"
            + "    let $e := $p/homepage"
            + "    where count($e) = 0"
            + "    return"
            + "        <people_without_homepage>"
            + "            {$p/name}"
            + "        </people_without_homepage>"
            + "} </results>";
    
    public final static String query_sd_order_by = "<results> {"
            + "    for $pe in doc('auction.xml')/site/people/person "
            + "    let $int := $pe/profile/interest "
            + "    where $pe/profile/business = \"Yes\" and count($int) > 1"
                    + "    order by $pe/name"
                    + "    return"
                    + "    <people>"
                    + "        {$pe}"
                    + "    </people>"
                    + "} </results>";

    public final static String query_sd_order_by_descending = "<results> {"
            + "    for $pe in doc('auction.xml')/site/people/person "
            + "    let $int := $pe/profile/interest "
            + "    where $pe/profile/business = \"Yes\" and count($int) > 1"
                    + "    order by $pe/name descending"
                    + "    return"
                    + "    <people>"
                    + "        {$pe}"
                    + "    </people>"
                    + "} </results>";
    
    public final static String query_sd_join = "<results> {   "
            + "for $it in doc('auction.xml')/site/regions/africa/item   "
            + "for $co in doc('auction.xml')/site/closed_auctions/closed_auction   "
            + "where $co/itemref/@item = $it/@id   and $it/payment = \"Cash\"    "
                    + "return     <itens>"
                    + "      {$co/price}"
                    + "      {$co/date}"
                    + "      {$co/quantity}"
                    + "      {$co/type}"
                    + "      {$it/payment}"
                    + "      {$it/location}"
                    + "      {$it/from}"
                    + "      {$it/to}"
                    + "    </itens>"
                    + " }</results>";
    
    public final static String query_sd_triple_join = "<results>{ \r\n"
            + "for $country in doc('auction.xml')/site/people/person/address/country \r\n"
            + "for $pe in doc('auction.xml')/site/people/person \r\n"
            + "for $oa in doc('auction.xml')/site/open_auctions/open_auction \r\n"
            + "where $pe/profile/education = \"College\" and $oa/current > 180 and \r\n"
                    + "$pe/address/country = $country and $pe/@id = $oa/seller/@person \r\n"
            + "order by $country \r\n"
            + "return \r\n"
            + "    <auction> \r\n"
            + "        <country>{ $country }</country> \r\n"
            + "        <person>{ $pe/name }</person> \r\n"
            + "        <current_value>{ $oa/current }</current_value> \r\n"
            + "    </auction> \r\n"
            + "}</results>";
    
    public final static String query_sd_aggregation = "<results> {"
            + "    let $p := doc('auction.xml')/site/closed_auctions/closed_auction"
            + "    return"
            + "        <summary>"
            + "            <cont>{count($p)}</cont>"
            + "            <media>{avg($p/price)}</media>"
            + "        </summary>"
            + "} </results>";

    public static final String query_sd_incomplete_path = 
            " <results> \r\n" +
            " { \r\n"+
            "   for $p in document('auction.xml')//person \r\n"+
            " return \r\n"+
            "  <person> \r\n"+
            "    {$p/name} \r\n"+
            "  </person> \r\n"+
            " } \r\n"+ 
            " </results>"; 
    
    public static final String query_sd_incomplete_path_join = 
            "<results> \r\n"
            + "{ \r\n"
            + "  for $p in document('auction.xml')//person \r\n"
            + "  for $a in document('auction.xml')//closed_auction \r\n"
            + "  where $p/@id = $a/@person \r\n"
            + "      return \r\n"
            + "        <buyer> \r\n"
            + "          {$p/name} \r\n"
            + "          {$a/price} \r\n"
            + "        </buyer> \r\n"
            + "    } </results>";
    
    public static final String query_sd_c9 = 
            "<results>{ \r\n"
            + "for $pe in doc('auction.xml')//person \r\n"
            + "for $oa in doc('auction.xml')/site/open_auctions/open_auction \r\n"
            + "where $pe/profile/education = \"College\" and $oa/type = \"Featured\" and $pe/@id = $oa/seller/@person \r\n"
                    + "order by $pe/address/country \r\n"
                    + "return \r\n"
                    + "    <seller> \r\n"
                    + "        <country>{ $pe/address/country }</country> \r\n"
                    + "        <person>{ $pe/name }</person> \r\n"
                    + "        <item id='{$oa/itemref/@item}'> \r\n"
                    + "            <initial_bid>{$oa/initial}</initial_bid> \r\n"
                    + "            <current_bid>{$oa/current}</current_bid> \r\n"
                    + "        </item> \r\n"
                    + "    </seller> \r\n"
                    + "}</results>";
    
    public static final String query_md_regular = 
            "<results> {\n"
            + "for $op in collection('auction')/site/open_auctions/open_auction\n"
            + "let $bd := $op/bidder where count($op/bidder) > 5\n"
            + "return\n"
            + "<open_auctions_with_more_than_5_bidders>\n"
            +   "<auction>\n"
            +       "{$op}\n"
            +   "</auction>\n"
            +   "<qty_bidder>\n"
            +   "{count($op/bidder)}\n"
            +   "</qty_bidder>\n"
            + "</open_auctions_with_more_than_5_bidders>\n"
            +"} </results>";
    
    public static final String query_md_incomplete_path = 
            " <results> \r\n" +
            " { \r\n"+
            "   for $p in collection('auction')//person \r\n"+
            " return \r\n"+
            "  <person> \r\n"+
            "    {$p/name} \r\n"+
            "  </person> \r\n"+
            " } \r\n"+ 
            " </results>"; 
    
    public static final String query_md_c1 = 
            "<results> { \r\n"
            + "    for $it in collection('auction')/site/regions/samerica/item \r\n"
            + "    for $pe in collection('auction')/site/people/person \r\n"
            + "    where $pe/profile/@income > 40000 and $pe/profile/interest/@category = $it/incategory/@category \r\n"
            + "    return \r\n"
            + "    <match> \r\n"
            + "        <person>{$pe/name}</person> \r\n"
            + "        <item>{$it/name}</item> \r\n"
            + "    </match> \r\n"
            + "} </results>";
    
    public static final String query_md_c9 = 
            "<results>{ \r\n"
            + "for $pe in collection('auction')//person \r\n"
            + "for $oa in collection('auction')//open_auction \r\n"
            + "where $pe/profile/education = \"College\" and $pe/@id = $oa/seller/@person \r\n"
                    + "order by $pe/address/country \r\n"
                    + "return \r\n"
                    + "    <seller> \r\n"
                    + "        <country>{ $pe/address/country }</country> \r\n"
                    + "        <person>{ $pe/name }</person> \r\n"
                    + "        <item id='{$oa/itemref/@item}'> \r\n"
                    + "            <initial_bid>{$oa/initial}</initial_bid> \r\n"
                    + "            <current_bid>{$oa/current}</current_bid> \r\n"
                    + "        </item> \r\n"
                    + "    </seller> \r\n"
                    + "}</results>";
    
    public static final String query_md_incomplete_path_join = 
            "<results> \r\n"
            + "{ \r\n"
            + "  for $p in collection('auction')//person \r\n"
            + "  for $a in collection('auction')//closed_auction \r\n"
            + "  where $p/@id = $a/@person \r\n"
            + "      return \r\n"
            + "        <buyer> \r\n"
            + "          {$p/name} \r\n"
            + "          {$a/price} \r\n"
            + "        </buyer> \r\n"
            + "    } </results>";
    
    public static final String query_md_xbench_test = 
            "<results>{\r\n"
            + "for $order in collection('orders')/order\r\n"
            + "where $order/@id= \"1\"\r\n"
            + "return\r\n"
            + "    <order>\r\n"
            + "        {$order/customer_id}\r\n"
            + "    </order>\r\n"
            + "}</results>\r\n";
    
}
