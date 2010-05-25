<?php

require_once "Cafeto/DeepCopy.php";
require_once "Cafeto/QuerySerialize/CriteriaBuilder.php";

class Cafeto_QuerySerialize {

	public $criteria;
	public $offset = 0;
	public $limit = 0;
	public $parameters;
	public $deepCopy;

	public function __construct() {
		$this->parameters['entry'] = array();
	}

	public function getCriteriaBuilder() {
		$obj = new Cafeto_QuerySerialize_CriteriaBuilder();
		return $obj;
	}

	public function addParameter($name, $value) {
		$this->parameters['entry'][] = array('key' => $name, 'value' => $value);
	}
}
