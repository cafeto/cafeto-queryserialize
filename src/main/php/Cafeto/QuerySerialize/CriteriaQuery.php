<?php

class Cafeto_QuerySerialize_CriteriaQuery {
	public $distict = false;
	public $root;
	public $selects = array();
	public $froms = array();
	public $where;
	public $having;
	public $groupBys = array();
	public $orders = array();

	public function __construct() {
	}
}
