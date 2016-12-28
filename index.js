'use strict';

import React, { Component } from 'react';

import { NativeModules, View, DeviceEventEmitter, requireNativeComponent } from 'react-native';

const WheelPicker = requireNativeComponent('WheelPicker', Picker);

// 判断选择的次数
let selectedTime = 0;

export default class Picker extends Component {

	// 设置字段的类型
	static propTypes = {
		onValueChange: React.PropTypes.func,
		onPickerDone: React.PropTypes.func,
	};

	constructor(...props) {
		super(...props);
		this.state = {
			initOption: {},
			initTime: {},
			selectedOption: 0,
			selectedTime: 0,
		};
	}

	/**
	 * 设置时间选择器的初始化信息
	 */
	initTimePicker(type, data, time) {
		let dataTime = {
			init: type,
			data: '2015-2018',
			time: time,
			selectedTime: ++selectedTime,
		};
		this.setState({
			initTime: dataTime,
		});
	}

	/**
	 * 设置选项选择器的初始化信息
	 */
	initOptionPicker(data, options) {
		let dataOption = {
			data: data,
			options: options,
			selectedOption: ++selectedTime,
		};
		this.setState({
			initOption: dataOption,
		});
	}


 /**
	* 获取选中的数据
  */
	onValueChange(event) {
		if (this.props.onPickerDone) {
			this.props.onPickerDone(event.nativeEvent.data);
		}
	}

	render() {
		const { initTime, initOption } = this.state;
		return (
			<WheelPicker
				ref={ref => { this.picker = ref; } }
				{...this.props}
				initTime={initTime}
				initOption={initOption}
				onValueChange={(event) => this.onValueChange(event)} />
		);
	}

}




