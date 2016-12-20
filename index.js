'use strict';

import React, { Component, NativeModules } from 'react';

const { WheelPicker } = NativeModules;

export default class WheelPicker extends Component {

	constructor(this.props) {
		super(props);
	}

	toggle() {
		WheelPicker.toggle();
	}

	getResult() {
		WheelPicker.getResult((value) => {
			console.log('显示结果信息');
			console.log(value);
		});
	}

}




