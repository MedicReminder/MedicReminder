<?php
/**
 * Created by JetBrains PhpStorm.
 * User: mcautreels
 * Date: 19/02/12
 * Time: 11:09
 * To change this template use File | Settings | File Templates.
 */

$searchQuery = $_GET["query"];

$user="medicines";
$password="gadc2012";
$database="medicines";
mysql_connect("itforitnet.ipagemysql.com",$user,$password);
@mysql_select_db($database) or die( "Unable to select database");

$query = "SELECT * FROM medicines WHERE name LIKE '%" . $searchQuery . "%'";

$result = mysql_query($query);

$i = 0;
$tojson = array();
while ($row = mysql_fetch_array($result, MYSQL_NUM)) {
    $medicine = array("id" => $row[0], "name" => $row[1]);

    $tojson[$i] = $medicine;
    $i++;
}

print(json_encode($tojson));

mysql_close();

?>