<?php

/*
 * Copyright (c) 2010, Fabio Ospitia Trujillo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *   * Neither the name of the Cafeto.Net nor the names of its contributors
 *     may be used to endorse or promote products derived from this software
 *     without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

require_once "Cafeto/QuerySerialize/Expression.php";
require_once "Cafeto/QuerySerialize/CriteriaQuery.php";
require_once "Cafeto/QuerySerialize/Domain.php";
require_once "Cafeto/QuerySerialize/Join.php";
require_once "Cafeto/QuerySerialize/Order.php";

class Cafeto_QuerySerialize_CriteriaBuilder {

	public function __construct() {
	}

	public function andOp($expr0, $expr1) {
		if ($expr0->logical != null && empty($expr0->values)) {
			$expr0->values[] = $expr1;
			return $expr0;
		}
		if ($expr1->logical != null && empty($expr1->values)) {
			$expr1->values[] = $expr0;
			return $expr1;
		}

		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->logical = "AND";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function orOp($expr0, $expr1) {
		if ($expr0->logical != null && empty($expr0->values)) {
			$expr0->values[] = $expr1;
			return $expr0;
		}
		if ($expr1->logical != null && empty($expr1->values)) {
			$expr1->values[] = $expr0;
			return $expr1;
		}

		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->logical = "OR";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function disjunction() {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->logical = "OR";
		return $obj;
	}

	public function conjunction() {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->logical = "AND";
		return $obj;
	}

	public function not($expr) {
		$expr->negated = true;
		return $expr;
	}

	public function sum($expr0, $expr1 = null) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->values[] = $expr0;
		if ($expr1 != null) {
			$obj->operator = "SUM";
			$obj->values[] = $expr1;
		} else {
			$obj->function = "SUM";
		}
		return $obj;
	}

	public function diff($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "DIFF";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function prod($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "PROD";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function qout($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "QUOT";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function eq($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "EQ";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function ne($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "NE";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function gt($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "GT";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function lt($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "LT";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function ge($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "GE";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function le($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "LE";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function like($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "LIKE";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function notLike($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "NOT_LIKE";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function between($expr0, $expr1, $expr2) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "BETWEEN";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		$obj->values[] = $expr2;
		return $obj;
	}

	public function notBetween($expr0, $expr1, $expr2) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "NOT_BETWEEN";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		$obj->values[] = $expr2;
		return $obj;
	}

	public function isNull($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "IS_NULL";
		$obj->values[] = $expr;
		return $obj;
	}

	public function isNotNull($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "IS_NOT_NULL";
		$obj->values[] = $expr;
		return $obj;
	}

	public function isEmpty($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "IS_EMPTY";
		$obj->values[] = $expr;
		return $obj;
	}

	public function isNotEmpty($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "IS_NOT_EMPTY";
		$obj->values[] = $expr;
		return $obj;
	}

	public function isMember($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "MEMBER";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function isNotMember($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "NOT_MEMBER";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		return $obj;
	}

	public function isTrue($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "IS_TRUE";
		$obj->values[] = $expr;
		return $obj;
	}

	public function isNotTrue($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "IS_NOT_TRUE";
		$obj->values[] = $expr;
		return $obj;
	}

	public function isFalse($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "IS_FALSE";
		$obj->values[] = $expr;
		return $obj;
	}

	public function isNotFalse($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "IS_NOT_FALSE";
		$obj->values[] = $expr;
		return $obj;
	}

	public function in($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "IN";
		if (is_array($expr)) {
			$obj->values = $expr;
		} else {
			$obj->subquery = $expr;
		}
		return $obj;
	}

	public function notIn($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "NOT_IN";
		if (is_array($expr)) {
			$obj->values = $expr;
		} else {
			$obj->subquery = $expr;
		}
		return $obj;
	}

	public function exists($subquery) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "EXISTS";
		$obj->subquery = $subquery;
		return $obj;
	}

	public function notExists($subquery) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "NOT_EXISTS";
		$obj->subquery = $subquery;
		return $obj;
	}

	public function all($subquery) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "ALL";
		$obj->subquery = $subquery;
		return $obj;
	}

	public function any($subquery) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "ANY";
		$obj->subquery = $subquery;
		return $obj;
	}

	public function some($subquery) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->operator = "SOME";
		$obj->subquery = $subquery;
		return $obj;
	}

	public function count($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "COUNT";
		$obj->values[] = $expr;
		return $obj;
	}

	public function countDistinct($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "COUNT_DISTINCT";
		$obj->values[] = $expr;
		return $obj;
	}

	public function max($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "MAX";
		$obj->values[] = $expr;
		return $obj;
	}

	public function min($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "MIN";
		$obj->values[] = $expr;
		return $obj;
	}

	public function avg($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "AVG";
		$obj->values[] = $expr;
		return $obj;
	}

	public function concat($expr0, $expr1) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "CONCAT";
		$obj->values[] = $expr;
		return $obj;
	}

	public function substring($expr0, $expr1, $expr2) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "SUBSTRING";
		$obj->values[] = $expr;
		return $obj;
	}

	public function trim($expr, $type = "BOTH", $char = " ") {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "TRIM";
		$obj->trim = $type;
		$obj->values[] = $this->stringLit($char);
		$obj->values[] = $expr;
		return $obj;
	}

	public function lower($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "LOWER";
		$obj->values[] = $expr;
		return $obj;
	}

	public function upper($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "UPPER";
		$obj->values[] = $expr;
		return $obj;
	}

	public function length($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "LENGTH";
		$obj->values[] = $expr;
		return $obj;
	}

	public function locate($expr0, $expr1, $expr2 = null) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "LOCATE";
		$obj->values[] = $expr0;
		$obj->values[] = $expr1;
		if ($expr2 != null) {
			$obj->values[] = $expr2;
		}
		return $obj;
	}

	public function abs($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "ABS";
		$obj->values[] = $expr;
		return $obj;
	}

	public function sqrt($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "SQRT";
		$obj->values[] = $expr;
		return $obj;
	}

	public function mod($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "MOD";
		$obj->values[] = $expr;
		return $obj;
	}

	public function size($expr) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "SIZE";
		$obj->values[] = $expr;
		return $obj;
	}

	public function currentDate() {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "CURRENT_DATE";
		return $obj;
	}

	public function currentTime() {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "CURRENT_TIME";
		return $obj;
	}

	public function currentTimestamp() {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->function = "CURRENT_TIMESTAMP";
		return $obj;
	}

	public function stringLit($object) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->literal = $this->stringVar($object);
		return $obj;
	}

	public function integerLit($object) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->literal = $this->integerVar($object);
		return $obj;
	}

	public function longLit($object) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->literal = $this->longVar($object);
		return $obj;
	}

	public function booleanLit($object) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->literal = $this->booleanVar($object);
		return $obj;
	}

	public function floatLit($object) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->literal = $this->floatVar($object);
		return $obj;
	}

	public function doubleLit($object) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->literal = $this->doubleVar($object);
		return $obj;
	}

	public function dateLit($object) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->literal = $this->dateVar($object);
		return $obj;
	}

	public function dateTimeLit($object) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->literal = $this->dateTimeVar($object);
		return $obj;
	}

	public function timeLit($object) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->literal = $this->timeVar($object);
		return $obj;
	}

	public function stringVar($object) {
		return new SoapVar($object, XSD_STRING, "string", "http://www.w3.org/2001/XMLSchema");
	}

	public function integerVar($object) {
		return new SoapVar($object, XSD_INTEGER, "int", "http://www.w3.org/2001/XMLSchema");
	}

	public function longVar($object) {
		return new SoapVar($object, XSD_LONG, "long", "http://www.w3.org/2001/XMLSchema");
	}

	public function booleanVar($object) {
		return new SoapVar($object, XSD_BOOLEAN, "boolean", "http://www.w3.org/2001/XMLSchema");
	}

	public function floatVar($object) {
		return new SoapVar($object, XSD_FLOAT, "float", "http://www.w3.org/2001/XMLSchema");
	}

	public function doubleVar($object) {
		return new SoapVar($object, XSD_DOUBLE, "double", "http://www.w3.org/2001/XMLSchema");
	}

	public function dateVar($object) {
		return new SoapVar($object, XSD_DATE, "date", "http://www.w3.org/2001/XMLSchema");
	}

	public function dateTimeVar($object) {
		return new SoapVar($object, XSD_DATETIME, "dateTime", "http://www.w3.org/2001/XMLSchema");
	}

	public function timeVar($object) {
		return new SoapVar($object, XSD_TIME, "time", "http://www.w3.org/2001/XMLSchema");
	}

	public function path($path) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->path = $path;
		return $obj;
	}

	public function parameter($parameter) {
		$obj = new Cafeto_QuerySerialize_Expression();
		$obj->parameter = $parameter;
		return $obj;
	}

	public function asc($expr) {
		$order = new Cafeto_QuerySerialize_Order();
		$order->expression = $expr;
		$order->ascending = true;
		return $order;
	}

	public function desc($expr) {
		$order = new Cafeto_QuerySerialize_Order();
		$order->expression = $expr;
		$order->ascending = false;
		return $order;
	}

	public function createQuery($domain = null) {
		$query = new Cafeto_QuerySerialize_CriteriaQuery();
		$query->root = $domain;
		return $query;
	}

	public function domain($name, $alias, $collection = false) {
		$domain = new Cafeto_QuerySerialize_Domain();
		$domain->name = $name;
		$domain->alias = $alias;
		$domain->collection = $collection;
		return $domain;
	}

	public function inner($path, $alias) {
		$join = new Cafeto_QuerySerialize_Join();
		$join->path = $path;
		$join->alias = $alias;
		return $join;
	}

	public function left($path, $alias) {
		$join = new Cafeto_QuerySerialize_Join();
		$join->path = $path;
		$join->alias = $alias;
		$join->type = "LEFT";
		return $join;
	}

	public function fetch($path) {
		$join = new Cafeto_QuerySerialize_Join();
		$join->fetch = true;
		$join->path = $path;
		return $join;
	}

	public function leftFetch($path) {
		$join = new Cafeto_QuerySerialize_Join();
		$join->fetch = true;
		$join->path = $path;
		$join->type = "LEFT";
		return $join;
	}
}
