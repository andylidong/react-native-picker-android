# react-native-picker-android

## * 本插件只适用于react native 32 版本以上，在React native 32以下的，开发者只要修改Event的事件就行了，
### RN32以前 Event（int，long）要传入两个参数,RN32以后 Event（int）要传入一个参数

## 导入方式：修改package.json,"dependencies"里面加入
## "react-native-picker-android": "git+https://github.com/andylidong/react-native-picker-android.git"
## 然后npm install


<img src="https://github.com/andylidong/react-native-picker-android/blob/master/pickerImage/1.jpg" width=256 height=420 />

<img src="https://github.com/andylidong/react-native-picker-android/blob/master/pickerImage/2.jpg" width=256 height=420 />

<img src="https://github.com/andylidong/react-native-picker-android/blob/master/pickerImage/3.jpg" width=256 height=420 />

<img src="https://github.com/andylidong/react-native-picker-android/blob/master/pickerImage/4.jpg" width=256 height=420 />


##这是Android端的picker，它包含了两种类型的picker

###1.时间选择器，包含了五种时间的选择，
###（1）年月日时分
###（2）年月日
###（3）时分
###（4）月日时分
###（5）年月

###2、选项选择器，单项选择器（多项选择器（关联的暂时没有做））

##注意：
###那个引用的类库https://github.com/andylidong/PickerView.git
###在这里面，将其导入到主项目中，方便用户修改样式



