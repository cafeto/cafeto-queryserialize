<?php

class Cafeto_QuerySerialize_Expression {
	public $negated = false;
	public $logical;
	public $operator;
	public $function;
	public $trim;
	public $values = array();
	public $path;
	public $parameter;
	public $literal;
	public $subquery;
	public $alias;

	public function __construct() {
	}
}
