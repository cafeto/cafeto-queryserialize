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

require_once "Cafeto/DeepCopy.php";
require_once "Cafeto/QuerySerialize/CriteriaBuilder.php";

class Cafeto_QuerySerialize {

	public $criteria; // Cafeto_QuerySerialize_CriteriaQuery object
	public $offset = 0; // Set the position of the first result to retrieve.
	public $limit = 0; // Set the maximum number of results to retrieve.
	public $parameters; // HashMap of parameters
	public $deepCopy; // Cafeto_DeepCopy object

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
